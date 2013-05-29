/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class ARealFactor extends PFactor
{
    private TRealdenotation _realdenotation_;

    public ARealFactor()
    {
        // Constructor
    }

    public ARealFactor(
        @SuppressWarnings("hiding") TRealdenotation _realdenotation_)
    {
        // Constructor
        setRealdenotation(_realdenotation_);

    }

    @Override
    public Object clone()
    {
        return new ARealFactor(
            cloneNode(this._realdenotation_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARealFactor(this);
    }

    public TRealdenotation getRealdenotation()
    {
        return this._realdenotation_;
    }

    public void setRealdenotation(TRealdenotation node)
    {
        if(this._realdenotation_ != null)
        {
            this._realdenotation_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._realdenotation_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._realdenotation_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._realdenotation_ == child)
        {
            this._realdenotation_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._realdenotation_ == oldChild)
        {
            setRealdenotation((TRealdenotation) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
