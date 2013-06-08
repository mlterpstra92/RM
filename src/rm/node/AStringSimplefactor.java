/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AStringSimplefactor extends PSimplefactor
{
    private TStringsym _stringsym_;

    public AStringSimplefactor()
    {
        // Constructor
    }

    public AStringSimplefactor(
        @SuppressWarnings("hiding") TStringsym _stringsym_)
    {
        // Constructor
        setStringsym(_stringsym_);

    }

    @Override
    public Object clone()
    {
        return new AStringSimplefactor(
            cloneNode(this._stringsym_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStringSimplefactor(this);
    }

    public TStringsym getStringsym()
    {
        return this._stringsym_;
    }

    public void setStringsym(TStringsym node)
    {
        if(this._stringsym_ != null)
        {
            this._stringsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._stringsym_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._stringsym_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._stringsym_ == child)
        {
            this._stringsym_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._stringsym_ == oldChild)
        {
            setStringsym((TStringsym) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
