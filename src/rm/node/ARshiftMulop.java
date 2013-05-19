/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class ARshiftMulop extends PMulop
{
    private TRshiftsym _rshiftsym_;

    public ARshiftMulop()
    {
        // Constructor
    }

    public ARshiftMulop(
        @SuppressWarnings("hiding") TRshiftsym _rshiftsym_)
    {
        // Constructor
        setRshiftsym(_rshiftsym_);

    }

    @Override
    public Object clone()
    {
        return new ARshiftMulop(
            cloneNode(this._rshiftsym_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARshiftMulop(this);
    }

    public TRshiftsym getRshiftsym()
    {
        return this._rshiftsym_;
    }

    public void setRshiftsym(TRshiftsym node)
    {
        if(this._rshiftsym_ != null)
        {
            this._rshiftsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rshiftsym_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._rshiftsym_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._rshiftsym_ == child)
        {
            this._rshiftsym_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._rshiftsym_ == oldChild)
        {
            setRshiftsym((TRshiftsym) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}