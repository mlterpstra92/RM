package rm.types;

import java.util.ArrayList;
import java.util.Arrays;
import rm.node.PExpr;

/**
 *
 * @author Maarten
 * This class represents a function
 * It has an unique name, an expression that it can execute and a list of arguments
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

    //Sorting is needed because order of arguments is important
    public ArrayList<FuncArg> getArgs() {
        Arrays.sort(args.toArray());
        return args;
    }
    
    //Sorting is needed because order of arguments is important
    public void addArg(FuncArg n)
    {
        if(!this.args.contains(n))
        {
            this.args.add(n);
        }
        Arrays.sort(args.toArray());
    }
    
    //Sorting is needed because order of arguments is important
    public FuncArg getArg(String name)
    {
        Arrays.sort(args.toArray());
        for(FuncArg arg : args)
        {
            if(arg.getArgName().equals(name))
                return arg;
        }
        return null;
    }
    
    //Sorting is needed because order of arguments is importants
    public FuncArg getArg(int i)
    {
        Arrays.sort(args.toArray());
        return args.get(i);
    }
    
    //Needed constructors
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