/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

import rm.node.PExpr;

/**
 *
 * @author Maarten
 */
public class FuncArg implements Comparable<FuncArg>{
    private String argName;
    private PExpr expr;
    private Type value;

    public Type getValue() {
        return value;
    }

    public void setValue(Type value) {
        this.value = value;
    }
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    
    public String getArgName() {
        return argName;
    }

    public void setExpr(PExpr n)
    {
        this.expr = n;
    }
    public PExpr getExpr() {
        return expr;
    }
    
    
    
    public FuncArg(String argName, PExpr value, Integer order)
    {
        this.argName = argName;
        this.expr = value;
        this.order = order;
        this.value = null;
    }

    @Override
    public int compareTo(FuncArg t) {
        return this.order.compareTo(t.getOrder());
    }
}
