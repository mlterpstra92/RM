package rm.types;

/**
 *
 * @author Maarten
 * This class represents an argument for a function
 * It is identified by a name and has a value and an order
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
