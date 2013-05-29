/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class ACommaPars extends PPars
{
    private PPars _pars_;
    private TCommasym _commasym_;
    private TIdent _ident_;

    public ACommaPars()
    {
        // Constructor
    }

    public ACommaPars(
        @SuppressWarnings("hiding") PPars _pars_,
        @SuppressWarnings("hiding") TCommasym _commasym_,
        @SuppressWarnings("hiding") TIdent _ident_)
    {
        // Constructor
        setPars(_pars_);

        setCommasym(_commasym_);

        setIdent(_ident_);

    }

    @Override
    public Object clone()
    {
        return new ACommaPars(
            cloneNode(this._pars_),
            cloneNode(this._commasym_),
            cloneNode(this._ident_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACommaPars(this);
    }

    public PPars getPars()
    {
        return this._pars_;
    }

    public void setPars(PPars node)
    {
        if(this._pars_ != null)
        {
            this._pars_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pars_ = node;
    }

    public TCommasym getCommasym()
    {
        return this._commasym_;
    }

    public void setCommasym(TCommasym node)
    {
        if(this._commasym_ != null)
        {
            this._commasym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._commasym_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._pars_)
            + toString(this._commasym_)
            + toString(this._ident_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._pars_ == child)
        {
            this._pars_ = null;
            return;
        }

        if(this._commasym_ == child)
        {
            this._commasym_ = null;
            return;
        }

        if(this._ident_ == child)
        {
            this._ident_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._pars_ == oldChild)
        {
            setPars((PPars) newChild);
            return;
        }

        if(this._commasym_ == oldChild)
        {
            setCommasym((TCommasym) newChild);
            return;
        }

        if(this._ident_ == oldChild)
        {
            setIdent((TIdent) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
