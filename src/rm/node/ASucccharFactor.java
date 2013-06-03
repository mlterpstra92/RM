/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class ASucccharFactor extends PFactor
{
    private TSuccsym _succsym_;
    private PFactor _factor_;

    public ASucccharFactor()
    {
        // Constructor
    }

    public ASucccharFactor(
        @SuppressWarnings("hiding") TSuccsym _succsym_,
        @SuppressWarnings("hiding") PFactor _factor_)
    {
        // Constructor
        setSuccsym(_succsym_);

        setFactor(_factor_);

    }

    @Override
    public Object clone()
    {
        return new ASucccharFactor(
            cloneNode(this._succsym_),
            cloneNode(this._factor_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASucccharFactor(this);
    }

    public TSuccsym getSuccsym()
    {
        return this._succsym_;
    }

    public void setSuccsym(TSuccsym node)
    {
        if(this._succsym_ != null)
        {
            this._succsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._succsym_ = node;
    }

    public PFactor getFactor()
    {
        return this._factor_;
    }

    public void setFactor(PFactor node)
    {
        if(this._factor_ != null)
        {
            this._factor_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._factor_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._succsym_)
            + toString(this._factor_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._succsym_ == child)
        {
            this._succsym_ = null;
            return;
        }

        if(this._factor_ == child)
        {
            this._factor_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._succsym_ == oldChild)
        {
            setSuccsym((TSuccsym) newChild);
            return;
        }

        if(this._factor_ == oldChild)
        {
            setFactor((PFactor) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}