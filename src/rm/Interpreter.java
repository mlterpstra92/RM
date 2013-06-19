/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import rm.node.*;
import rm.analysis.*;
import rm.types.CharType;
import rm.types.FuncArg;
import rm.types.Function;
import rm.types.IntegerType;
import rm.types.RealType;
import rm.types.StringType;
import rm.types.Type;

 /**
 *
 * @author Maarten
 */

class Interpreter extends DepthFirstAdapter {

    static boolean FailOnRedeclaration = false;
    static void setFailOnRedeclare() 
    {
        FailOnRedeclaration = true;
    }

    //The function execution stack and the symbol table
    Stack<Function> stack = new Stack<>();
    HashMap<String, Function> symbolTable = new HashMap<>();
    
    //Before the start of the program add the built-in functions
    @Override
    public void caseStart(Start node)
    {
        addBuiltInFunctions();
        if(node.getPProgram() instanceof ADefProgram)
            caseADefProgram((ADefProgram)node.getPProgram());
        else
            caseACompProgram((ACompProgram)node.getPProgram());
    }
    
    //These are basically the main 'hooks' for the interpreter
    @Override
    public void caseADefProgram(ADefProgram node)
    {
        ADef def = (ADef)node.getDef();
        caseADef(def);
        if(node.getProgram() instanceof ADefProgram)
            caseADefProgram((ADefProgram)node.getProgram());
        else if(node.getProgram() instanceof ACompProgram)
            caseACompProgram((ACompProgram)node.getProgram());
    }
    
    @Override
    public void caseACompProgram(ACompProgram node){
        
        AComp comp = (AComp)node.getComp();
        caseAComp(comp);
        if(node.getProgram() instanceof ADefProgram)
            caseADefProgram((ADefProgram)node.getProgram());
        else if(node.getProgram() instanceof ACompProgram)
            caseACompProgram((ACompProgram)node.getProgram());
    }
    
    //Define a new function
    @Override
    public void caseADef(ADef def)
    {
        //Get the name - this is the unique identifier
        String ident = (def.getIdent()).getText().trim();
        //Check if we are declaring a function
        //If we are we should at least issue a warning, depending on what the user wants
        boolean isBuiltin = false;//isBuiltinFunction(ident);
        if((!isBuiltin) && (symbolTable.get(ident) == null))
        {
            //Add a new function to the symbol table
            Function f = new Function(ident);
            f.setExpr(def.getExpr());
            //Keep track of order of arguments so that we don't set the wrong value
            //For the wrong argument
            int order = 0;
            //Add dummy paramters
            for(String arg : getParams(def.getParlst()))
            {
                FuncArg v = new FuncArg(arg, null, order);
                f.addArg(v);
                ++order;
            }
            symbolTable.put(ident,f);
        }
        else 
        {
            //If the user redeclares a function (which is technically possible, but probably not wanted)
            //We should at least issue a warning, possibly failure
            if(FailOnRedeclaration)
            {
                System.err.println("Function may not be redeclared. Exiting...");
                System.exit(-5);
            }
            else
                System.err.println("Warning: function " + ident + " is already defined. Possibly the result is NOT what you intended");
        }
    }
    
    //Retrieve the parameters for a function call
    private Iterable<String> getParams(PParlst parlst) {
        ArrayList<String> list = new ArrayList<>();
        if(!(parlst instanceof AEmptyParlst))
        {
            AParlstParlst lst = (AParlstParlst)parlst;
            interpretParams(lst.getPars(), list);
        }
        return list;
    }

    private void interpretParams(PPars pars, ArrayList<String> list) {
        if(pars instanceof AIdentPars)
            list.add(((AIdentPars)pars).getIdent().getText().trim());
        else
        {
            ACommaPars p = (ACommaPars)pars;
            interpretParams(p.getPars(), list);
            list.add(p.getIdent().getText().trim());
        }
    }
    
    @Override
    //The user calls a function in his/her program.
    //Execute it and display the results
    public void caseAComp(AComp node)
    {
        Type n = interpretExpr(node.getExpr());
        if(n != null)
        {
            //If it is a real value set proper rounding mode
            if(n instanceof RealType)
            {
               NumberFormat numf = NumberFormat.getNumberInstance();
               numf.setMaximumFractionDigits(8);
            }
            System.out.println(n.getValue());
        }
    }
    
    //An expression is either complex (using IF .. THEN .. ELSE .. structure) 
    //or simple (not using this construct)
    public Type interpretExpr(PExpr expr)
    {
        if(expr instanceof AComplexExpr)
        {
            return interpretComplexExpr((AComplexExpr)expr);
        }
        else return interpretSimpleExpr(((ASimpleExpr)expr).getSimplexpr());
    }
    
