/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AMonadexprSimplefactor extends PSimplefactor
{
    private PMonadexpr _monadexpr_;

    public AMonadexprSimplefactor()
    {
        // Constructor
    }

    public AMonadexprSimplefactor(
        @SuppressWarnings("hiding") PMonadexpr _monadexpr_)
    {
        // Constructor
        setMonadexpr(_monadexpr_);

    }

    @Override
    public Object clone()
    {
        return new AMonadexprSimplefactor(
            cloneNode(this._monadexpr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMonadexprSimplefactor(this);
    }

    public PMonadexpr getMonadexpr()
    {
        return this._monadexpr_;
    }

    public void setMonadexpr(PMonadexpr node)
    {
        if(this._monadexpr_ != null)
        {
            this._monadexpr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._monadexpr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._monadexpr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._monadexpr_ == child)
        {
            this._monadexpr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._monadexpr_ == oldChild)
        {
            setMonadexpr((PMonadexpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
