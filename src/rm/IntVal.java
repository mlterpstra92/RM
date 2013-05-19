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
public class IntVal extends Number
{
    private Integer value;

    public IntVal(Integer v)
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
        final IntVal other = (IntVal) obj;
        if (!Objects.equals(this.value, other.getValue())) {
            return false;
        }
        return true;
    }

    public IntVal checkObj(Number obj)
    {
        if (obj == null) {
            return null;
        }
        if (getClass() != obj.getClass()) {
            return null;
        }
        final IntVal other = (IntVal) obj;
        return other;
    }
    @Override
    public Boolean IsLessThan(Number obj) {
        IntVal other = checkObj(obj);
        if(obj != null)
            return this.value < other.getValue();    
        else
            return false;
    }

    @Override
    public Boolean IsLessOrEqual(Number obj) {
        IntVal other = checkObj(obj);
        if(obj != null)
            return this.value <= other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterThan(Number obj) {
        IntVal other = checkObj(obj);
        if(obj != null)
            return this.value > other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterOrEqual(Number obj) {
        IntVal other = checkObj(obj);
        if(obj != null)
            return this.value >= other.getValue();    
        else
            return false;    
    }
    
    public IntVal idiv(Number obj)
    {
        IntVal other = checkObj(obj);
        if(obj != null){
            return new IntVal(this.value / other.getValue());
        }
        return null;
    }
    
    public IntVal imod(Number obj)
    {
        IntVal other = checkObj(obj);
        if(obj != null){
            return new IntVal(this.value % other.getValue());
        }
        return null;
    }

    @Override
    public Number add(Number obj) {
        IntVal other = checkObj(obj);
        if(obj != null){
            return new IntVal(this.value + other.getValue());
        }
        return null;    }

    @Override
    public Number minus(Number obj) {
        IntVal other = checkObj(obj);
        if(obj != null){
            return new IntVal(this.value - other.getValue());
        }
        return null;
    }

    @Override
    public Number times(Number obj) {
        IntVal other = checkObj(obj);
        if(obj != null){
            return new IntVal(this.value * other.getValue());
        }
        return null;    
    }
    
    public IntVal rshift(IntVal obj)
    {
        return new IntVal(this.value >> obj.getValue());
    }
    
    public IntVal lshift(IntVal obj)
    {
        return new IntVal(this.value << obj.getValue());
    }
}