    //Interpret the conditional expression and depending on its truth value
    //return either the interpretation of the true clause or the false clause
    private Type interpretComplexExpr(AComplexExpr aComplexExpr) {
        Boolean isTrue = interpretRelComp((PRelcomp)aComplexExpr.getRelcomp());
        if(isTrue)
            return interpretExpr(aComplexExpr.getTrueclause());
        else return interpretExpr(aComplexExpr.getFalseclause());
    }
    
    //Determine the truth value of a relational compound
    //Notice that Boolean AND and Boolean OR have the same precedence
    //So it is just a left-to-right interpretation
    private Boolean interpretRelComp(PRelcomp pRelcomp) {
        if(pRelcomp instanceof ACompcondRelcomp)
        {
            ACompcondRelcomp condis = (ACompcondRelcomp)pRelcomp;
            final Boolean leftside = interpretRelComp(condis.getP1());
            final Boolean rightSide = interpretRelExpr((ARelexpr)condis.getP2());
            switch(condis.getCondop().getText().trim())
            {
                case "&&":
                    return leftside && rightSide;
                case "||":
                    return leftside || rightSide;
                default:
                    throw new IllegalArgumentException("Invalid construct " + condis.getCondop().getText().trim());
            }
        }
        else
        {
            ACondRelcomp ac = (ACondRelcomp)pRelcomp;
            return interpretRelExpr((ARelexpr)ac.getRelexpr());
        }
    }
    
    //Determine the interpretation of a "simple" relational expression
    private Boolean interpretRelExpr(ARelexpr relexpr) 
    {
        //Get both operands
        Type leftType = interpretExpr(relexpr.getLeft());
        Type rightType = interpretExpr(relexpr.getRight());
        //Possibly do coercion depending on the datatypes
        if(shouldCoerce(leftType, rightType))
        {
            leftType = new RealType(new Double(leftType.getValue().toString()));
            rightType = new RealType(new Double(rightType.getValue().toString()));
        }
        //Get the right operator and perform the operation
        //The implementation of these operations is handled by their types
        PRelop op = (relexpr.getRelop());
        if(op instanceof AEqualRelop)
            return leftType.equals(rightType);
        if(op instanceof ANotequalRelop)
            return !leftType.equals(rightType);
        if(op instanceof ALessthanRelop)
            return leftType.IsLessThan(rightType);
        if(op instanceof ALessorequalRelop)
            return leftType.IsLessOrEqual(rightType);
        if(op instanceof AGreaterRelop)
            return leftType.IsGreaterThan(rightType);
        if(op instanceof AGreaterequalRelop)
            return leftType.IsGreaterOrEqual(rightType);
        
        throw new IllegalArgumentException("Invalid operator " + relexpr.getRelop().toString());
    }
    
    //A simple expression is either a term or the addition/substraction
    //of a simple expression and a term
    private Type interpretSimpleExpr(PSimplexpr aSimpleExpr) {
        if(aSimpleExpr instanceof AAddSimplexpr)
        {
            AAddSimplexpr ex = (AAddSimplexpr)aSimpleExpr;
            Type n1 = interpretSimpleExpr(ex.getSimplexpr());
            Type n2 = interpretTerm(ex.getTerm());
            //It might be possible that we need to do coercion
            if(shouldCoerce(n1, n2))
            {
                n1 = new RealType(new Double(n1.getValue().toString()));
                n2 = new RealType(new Double(n2.getValue().toString()));
            }
            //Implementations of add and minus courtesy of the type implementation
            if(ex.getAddop() instanceof APlusAddop)
                return n1.add(n2);
            else
                return n1.minus(n2);
        }
        else
            return interpretTerm(((ATermSimplexpr)aSimpleExpr).getTerm());
    }
    
    //A term is either a multiplication/division of a term and factor or just a factor
    private Type interpretTerm(PTerm term) throws IllegalArgumentException {
        if(term instanceof AMultTerm)
        {
            AMultTerm ex = (AMultTerm)term;
            Type n1 = interpretTerm(ex.getTerm());
            Type n2 = interpretFactorExpr(ex.getFactorexpr());
            final Object divisor = n2.getValue();
            //Do possible coercion
            if(shouldCoerce(n1, n2))
            {
                n1 = new RealType(new Double(n1.getValue().toString()));
                n2 = new RealType(new Double(divisor.toString()));
            }
            
            if(ex.getMulop() instanceof AMultMulop)
                return n1.times(n2);
            
            else if(ex.getMulop() instanceof ADivMulop)
            {
                //We cannot divide by zero so we need to check for that
                if(divisor instanceof Double && (Double)divisor == 0.0)
                    throw new IllegalArgumentException("Error: Division by zero");
                
                if(divisor instanceof Integer && (Integer)divisor == 0)
                     throw new IllegalArgumentException("Error: Division by zero");
                
                return n1.div(n2);
                
            }
            //Modulo operation (e.g. 14 % 2 = 0)
            else if(ex.getMulop() instanceof AIntegermodMulop)
            {
                IntegerType iv1 = (IntegerType)n1;
                IntegerType iv2 = (IntegerType)n2;

                return iv1.imod(iv2);
            }
            //Left shift of integers (4 << 1 = 8)
            else if(ex.getMulop() instanceof ALshiftMulop)
            {
                IntegerType iv1 = (IntegerType)n1;
                IntegerType iv2 = (IntegerType)n2;
                return iv1.lshift(iv2);
            }
            //Right shift of integers (4 >> 1 = 2)
            else if(ex.getMulop() instanceof ARshiftMulop)
            {
                IntegerType iv1 = (IntegerType)n1;
                IntegerType iv2 = (IntegerType)n2;
                return iv1.rshift(iv2);
            }
            else throw new IllegalArgumentException("Invalid operator " + ex.getMulop().toString());
        }
        else
            return interpretFactorExpr(((AFactorTerm)term).getFactorexpr());
    }
    
