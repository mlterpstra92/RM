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
            FuncArg v = new FuncArg(arg, new IntVal(-1), order);
            ++order;
            f.addArg(v);
        }
        func_list.put(ident,f);
    }
    
    @Override
    public void caseAComp(AComp node)
    {
        Number n;
        try {
            n = parseExpr(node.getExpr());
        } catch (Exception ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if(n != null)
        {
            if(n instanceof RealVal)
            {
               RealVal b = (RealVal)n;
               NumberFormat numf = NumberFormat.getNumberInstance();
               numf.setMaximumFractionDigits(8);
               System.out.println(numf.format(b.getValue()));
            }
            else
            {
                IntVal i = (IntVal)n;
                System.out.println(i.getValue());
            }
        }
    }
    
    public Number parseExpr(PExpr expr) throws Exception
    {
        if(expr instanceof AComplexexprExpr)
        {
            return parseComplexExpr((AComplexexprExpr)expr);
        }
        
        else return parseSimpleExpr(((ASimpleexprExpr)expr).getSmplexpr());
    }
    
    private Number parseComplexExpr(AComplexexprExpr aComplexExpr) throws Exception {
        Boolean isTrue = parseRelComp((PRelcomp)aComplexExpr.getRelcomp());
        //Boolean isTrue = parseRelExpr((ARelexpr)aComplexExpr.getRelexpr());
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
        Number leftNumber = parseExpr(relexpr.getLeft());
        Number rightNumber = parseExpr(relexpr.getRight());
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
    
    private Number parseSimpleExpr(PSmplexpr aSimpleExpr) throws Exception {
        if(aSimpleExpr instanceof AAddSmplexpr)
        {
            AAddSmplexpr ex = (AAddSmplexpr)aSimpleExpr;
            Number n1 = parseSimpleExpr(ex.getSmplexpr());
            Number n2 = parseTerm(ex.getTerm());
            if(ex.getAddop() instanceof APlusAddop)
                return n1.add(n2);
            else
                return n1.minus(n2);
        }
        else
            return parseTerm(((ATermSmplexpr)aSimpleExpr).getTerm());
    }
    
    private Number parseTerm(PTerm term) throws Exception {
        if(term instanceof AMultTerm)
        {
            AMultTerm ex = (AMultTerm)term;
            Number n1 = parseTerm(ex.getTerm());
            Number n2 = parseFactor(ex.getFactor());
            if(ex.getMulop() instanceof AMultMulop)
                return n1.times(n2);
            else if(ex.getMulop() instanceof AIntegerdivMulop)
            {
                IntVal iv1 = (IntVal)n1;
                IntVal iv2 = (IntVal)n2;
                if(iv2.getValue() == 0)
                    throw new IllegalArgumentException("Division by zero");
                return iv1.idiv(iv2);
            }
            else if(ex.getMulop() instanceof AIntegermodMulop)
            {
                IntVal iv1 = (IntVal)n1;
                IntVal iv2 = (IntVal)n2;

                return iv1.imod(iv2);
            }
            else if(ex.getMulop() instanceof ARealdivMulop)
            {                
                RealVal rv1 = (RealVal)n1;
                RealVal rv2 = (RealVal)n2;

                if(rv1.getValue() < 1.0/1000000.0)
                    throw new IllegalArgumentException("Division by zero");
                return rv1.rdiv(rv2);
            }
            else if(ex.getMulop() instanceof ALshiftMulop)
            {
                IntVal iv1 = (IntVal)n1;
                IntVal iv2 = (IntVal)n2;
                return iv1.lshift(iv2);
            }
            else if(ex.getMulop() instanceof ARshiftMulop)
            {
                IntVal iv1 = (IntVal)n1;
                IntVal iv2 = (IntVal)n2;
                return iv1.rshift(iv2);
            }
            else throw new Exception("Invalid operator " + ex.getMulop().toString());
        }
        else
            return parseFactor(((AFactorTerm)term).getFactor());
    }
    
    private ArrayList<Number> getArgs(PArglst args) throws Exception
    {
        ArrayList<Number> list = new ArrayList<>();
        if(!(args instanceof AEmptyArglst))
        {
            AArgsArglst lst = (AArgsArglst)args;
            parseArgs(lst.getArgs(), list);
        }
        return list;
    }
    
    private void parseArgs(PArgs args, ArrayList<Number> list) throws Exception
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
    
    private Number parseFactor(PFactor factor) throws Exception {
        if(factor instanceof AParFactor)
            return parseExpr(((AParFactor)factor).getExpr());
        else if (factor instanceof AIntFactor)
        {
            AIntFactor intfac = (AIntFactor)factor;
            return new IntVal(Integer.parseInt(intfac.getIntdenotation().getText().trim()));
        }
        else if (factor instanceof ARealFactor)
        {
            ARealFactor intfac = (ARealFactor)factor;
            return new RealVal(Double.parseDouble(intfac.getRealdenotation().getText().trim()));
        }
        else
        {
            //Function call
            AIdentFactor fac = (AIdentFactor)factor;
            String ident = fac.getIdent().getText().trim();
            ArrayList<Number> list = getArgs(fac.getArglst());
            //Built-in pow function because approximating it sucks
            if(ident.equals("pow"))
            {
                Number x = new RealVal(new Double(list.get(0).getValue().toString()));
                Number y = new RealVal(new Double(list.get(1).getValue().toString()));
                return new RealVal(Math.pow((Double)x.getValue(), (Double)y.getValue()));
            }
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
                Number n = list.get(i);
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

   private Number handleFunc() throws Exception {
        if(funcStack.empty())
        {
            System.err.println("Error: stackerror");
            System.exit(-1);
        }
        Function currFunc = funcStack.pop();
        return parseExpr(currFunc.getExpr());
    }

}