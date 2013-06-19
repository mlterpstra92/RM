/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AComp extends PComp
{
    private PExpr _expr_;
    private TQuestionsym _questionsym_;

    public AComp()
    {
        // Constructor
    }

    public AComp(
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TQuestionsym _questionsym_)
    {
        // Constructor
        setExpr(_expr_);

        setQuestionsym(_questionsym_);

    }

    @Override
    public Object clone()
    {
        return new AComp(
            cloneNode(this._expr_),
            cloneNode(this._questionsym_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAComp(this);
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

    public TQuestionsym getQuestionsym()
    {
        return this._questionsym_;
    }

    public void setQuestionsym(TQuestionsym node)
    {
        if(this._questionsym_ != null)
        {
            this._questionsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._questionsym_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expr_)
            + toString(this._questionsym_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._questionsym_ == child)
        {
            this._questionsym_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._questionsym_ == oldChild)
        {
            setQuestionsym((TQuestionsym) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