    //Retrieve arguments for a functioncall
    private ArrayList<Type> getArgs(PArglst args)
    {
        ArrayList<Type> list = new ArrayList<>();
        if(!(args instanceof AEmptyArglst))
        {
            AArgsArglst lst = (AArgsArglst)args;
            interpretArgs(lst.getArgs(), list);
        }
        return list;
    }
    
    private void interpretArgs(PArgs args, ArrayList<Type> list)
    {
        if(args instanceof AExprArgs)
            list.add(interpretExpr(((AExprArgs)args).getExpr()));
        else
        {
            AListargsArgs listargs = (AListargsArgs)args;
            interpretArgs(listargs.getArgs(), list);
            list.add(interpretExpr(listargs.getExpr()));
        }
    }
    
    //A factor expression is either an expression or a simple factor
    private Type interpretFactorExpr(PFactorexpr factor) 
    {
        if(factor instanceof AParFactorexpr)
            return interpretExpr(((AParFactorexpr)factor).getExpr());
        else return interpretSimpleFactor(((ASimplefacFactorexpr)factor).getSimplefactor());
    }
    
    //A real number can either be written as a scientific number (1.0E-5) or as
    //A plain old real number (3.14)
    private Type interpretRealNum(ARealNumfac realnum)
    {
        //What follows is a lot of type juggling to extract the doubles out of it
        if(realnum.getRealnum() instanceof AScnumRealnum)
        {
            AScnumRealnum scnum = (AScnumRealnum)realnum.getRealnum();
            double base = Double.parseDouble(scnum.getBase().getText().trim());
            double exp = new Double(interpretNumber(scnum.getExp()).getValue().toString());
            return new RealType(Math.pow(base*10.0, exp));
        }
        else
        {
            ARealRealnum rreal = (ARealRealnum)realnum.getRealnum();
            return new RealType(Double.parseDouble(rreal.getRealdenotation().getText().trim()));
        }
            
    }
    
    //A number is either an integer, a real or a monad
    private Type interpretNumber(PNumfac numfac)
    {
        if(numfac instanceof AIntNumfac)
        {
            return new IntegerType(Integer.parseInt(((AIntNumfac)numfac).getIntdenotation().getText().trim()));
        }
        else if(numfac instanceof ARealNumfac)
            return interpretRealNum((ARealNumfac)numfac);
        
        else if(numfac instanceof AMonadNumfac)
        {
            AMonadNumfac monadnum = (AMonadNumfac)numfac;
            return interpretMonadExpr(monadnum.getMonadexpr());
        }
        else return null;
    }
    
    private Type interpretMonadExpr(PMonadexpr numfac)
    {
        //A monad expression is either the negation of that expression
        if(numfac instanceof ANegMonadexpr)
        {
            ANegMonadexpr negmonad = (ANegMonadexpr)numfac;
            Type t = interpretSimpleFactor(negmonad.getSimplefactor());
            t.negate();
            return t;
        }
        //Or it is the succession of a char
        if(numfac instanceof ASucccharMonadexpr)
        {            
            ASucccharMonadexpr succfac = (ASucccharMonadexpr)numfac;
            Type toSucc = interpretSimpleFactor(succfac.getSimplefactor());
            if(!toSucc.getClass().getName().equals(CharType.class.getName()))
                throw new IllegalArgumentException("Cannot succeed to a non-character");
            return toSucc.add(new IntegerType(1));
        }
        return null;
    }
    
