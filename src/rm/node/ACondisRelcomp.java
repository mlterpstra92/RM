/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class ACondisRelcomp extends PRelcomp
{
    private PRelcomp _p1_;
    private TCondis _condis_;
    private PRelexpr _p2_;

    public ACondisRelcomp()
    {
        // Constructor
    }

    public ACondisRelcomp(
        @SuppressWarnings("hiding") PRelcomp _p1_,
        @SuppressWarnings("hiding") TCondis _condis_,
        @SuppressWarnings("hiding") PRelexpr _p2_)
    {
        // Constructor
        setP1(_p1_);

        setCondis(_condis_);

        setP2(_p2_);

    }

    @Override
    public Object clone()
    {
        return new ACondisRelcomp(
            cloneNode(this._p1_),
            cloneNode(this._condis_),
            cloneNode(this._p2_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACondisRelcomp(this);
    }

    public PRelcomp getP1()
    {
        return this._p1_;
    }

    public void setP1(PRelcomp node)
    {
        if(this._p1_ != null)
        {
            this._p1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._p1_ = node;
    }

    public TCondis getCondis()
    {
        return this._condis_;
    }

    public void setCondis(TCondis node)
    {
        if(this._condis_ != null)
        {
            this._condis_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._condis_ = node;
    }

    public PRelexpr getP2()
    {
        return this._p2_;
    }

    public void setP2(PRelexpr node)
    {
        if(this._p2_ != null)
        {
            this._p2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._p2_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._p1_)
            + toString(this._condis_)
            + toString(this._p2_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._p1_ == child)
        {
            this._p1_ = null;
            return;
        }

        if(this._condis_ == child)
        {
            this._condis_ = null;
            return;
        }

        if(this._p2_ == child)
        {
            this._p2_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._p1_ == oldChild)
        {
            setP1((PRelcomp) newChild);
            return;
        }

        if(this._condis_ == oldChild)
        {
            setCondis((TCondis) newChild);
            return;
        }

        if(this._p2_ == oldChild)
        {
            setP2((PRelexpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}