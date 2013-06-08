/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AComplexexprExpr extends PExpr
{
    private TIfsym _ifsym_;
    private PRelcomp _relcomp_;
    private TThensym _thensym_;
    private PExpr _trueclause_;
    private TElsesym _elsesym_;
    private PExpr _falseclause_;

    public AComplexexprExpr()
    {
        // Constructor
    }

    public AComplexexprExpr(
        @SuppressWarnings("hiding") TIfsym _ifsym_,
        @SuppressWarnings("hiding") PRelcomp _relcomp_,
        @SuppressWarnings("hiding") TThensym _thensym_,
        @SuppressWarnings("hiding") PExpr _trueclause_,
        @SuppressWarnings("hiding") TElsesym _elsesym_,
        @SuppressWarnings("hiding") PExpr _falseclause_)
    {
        // Constructor
        setIfsym(_ifsym_);

        setRelcomp(_relcomp_);

        setThensym(_thensym_);

        setTrueclause(_trueclause_);

        setElsesym(_elsesym_);

        setFalseclause(_falseclause_);

    }

    @Override
    public Object clone()
    {
        return new AComplexexprExpr(
            cloneNode(this._ifsym_),
            cloneNode(this._relcomp_),
            cloneNode(this._thensym_),
            cloneNode(this._trueclause_),
            cloneNode(this._elsesym_),
            cloneNode(this._falseclause_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAComplexexprExpr(this);
    }

    public TIfsym getIfsym()
    {
        return this._ifsym_;
    }

    public void setIfsym(TIfsym node)
    {
        if(this._ifsym_ != null)
        {
            this._ifsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ifsym_ = node;
    }

    public PRelcomp getRelcomp()
    {
        return this._relcomp_;
    }

    public void setRelcomp(PRelcomp node)
    {
        if(this._relcomp_ != null)
        {
            this._relcomp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._relcomp_ = node;
    }

    public TThensym getThensym()
    {
        return this._thensym_;
    }

    public void setThensym(TThensym node)
    {
        if(this._thensym_ != null)
        {
            this._thensym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._thensym_ = node;
    }

    public PExpr getTrueclause()
    {
        return this._trueclause_;
    }

    public void setTrueclause(PExpr node)
    {
        if(this._trueclause_ != null)
        {
            this._trueclause_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._trueclause_ = node;
    }

    public TElsesym getElsesym()
    {
        return this._elsesym_;
    }

    public void setElsesym(TElsesym node)
    {
        if(this._elsesym_ != null)
        {
            this._elsesym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._elsesym_ = node;
    }

    public PExpr getFalseclause()
    {
        return this._falseclause_;
    }

    public void setFalseclause(PExpr node)
    {
        if(this._falseclause_ != null)
        {
            this._falseclause_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._falseclause_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ifsym_)
            + toString(this._relcomp_)
            + toString(this._thensym_)
            + toString(this._trueclause_)
            + toString(this._elsesym_)
            + toString(this._falseclause_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ifsym_ == child)
        {
            this._ifsym_ = null;
            return;
        }

        if(this._relcomp_ == child)
        {
            this._relcomp_ = null;
            return;
        }

        if(this._thensym_ == child)
        {
            this._thensym_ = null;
            return;
        }

        if(this._trueclause_ == child)
        {
            this._trueclause_ = null;
            return;
        }

        if(this._elsesym_ == child)
        {
            this._elsesym_ = null;
            return;
        }

        if(this._falseclause_ == child)
        {
            this._falseclause_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ifsym_ == oldChild)
        {
            setIfsym((TIfsym) newChild);
            return;
        }

        if(this._relcomp_ == oldChild)
        {
            setRelcomp((PRelcomp) newChild);
            return;
        }

        if(this._thensym_ == oldChild)
        {
            setThensym((TThensym) newChild);
            return;
        }

        if(this._trueclause_ == oldChild)
        {
            setTrueclause((PExpr) newChild);
            return;
        }

        if(this._elsesym_ == oldChild)
        {
            setElsesym((TElsesym) newChild);
            return;
        }

        if(this._falseclause_ == oldChild)
        {
            setFalseclause((PExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
