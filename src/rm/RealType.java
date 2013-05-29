/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

import java.util.Objects;

/**
 *
 * @author maarten
 */
public class RealType extends Type {
    private Double value;
    
    public RealType(Double v)
    {
        this.value = v;
    }
        
    @Override    
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    @Override
    public int hashCode()
    {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IntegerType other = (IntegerType) obj;
        return Objects.equals(this.value, other.getValue());
    }

    public RealType checkObj(Type obj)
    {
        if (obj == null) {
            return null;
        }
        if (getClass() != obj.getClass()) {
            return null;
        }
        final RealType other = (RealType) obj;
        return other;
    }
    @Override
    public Boolean IsLessThan(Type obj) {
        RealType other = checkObj(obj);
        if(other != null)
            return this.value < other.getValue();    
        else
            return false;
    }

    @Override
    public Boolean IsLessOrEqual(Type obj) {
        RealType other = checkObj(obj);
        if(other != null)
            return this.value <= other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterThan(Type obj) {
        RealType other = checkObj(obj);
        if(other != null)
            return this.value > other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterOrEqual(Type obj) {
        RealType other = checkObj(obj);
        if(other != null)
            return this.value >= other.getValue();    
        else
            return false;    
    }
    
    public RealType rdiv(Type obj)
    {
        RealType other = checkObj(obj);
        if(other != null)
            return new RealType(this.value / other.getValue());
        return null;
    }

    @Override
    public Type add(Type obj) {
        RealType other = checkObj(obj);
        if(other != null)
            return new RealType(this.value + other.getValue());
        return null;
    }

    @Override
    public Type minus(Type obj) {
        RealType other = checkObj(obj);
        if(other != null)
            return new RealType(this.value - other.getValue());
        return null;    }

    @Override
    public Type times(Type obj) {
        RealType other = checkObj(obj);
        if(other != null)
            return new RealType(this.value * other.getValue());
        return null;
    }

    @Override
    public Type div(Type rightNumber) 
    {
        RealType other = checkObj(rightNumber);
        if(other != null)
            return new RealType(this.value / other.getValue());
        return null;
    }

    @Override
    public void negate() {
        this.value *= -1.0;
    }
}
