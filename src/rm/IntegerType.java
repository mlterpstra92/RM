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
public class IntegerType extends Type
{
    private Integer value;

    public IntegerType(Integer v)
    {
        this.value = v;
    }
    
    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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

    public IntegerType checkObj(Type obj)
    {
        if (obj == null) {
            return null;
        }
        if (getClass() != obj.getClass()) {
            return null;
        }
        final IntegerType other = (IntegerType) obj;
        return other;
    }
    @Override
    public Boolean IsLessThan(Type obj) {
        IntegerType other = checkObj(obj);
        if(obj != null)
            return this.value < other.getValue();    
        else
            return false;
    }

    @Override
    public Boolean IsLessOrEqual(Type obj) {
        IntegerType other = checkObj(obj);
        if(obj != null)
            return this.value <= other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterThan(Type obj) {
        IntegerType other = checkObj(obj);
        if(obj != null)
            return this.value > other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterOrEqual(Type obj) {
        IntegerType other = checkObj(obj);
        if(obj != null)
            return this.value >= other.getValue();    
        else
            return false;    
    }
    
    public IntegerType idiv(Type obj)
    {
        IntegerType other = checkObj(obj);
        if(obj != null){
            return new IntegerType(this.value / other.getValue());
        }
        return null;
    }
    
    public IntegerType imod(Type obj)
    {
        IntegerType other = checkObj(obj);
        if(obj != null){
            return new IntegerType(this.value % other.getValue());
        }
        return null;
    }

    @Override
    public Type add(Type obj) {
        IntegerType other = checkObj(obj);
        if(obj != null){
            return new IntegerType(this.value + other.getValue());
        }
        return null;    
    }

    @Override
    public Type minus(Type obj) {
        IntegerType other = checkObj(obj);
        if(obj != null){
            return new IntegerType(this.value - other.getValue());
        }
        return null;
    }

    @Override
    public Type times(Type obj) {
        IntegerType other = checkObj(obj);
        if(obj != null){
            return new IntegerType(this.value * other.getValue());
        }
        return null;    
    }
    
    public IntegerType rshift(IntegerType obj)
    {
        return new IntegerType(this.value >> obj.getValue());
    }
    
    public IntegerType lshift(IntegerType obj)
    {
        return new IntegerType(this.value << obj.getValue());
    }

    @Override
    public Type div(Type rightNumber) {
        IntegerType other = checkObj(rightNumber);
        if(other != null)
            return new IntegerType(this.value / other.getValue());
        return null;
    }

    @Override
    public void negate() {
        this.value *= -1;
    }
}
