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
public class RealVal extends Number {
    private Double value;
    
    public RealVal(Double v)
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
        final IntVal other = (IntVal) obj;
        if (!Objects.equals(this.value, other.getValue())) {
            return false;
        }
        return true;
    }

    public RealVal checkObj(Number obj)
    {
        if (obj == null) {
            return null;
        }
        if (getClass() != obj.getClass()) {
            return null;
        }
        final RealVal other = (RealVal) obj;
        return other;
    }
    @Override
    public Boolean IsLessThan(Number obj) {
        RealVal other = checkObj(obj);
        if(obj != null)
            return this.value < other.getValue();    
        else
            return false;
    }

    @Override
    public Boolean IsLessOrEqual(Number obj) {
        RealVal other = checkObj(obj);
        if(obj != null)
            return this.value <= other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterThan(Number obj) {
        RealVal other = checkObj(obj);
        if(obj != null)
            return this.value > other.getValue();    
        else
            return false;    
    }

    @Override
    public Boolean IsGreaterOrEqual(Number obj) {
        RealVal other = checkObj(obj);
        if(obj != null)
            return this.value >= other.getValue();    
        else
            return false;    
    }
    
    public RealVal rdiv(Number obj)
    {
        RealVal other = checkObj(obj);
        if(obj != null)
            return new RealVal(this.value / other.getValue());
        return null;
    }

    @Override
    public Number add(Number obj) {
        RealVal other = checkObj(obj);
        if(obj != null)
            return new RealVal(this.value + other.getValue());
        return null;
    }

    @Override
    public Number minus(Number obj) {
        RealVal other = checkObj(obj);
        if(obj != null)
            return new RealVal(this.value - other.getValue());
        return null;    }

    @Override
    public Number times(Number obj) {
        RealVal other = checkObj(obj);
        if(obj != null)
            return new RealVal(this.value * other.getValue());
        return null;
    }
}
