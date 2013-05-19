/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

import java.util.ArrayList;
import java.util.Arrays;
import rm.node.PExpr;

/**
 *
 * @author Maarten
 */
public class Function {
    private String name;
    private PExpr expr;
    private ArrayList<FuncArg> args;

    public PExpr getExpr() {
        return expr;
    }

    public void setExpr(PExpr expr) {
        this.expr = expr;
    }
    
    public String getName() {
        return name;
    }

    public ArrayList<FuncArg> getArgs() {
        Arrays.sort(args.toArray());
        return args;
    }
    
    public void addArg(FuncArg n)
    {
        if(!this.args.contains(n))
        {
            this.args.add(n);
        }
    }
    
    public FuncArg getArg(String name)
    {
        //Arrays.sort(args.toArray());
        for(FuncArg arg : args)
        {
            if(arg.getArgName().equals(name))
                return arg;
        }
        return null;
    }
    
    public FuncArg getArg(int i)
    {
        //Arrays.sort(args.toArray());
        return args.get(i);
    }
    
    public Function(String name)
    {
        this(name, new ArrayList<FuncArg>());
    }
    
    public Function(String name, ArrayList<FuncArg> args)
    {
        this.name = name;
        this.args = args;
    }
}
