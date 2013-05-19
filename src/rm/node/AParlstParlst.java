/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AParlstParlst extends PParlst
{
    private TLparsym _lparsym_;
    private PPars _pars_;
    private TRparsym _rparsym_;

    public AParlstParlst()
    {
        // Constructor
    }

    public AParlstParlst(
        @SuppressWarnings("hiding") TLparsym _lparsym_,
        @SuppressWarnings("hiding") PPars _pars_,
        @SuppressWarnings("hiding") TRparsym _rparsym_)
    {
        // Constructor
        setLparsym(_lparsym_);

        setPars(_pars_);

        setRparsym(_rparsym_);

    }

    @Override
    public Object clone()
    {
        return new AParlstParlst(
            cloneNode(this._lparsym_),
            cloneNode(this._pars_),
            cloneNode(this._rparsym_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAParlstParlst(this);
    }

    public TLparsym getLparsym()
    {
        return this._lparsym_;
    }

    public void setLparsym(TLparsym node)
    {
        if(this._lparsym_ != null)
        {
            this._lparsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lparsym_ = node;
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

    public TRparsym getRparsym()
    {
        return this._rparsym_;
    }

    public void setRparsym(TRparsym node)
    {
        if(this._rparsym_ != null)
        {
            this._rparsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rparsym_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lparsym_)
            + toString(this._pars_)
            + toString(this._rparsym_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lparsym_ == child)
        {
            this._lparsym_ = null;
            return;
        }

        if(this._pars_ == child)
        {
            this._pars_ = null;
            return;
        }

        if(this._rparsym_ == child)
        {
            this._rparsym_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lparsym_ == oldChild)
        {
            setLparsym((TLparsym) newChild);
            return;
        }

        if(this._pars_ == oldChild)
        {
            setPars((PPars) newChild);
            return;
        }

        if(this._rparsym_ == oldChild)
        {
            setRparsym((TRparsym) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}