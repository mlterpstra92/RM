
package rm.types;

import java.util.Objects;

/**
 *
 * @author Maarten
 * Implementation of the String datatype
 */
public class StringType extends Type {
    private String value;
    public StringType(String s)
    {
        this.value = s;
    }
    
    @Override
    public Object getValue() {
        return value;
    }

    
    @Override
    public Boolean IsLessThan(Type other) {
        StringType obj = checkObj(other);
        if(obj != null)
            return this.value.compareTo((String)obj.getValue()) < 0;
        throw new IllegalArgumentException("Invalid type of other");
    }

    @Override
    public Boolean IsLessOrEqual(Type other) {
        StringType obj = checkObj(other);
        if(obj != null) return this.value.compareTo((String)obj.getValue()) <= 0;    
        throw new IllegalArgumentException("Invalid type of other");
    }

    @Override
    public Boolean IsGreaterThan(Type other) {
        StringType obj = checkObj(other);
        if(obj != null) return this.value.compareTo((String)obj.getValue()) > 0;    
        throw new IllegalArgumentException("Invalid type of other");    }

    @Override
    public Boolean IsGreaterOrEqual(Type other) {
        StringType obj = checkObj(other);
        if(obj != null) return this.value.compareTo((String)obj.getValue()) >= 0;    
        throw new IllegalArgumentException("Invalid type of other");    }

    //Add in a string context means concatenation
    @Override
    public Type add(Type other) {
        StringType obj = checkObj(other);
        return this.concat(obj);
    }

    @Override
    public Type minus(Type other) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Type times(Type other) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public Type div(Type rightNumber) {
        throw new UnsupportedOperationException("Not supported."); 
    }

    @Override
    public void negate() {
        throw new UnsupportedOperationException("Not supported."); 
    }
 
    public StringType concat(StringType other)
    {
        return new StringType(value.concat((String)other.getValue()));
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
        final StringType other = (StringType) obj;
        return Objects.equals(this.value, other.getValue());
    }

    //Perform a "safe cast" the desired datatype
    public StringType checkObj(Type obj)
    {
        if (obj == null) {
            return null;
        }
        if (getClass() != obj.getClass()) {
            return null;
        }
        final StringType other = (StringType) obj;
        return other;
    }
}
