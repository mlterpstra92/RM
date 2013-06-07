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
    private Type value;
    private Integer order;

    public FuncArg(String argName, Type value, Integer order)
    {
        this.argName = argName;
        this.order = order;
        this.value = value;
    }
    
    public Type getValue() {
        return value;
    }

    public void setValue(Type value) {
        this.value = value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    
    public String getArgName() {
        return argName;
    }
    
    @Override
    public int compareTo(FuncArg t) {
        return this.order.compareTo(t.getOrder());
    }
}
