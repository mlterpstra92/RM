/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

/**
 *
 * @author Maarten
 */
public class FuncArg implements Comparable<FuncArg>{
    private String argName;
    private Number value;
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

    public void setValue(Number n)
    {
        this.value = n;
    }
    public Number getValue() {
        return value;
    }
    
    public FuncArg(String argName, Number value, Integer order)
    {
        this.argName = argName;
        this.value = value;
        this.order = order;
    }

    @Override
    public int compareTo(FuncArg t) {
        return this.order.compareTo(t.getOrder());
    }
}
