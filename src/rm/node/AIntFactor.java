/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AIntFactor extends PFactor
{
    private TIntdenotation _intdenotation_;

    public AIntFactor()
    {
        // Constructor
    }

    public AIntFactor(
        @SuppressWarnings("hiding") TIntdenotation _intdenotation_)
    {
        // Constructor
        setIntdenotation(_intdenotation_);

    }

    @Override
    public Object clone()
    {
        return new AIntFactor(
            cloneNode(this._intdenotation_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIntFactor(this);
    }

    public TIntdenotation getIntdenotation()
    {
        return this._intdenotation_;
    }

    public void setIntdenotation(TIntdenotation node)
    {
        if(this._intdenotation_ != null)
        {
            this._intdenotation_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._intdenotation_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._intdenotation_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._intdenotation_ == child)
        {
            this._intdenotation_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._intdenotation_ == oldChild)
        {
            setIntdenotation((TIntdenotation) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
