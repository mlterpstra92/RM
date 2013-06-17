/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm.types;

/**
 *
 * @author Maarten
 * Type class: Define the operations that all types must be able to execute 
 */
public abstract class Type
{
    public abstract Object getValue();
    public abstract Boolean IsLessThan(Type other);
    public abstract Boolean IsLessOrEqual(Type rightNumber);
    public abstract Boolean IsGreaterThan(Type rightNumber);
    public abstract Boolean IsGreaterOrEqual(Type rightNumber);
    
    public abstract Type add(Type other);
    public abstract Type minus(Type other);
    public abstract Type times(Type other);
    public abstract Type div(Type rightNumber);
    public abstract void negate();
}