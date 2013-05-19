/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AIdentFactor extends PFactor
{
    private TIdent _ident_;
    private PArglst _arglst_;

    public AIdentFactor()
    {
        // Constructor
    }

    public AIdentFactor(
        @SuppressWarnings("hiding") TIdent _ident_,
        @SuppressWarnings("hiding") PArglst _arglst_)
    {
        // Constructor
        setIdent(_ident_);

        setArglst(_arglst_);

    }

    @Override
    public Object clone()
    {
        return new AIdentFactor(
            cloneNode(this._ident_),
            cloneNode(this._arglst_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIdentFactor(this);
    }

    public TIdent getIdent()
    {
        return this._ident_;
    }

    public void setIdent(TIdent node)
    {
        if(this._ident_ != null)
        {
            this._ident_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ident_ = node;
    }

    public PArglst getArglst()
    {
        return this._arglst_;
    }

    public void setArglst(PArglst node)
    {
        if(this._arglst_ != null)
        {
            this._arglst_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._arglst_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ident_)
            + toString(this._arglst_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ident_ == child)
        {
            this._ident_ = null;
            return;
        }

        if(this._arglst_ == child)
        {
            this._arglst_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ident_ == oldChild)
        {
            setIdent((TIdent) newChild);
            return;
        }

        if(this._arglst_ == oldChild)
        {
            setArglst((PArglst) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}