    //A simple factor is just a plain datatype or a function declaration
    private Type interpretSimpleFactor(PSimplefactor factor)
    {
        //If we have a number interpret that
        if (factor instanceof ANumSimplefactor)
        {
            return interpretNumber(((ANumSimplefactor)factor).getNumfac());
        }
        //Same goes for char. We need charAt(1) because a char declaration is
        //like 'a', so charAt(0) contains an apostrophe 
        else if(factor instanceof ACharSimplefactor)
        {
            return new CharType(((ACharSimplefactor)factor).getCharsym().getText().trim().charAt(1));
        }
        
        //The string datatype
        else if(factor instanceof AStringSimplefactor)
        {
            AStringSimplefactor stringFac = (AStringSimplefactor)factor;
            String tempString = (stringFac.getStringsym().getText().trim());
            //Strip the quotes
            tempString = tempString.substring(1, tempString.length() - 1);
            return new StringType(tempString);
        }
        //Function call
        AIdentSimplefactor fac = (AIdentSimplefactor)factor;
        Type t = null;
        try{
             t = handleFunc(fac);
        }
        catch(Exception ex)
        {
            System.err.println("Error in function handling: ");
            ex.printStackTrace();
        }
        finally{
            return t;
        }
    }

    //As the name describes, this function handles the execution of a function in the program
    private Type handleFunc(AIdentSimplefactor func) throws Exception 
    {
        //Get the function name
        String ident = func.getIdent().getText().trim();
        //Retrieve all its arguments
        ArrayList<Type> list = getArgs(func.getArglst());
        //Check if it is a built-in function
        //Type builtInFuncResult = executeBuiltInFunc(ident, list);
        //if(builtInFuncResult != null)
        //    return builtInFuncResult;

        //If we're here it means it wasn't a built-in function so we look
        //in the symbol table for a function that matches our name
        //The downside of this is that we can't do anything like polymorphism
        Function function = symbolTable.get(ident);

        //If we have found a fitting function we set its arguments to the 
        //values it was called with and execute that
        if(function != null)
        {
            setArguments(function, list);
            return executeFunc(function);
        }

        //If we couldn't find it as a function, it might be that we are handling
        //a functions argument. So we look at the current function and see if we
        //can find it there
        Function curr = stack.peek();
        FuncArg fa = curr.getArg(ident);
        if(fa != null)
        {
            return fa.getValue();
        }

        //If we can't even do that, the user probably wants something we don't have
        //so we raise an exception
        throw new Exception("Error: No such function/argument as " + ident + " exists");
    }

    //We should only coerce values if and only if one of the types is a real and the other is an integer
    //Not if both types are reals or if we are dealing with doubles and chars
    private boolean shouldCoerce(Type n1, Type n2) 
    {
        return ((n1 instanceof RealType) ^ (n2 instanceof RealType)) && ((n1 instanceof IntegerType ^ n2 instanceof IntegerType));
    }

    //Possible improvement: Set the appropriate expression for built-in functions
    //so we don't need this
    private Type executeBuiltInFunc(Function f) 
    {
        if(f.getArgs().size() == 1)
        {
            Type retVal;
            Double d = new Double(f.getArg("x").getValue().getValue().toString().trim());
            switch(f.getName())
            {
                case "sin":
                    retVal = new RealType(Math.sin(d));
                    break;
                case "cos":
                    retVal = new RealType(Math.cos(d));
                    break;
                case "tan":
                    retVal = new RealType(Math.tan(d));
                    break;
                case "exp":
                    retVal = new RealType(Math.exp(d));
                    break;
                case "log":
                    retVal = new RealType(Math.log(d));
                    break;
                default:
                    retVal = null;
                    break;
            }
            return retVal;
        }
        throw new IllegalArgumentException("One argument expected");
    }

    //Update the arguments of the called function with new values
    private void setArguments(Function func, ArrayList<Type> list) 
    {
        int i = 0;
        for(FuncArg arg : func.getArgs())
        {
            arg.setValue(list.get(i));
            ++i;
        }
    }

    //Function execution is quite simple: push it on the stack, interpret its expression
    //and pop it off
    private Type executeFunc(Function function) 
    {
        if(function.getExpr() != null) {
            stack.push(function);
            Type t = interpretExpr(function.getExpr());
            stack.pop();
            return t;
        }
        else 
            return executeBuiltInFunc(function);
    }
    
    //Make sure the stack is empty at the end of program execution
    @Override
    public void caseAEmpty(AEmpty node)
    {
        if(!stack.empty())
            System.err.println("Unexpected elements on stack");
    }

    //Functions to be added to the symbol table
    private void addBuiltInFunctions() {
        ArrayList<String> builtInFuncs = new ArrayList<String>(){{
            add("cos");
            add("sin");
            add("tan");
            add("exp");
            add("log");
        }};
        for(String s : builtInFuncs)
        {
            ArrayList<FuncArg> args = new ArrayList<>();
            //Default dummy parameter
            args.add(new FuncArg("x", null, 0));
            Function f = new Function(s, args);
            symbolTable.put(s, f);
        }
    }
}