/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AListargsArgs extends PArgs
{
    private PArgs _args_;
    private TCommasym _commasym_;
    private PExpr _expr_;

    public AListargsArgs()
    {
        // Constructor
    }

    public AListargsArgs(
        @SuppressWarnings("hiding") PArgs _args_,
        @SuppressWarnings("hiding") TCommasym _commasym_,
        @SuppressWarnings("hiding") PExpr _expr_)
    {
        // Constructor
        setArgs(_args_);

        setCommasym(_commasym_);

        setExpr(_expr_);

    }

    @Override
    public Object clone()
    {
        return new AListargsArgs(
            cloneNode(this._args_),
            cloneNode(this._commasym_),
            cloneNode(this._expr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListargsArgs(this);
    }

    public PArgs getArgs()
    {
        return this._args_;
    }

    public void setArgs(PArgs node)
    {
        if(this._args_ != null)
        {
            this._args_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._args_ = node;
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

    public PExpr getExpr()
    {
        return this._expr_;
    }

    public void setExpr(PExpr node)
    {
        if(this._expr_ != null)
        {
            this._expr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._args_)
            + toString(this._commasym_)
            + toString(this._expr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._args_ == child)
        {
            this._args_ = null;
            return;
        }

        if(this._commasym_ == child)
        {
            this._commasym_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._args_ == oldChild)
        {
            setArgs((PArgs) newChild);
            return;
        }

        if(this._commasym_ == oldChild)
        {
            setCommasym((TCommasym) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
