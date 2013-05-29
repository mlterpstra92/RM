/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.*;
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

    Stack<Function> funcStack = new Stack<>();
    HashMap<String, FuncArg> argStack = new HashMap<>();
    HashMap<String, Function> func_list = new HashMap<>();
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
        if(func_list.get(ident) != null)         
        {
            System.err.println("Function already defined");
            System.exit(-1);
        }
        Function f = new Function(ident);
        f.setExpr(def.getExpr());
        int order = 0;
        for(String arg : getParams(def.getParlst()))
        {
            FuncArg v = new FuncArg(arg, null, order);
            ++order;
            f.addArg(v);
        }
        func_list.put(ident,f);
    }
    
    @Override
    public void caseAComp(AComp node)
    {
        Type n;
        try {
            n = parseExpr(node.getExpr());
        } catch (Exception ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
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
            switch(condis.getCondis().getText().trim())
            {
                case "&&":
                    return parseRelComp(condis.getP1()) && parseRelExpr((ARelexpr)condis.getP2());
                case "||":
                    boolean b1 = parseRelComp(condis.getP1());
                    if(b1)
                        return b1;
                    else
                        return parseRelExpr((ARelexpr)condis.getP2());
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
        else if(op instanceof ANotequalRelop)
            return !leftNumber.equals(rightNumber);
        else if(op instanceof ALessthanRelop)
            return leftNumber.IsLessThan(rightNumber);
        else if(op instanceof ALessorequalRelop)
            return leftNumber.IsLessOrEqual(rightNumber);
        else if(op instanceof AGreaterRelop)
            return leftNumber.IsGreaterThan(rightNumber);
        else if(op instanceof AGreaterequalRelop)
            return leftNumber.IsGreaterOrEqual(rightNumber);
        else throw new Exception("Invalid operator " + relexpr.getRelop().toString());
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
            Type n2 = parseFactor(ex.getFactor());
            if(shouldCoerce(n1, n2))
            {
                n1 = new RealType(new Double(n1.getValue().toString()));
                n2 = new RealType(new Double(n2.getValue().toString()));
            }
            if(ex.getMulop() instanceof AMultMulop)
                return n1.times(n2);
            else if(ex.getMulop() instanceof ADivMulop)
            {
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
            return parseFactor(((AFactorTerm)term).getFactor());
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
    
    private Type parseFactor(PFactor factor) throws Exception {
        if(factor instanceof AParFactor)
            return parseExpr(((AParFactor)factor).getExpr());
        else if (factor instanceof AIntFactor)
        {
            AIntFactor intfac = (AIntFactor)factor;
            return new IntegerType(Integer.parseInt(intfac.getIntdenotation().getText().trim()));
        }
        else if (factor instanceof ARealFactor)
        {
            ARealFactor intfac = (ARealFactor)factor;
            return new RealType(Double.parseDouble(intfac.getRealdenotation().getText().trim()));
        }
        else if(factor instanceof ANegatetypeFactor)
        {
            ANegatetypeFactor minfac = (ANegatetypeFactor)factor;
            Type t = parseFactor(minfac.getFactor());
            t.negate();
            return t;
        }
        else
        {
            //Function call
            AIdentFactor fac = (AIdentFactor)factor;
            String ident = fac.getIdent().getText().trim();
            ArrayList<Type> list = getArgs(fac.getArglst());
            Type builtInFuncResult = executeBuiltInFunc(ident, list);
            if(builtInFuncResult != null)
                return builtInFuncResult;
            Function func = func_list.get(ident);
            if(func == null)
            {
                FuncArg fa = argStack.get(ident);
                if(fa == null)
                    throw new IllegalArgumentException("No such func/arg " + ident);
                return fa.getValue();
            }
            for(int i = 0; i < list.size(); ++i)
            {
                Type n = list.get(i);
                FuncArg fa = func.getArg(i);
                
                if(fa != null)
                {
                    fa.setValue(n);
                    argStack.put(fa.getArgName(), fa);
                }
                else
                    throw new IllegalArgumentException("Invalid arg " + ident);
            }
            funcStack.push(func);
            return handleFunc();
        }
    }

   private Type handleFunc() throws Exception {
        if(funcStack.empty())
        {
            System.err.println("Error: stackerror");
            System.exit(-1);
        }
        Function currFunc = funcStack.pop();
        return parseExpr(currFunc.getExpr());
    }

    private boolean shouldCoerce(Type n1, Type n2) {
        return (n1 instanceof RealType) ^ (n2 instanceof RealType);
    }

    private Type executeBuiltInFunc(String ident, ArrayList<Type> list) 
    {
        Type retVal;
        if(list.isEmpty())
            return null;
        
        Double d = new Double(list.get(0).getValue().toString());
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
}