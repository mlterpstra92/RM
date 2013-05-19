/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class AIntegermodMulop extends PMulop
{
    private TImodsym _imodsym_;

    public AIntegermodMulop()
    {
        // Constructor
    }

    public AIntegermodMulop(
        @SuppressWarnings("hiding") TImodsym _imodsym_)
    {
        // Constructor
        setImodsym(_imodsym_);

    }

    @Override
    public Object clone()
    {
        return new AIntegermodMulop(
            cloneNode(this._imodsym_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIntegermodMulop(this);
    }

    public TImodsym getImodsym()
    {
        return this._imodsym_;
    }

    public void setImodsym(TImodsym node)
    {
        if(this._imodsym_ != null)
        {
            this._imodsym_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._imodsym_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._imodsym_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._imodsym_ == child)
        {
            this._imodsym_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._imodsym_ == oldChild)
        {
            setImodsym((TImodsym) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
