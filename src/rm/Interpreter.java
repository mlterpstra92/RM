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



            /**
 *
 * @author Maarten
 */

class Interpreter extends DepthFirstAdapter {
    private Iterable<String> getParams(PParlst parlst) {
        ArrayList<String> list = new ArrayList<>();
        if(!(parlst instanceof AEmptyParlst))
        {
            AParlstParlst lst = (AParlstParlst)parlst;
            parseParams(lst.getPars(), list);
        }
        return list;
    }

    private void parseParams(PPars pars, ArrayList<String> list) {
        if(pars instanceof AIdentPars)
            list.add(((AIdentPars)pars).getIdent().getText().trim());
        else
        {
            ACommaPars p = (ACommaPars)pars;
            parseParams(p.getPars(), list);
            list.add(p.getIdent().getText().trim());
        }
    }

    Stack<Function> stack = new Stack<>();
    HashMap<String, Function> symbolTable = new HashMap<>();
    @Override
    public void caseADefProgram(ADefProgram node){
        
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
    
    @Override
    public void caseADef(ADef def)
    {
        String ident = (def.getIdent()).getText().trim();
        if(symbolTable.get(ident) != null)         
        {
            System.err.println("Function already defined");
            return;
        }
        Function f = new Function(ident);
        f.setExpr(def.getExpr());
        int order = 0;
        for(String arg : getParams(def.getParlst()))
        {
            FuncArg v = new FuncArg(arg, null, order);
            f.addArg(v);
            ++order;
        }
        symbolTable.put(ident,f);
    }
    
    @Override
    public void caseAComp(AComp node)
    {
        Type n;
        try {
            n = parseExpr(node.getExpr());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
        if(n != null)
        {
            if(n instanceof RealType)
            {
               NumberFormat numf = NumberFormat.getNumberInstance();
               numf.setMaximumFractionDigits(8);
            }
            System.out.println(n.getValue());
        }
    }
    
    public Type parseExpr(PExpr expr) throws Exception
    {
        if(expr instanceof AComplexexprExpr)
        {
            return parseComplexExpr((AComplexexprExpr)expr);
        }
        else return parseSimpleExpr(((ASimpleexprExpr)expr).getSmplexpr());
    }
    
    private Type parseComplexExpr(AComplexexprExpr aComplexExpr) throws Exception {
        Boolean isTrue = parseRelComp((PRelcomp)aComplexExpr.getRelcomp());
        if(isTrue)
            return parseExpr(aComplexExpr.getTrueclause());
        else return parseExpr(aComplexExpr.getFalseclause());
    }
    
    private Boolean parseRelComp(PRelcomp pRelcomp) throws Exception {
        if(pRelcomp instanceof ACondisRelcomp)
        {
            ACondisRelcomp condis = (ACondisRelcomp)pRelcomp;
            final Boolean leftside = parseRelComp(condis.getP1());
            final Boolean rightSide = parseRelExpr((ARelexpr)condis.getP2());
            switch(condis.getCondis().getText().trim())
            {
                case "&&":
                    return leftside && rightSide;
                case "||":
                    return leftside || rightSide;
                default:
                    throw new Exception("Invalid construct " + condis.getCondis().getText().trim());
            }
        }
        else
        {
            ACondRelcomp ac = (ACondRelcomp)pRelcomp;
            return parseRelExpr((ARelexpr)ac.getRelexpr());
        }
    }
    
    private Boolean parseRelExpr(ARelexpr relexpr) throws Exception 
    {
        Type leftNumber = parseExpr(relexpr.getLeft());
        Type rightNumber = parseExpr(relexpr.getRight());
        if(shouldCoerce(leftNumber, rightNumber))
        {
            leftNumber = new RealType(new Double(leftNumber.getValue().toString()));
            rightNumber = new RealType(new Double(rightNumber.getValue().toString()));
        }
        PRelop op = (relexpr.getRelop());
        if(op instanceof AEqualRelop)
            return leftNumber.equals(rightNumber);
        if(op instanceof ANotequalRelop)
            return !leftNumber.equals(rightNumber);
        if(op instanceof ALessthanRelop)
            return leftNumber.IsLessThan(rightNumber);
        if(op instanceof ALessorequalRelop)
            return leftNumber.IsLessOrEqual(rightNumber);
        if(op instanceof AGreaterRelop)
            return leftNumber.IsGreaterThan(rightNumber);
        if(op instanceof AGreaterequalRelop)
            return leftNumber.IsGreaterOrEqual(rightNumber);
        
        throw new Exception("Invalid operator " + relexpr.getRelop().toString());
    }
    
    private Type parseSimpleExpr(PSmplexpr aSimpleExpr) throws Exception {
        if(aSimpleExpr instanceof AAddSmplexpr)
        {
            AAddSmplexpr ex = (AAddSmplexpr)aSimpleExpr;
            Type n1 = parseSimpleExpr(ex.getSmplexpr());
            Type n2 = parseTerm(ex.getTerm());
            if(shouldCoerce(n1, n2))
            {
                n1 = new RealType(new Double(n1.getValue().toString()));
                n2 = new RealType(new Double(n2.getValue().toString()));
            }
            if(ex.getAddop() instanceof APlusAddop)
                return n1.add(n2);
            else
                return n1.minus(n2);
        }
        else
            return parseTerm(((ATermSmplexpr)aSimpleExpr).getTerm());
    }
    
    private Type parseTerm(PTerm term) throws Exception {
        if(term instanceof AMultTerm)
        {
            AMultTerm ex = (AMultTerm)term;
            Type n1 = parseTerm(ex.getTerm());
            Type n2 = parseFactorexpr(ex.getFactorexpr());
            final Object divisor = n2.getValue();
            if(shouldCoerce(n1, n2))
            {
                n1 = new RealType(new Double(n1.getValue().toString()));
                n2 = new RealType(new Double(divisor.toString()));
            }
            if(ex.getMulop() instanceof AMultMulop)
                return n1.times(n2);
            
            else if(ex.getMulop() instanceof ADivMulop)
            {
                if(divisor instanceof Double && (Double)divisor == 0.0)
                    throw new IllegalArgumentException("Error: Division by zero");
                
                if(divisor instanceof Integer && (Integer)divisor == 0)
                     throw new IllegalArgumentException("Error: Division by zero");
                
                return n1.div(n2);
                
            }
            else if(ex.getMulop() instanceof AIntegermodMulop)
            {
                IntegerType iv1 = (IntegerType)n1;
                IntegerType iv2 = (IntegerType)n2;

                return iv1.imod(iv2);
            }
            else if(ex.getMulop() instanceof ALshiftMulop)
            {
                IntegerType iv1 = (IntegerType)n1;
                IntegerType iv2 = (IntegerType)n2;
                return iv1.lshift(iv2);
            }
            else if(ex.getMulop() instanceof ARshiftMulop)
            {
                IntegerType iv1 = (IntegerType)n1;
                IntegerType iv2 = (IntegerType)n2;
                return iv1.rshift(iv2);
            }
            else throw new Exception("Invalid operator " + ex.getMulop().toString());
        }
        else
            return parseFactorexpr(((AFactorTerm)term).getFactorexpr());
    }
    
    private ArrayList<Type> getArgs(PArglst args) throws Exception
    {
        ArrayList<Type> list = new ArrayList<>();
        if(!(args instanceof AEmptyArglst))
        {
            AArgsArglst lst = (AArgsArglst)args;
            parseArgs(lst.getArgs(), list);
        }
        return list;
    }
    
    private void parseArgs(PArgs args, ArrayList<Type> list) throws Exception
    {
        if(args instanceof AExprArgs)
            list.add(parseExpr(((AExprArgs)args).getExpr()));
        else
        {
            AListargsArgs listargs = (AListargsArgs)args;
            parseArgs(listargs.getArgs(), list);
            list.add(parseExpr(listargs.getExpr()));
        }
    }
    
    private Type parseFactorexpr(PFactorexpr factor) throws Exception 
    {
        if(factor instanceof AParFactorexpr)
            return parseExpr(((AParFactorexpr)factor).getExpr());
        else return parseSimpleFactor(((ASimplefacFactorexpr)factor).getSimplefactor());
    }
    
    /*private Type parseNumber(PNumfac numfac)
    {
        if(numfac instanceof AIntNumfac)
        {
            AIntNumfac intfac = (AIntNumfac)numfac;
            return new IntegerType(Integer.parseInt(intfac.getIntdenotation().getText().trim()));
        }        
        else
        {
            ARealNumfac realfac = (ARealNumfac)numfac;
            return new RealType(Double.parseDouble(realfac.getRealdenotation().getText().trim()));
        }

    }*/
    
    private Type parseRealNum(ARealNumfac realnum) throws Exception
    {
        if(realnum.getRealnum() instanceof AScnumRealnum)
        {
            AScnumRealnum scnum = (AScnumRealnum)realnum.getRealnum();
            double base = Double.parseDouble(scnum.getBase().getText().trim());
            double exp = new Double(parseNumber(scnum.getExp()).getValue().toString());
            System.out.println(base + " " + exp);
            
            return new RealType(Math.pow(base*10.0, exp));
        }
        else
        {
            ARealRealnum rreal = (ARealRealnum)realnum.getRealnum();
            return new RealType(Double.parseDouble(rreal.getRealdenotation().getText().trim()));
        }
            
    }
    private Type parseNumber(PNumfac numfac) throws Exception
    {
        if(numfac instanceof AIntNumfac)
        {
            return new IntegerType(Integer.parseInt(((AIntNumfac)numfac).getIntdenotation().getText().trim()));
        }
        else if(numfac instanceof ARealNumfac)
            return parseRealNum((ARealNumfac)numfac);
        else if(numfac instanceof AMonadNumfac)
        {
             AMonadNumfac monadnum = (AMonadNumfac)numfac;
            ANegMonadexpr negmonad = (ANegMonadexpr)monadnum.getMonadexpr();
            Type t = parseSimpleFactor(negmonad.getSimplefactor());
            t.negate();
            return t;

        }
        else return null;
    }
    private Type parseNumber(ANumSimplefactor numfac) throws Exception{
        if(numfac.getNumfac() instanceof AIntNumfac)
        {
            AIntNumfac intfac = (AIntNumfac)numfac.getNumfac();
            return new IntegerType(Integer.parseInt(intfac.getIntdenotation().getText().trim()));
        }
        else if(numfac.getNumfac() instanceof ARealNumfac)
        {
            return parseRealNum((ARealNumfac)numfac.getNumfac());
        }
        else if(numfac.getNumfac() instanceof AMonadNumfac)
        {
            AMonadNumfac monadnum = (AMonadNumfac)numfac.getNumfac();
            ANegMonadexpr negmonad = (ANegMonadexpr)monadnum.getMonadexpr();
            Type t = parseSimpleFactor(negmonad.getSimplefactor());
            t.negate();
            return t;
        }
        return null;
        
    }
    
    private Type parseSimpleFactor(PSimplefactor factor) throws Exception
    {
        if (factor instanceof ANumSimplefactor)
        {
            return parseNumber((ANumSimplefactor)factor);
        }
        else if(factor instanceof ACharSimplefactor)
        {
            return new CharType(((ACharSimplefactor)factor).getCharsym().getText().trim().charAt(1));
        }
        
        else if(factor instanceof ASucccharSimplefactor)
        {
            ASucccharSimplefactor succfac = (ASucccharSimplefactor)factor;
            Type toSucc = parseSimpleFactor(succfac.getSimplefactor());
            if(!toSucc.getClass().getName().equals(CharType.class.getName()))
                throw new IllegalArgumentException("Cannot succeed to a non-character");
            return toSucc.add(new IntegerType(1));
        }
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
        return handleFunc(fac);
    }

   private Type handleFunc(AIdentSimplefactor func) throws Exception {
        String ident = func.getIdent().getText().trim();
        ArrayList<Type> list = getArgs(func.getArglst());
        Type builtInFuncResult = executeBuiltInFunc(ident, list);
        if(builtInFuncResult != null)
            return builtInFuncResult;
        
        Function function = symbolTable.get(ident);
        
        if(function != null)
        {
            stack.push(function);
            setArguments(function, list);
            return parseExpr(function.getExpr());
        }
        
        Function curr = stack.peek();
        FuncArg fa = curr.getArg(ident);
        if(fa != null)
        {
            return fa.getValue();
        }
        throw new Exception("Error: No such function/argument as " + ident + " exists");
    }

    private boolean shouldCoerce(Type n1, Type n2) 
    {
        return ((n1 instanceof RealType) ^ (n2 instanceof RealType)) && ((n1 instanceof IntegerType || n2 instanceof IntegerType));
    }

    private Type executeBuiltInFunc(String ident, ArrayList<Type> list) throws Exception 
    {
        Type retVal;
        if(list.isEmpty())
            return null;
        
        Double d = new Double((list.get(0)).getValue().toString());
        switch(ident)
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

    private void setArguments(Function func, ArrayList<Type> list) 
    {
        for(int i = 0; i < func.getArgs().size(); ++i)
        {
            FuncArg fa = func.getArg(i);
            fa.setValue(list.get(i));
        }
    }
}