/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AEmpty extends PEmpty
{

    public AEmpty()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new AEmpty();
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEmpty(this);
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        throw new RuntimeException("Not a child.");
    }
}
