/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

import java.util.Objects;

/**
 *
 * @author Maarten
 */
public class BoolType extends Type{
    private Boolean value;
    public BoolType(Boolean b)
    {
        this.value = b;
    }
    @Override
    public Object getValue() 
    {
        return value;
    }

    @Override
    public Boolean IsLessThan(Type other) {
        throw new UnsupportedOperationException("Booleans don't implement relational operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean IsLessOrEqual(Type rightNumber) {
        throw new UnsupportedOperationException("Booleans don't implement relational operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean IsGreaterThan(Type rightNumber) {
        throw new UnsupportedOperationException("Booleans don't implement relational operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean IsGreaterOrEqual(Type rightNumber) {
        throw new UnsupportedOperationException("Booleans don't implement relational operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type add(Type other) {
        throw new UnsupportedOperationException("Booleans don't implement mathematical operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type minus(Type other) {
        throw new UnsupportedOperationException("Booleans don't implement mathematical operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type times(Type other) {
        throw new UnsupportedOperationException("Booleans don't implement mathematical operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type div(Type rightNumber) {
        throw new UnsupportedOperationException("Booleans don't implement mathematical operators"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void negate() {
        this.value = !this.value;
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
        final BoolType other = (BoolType) obj;
        return Objects.equals(this.value, other.getValue());
    }
}
