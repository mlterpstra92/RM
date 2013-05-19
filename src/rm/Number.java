/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rm;

/**
 *
 * @author Maarten
 */
public abstract class Number
{
    public abstract Object getValue();
    public abstract Boolean IsLessThan(Number other);
    public abstract Boolean IsLessOrEqual(Number rightNumber);
    public abstract Boolean IsGreaterThan(Number rightNumber);
    public abstract Boolean IsGreaterOrEqual(Number rightNumber);
    
    public abstract Number add(Number other);
    public abstract Number minus(Number other);
    public abstract Number times(Number other);

}