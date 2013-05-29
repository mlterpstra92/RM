/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class TLetsym extends Token
{
    public TLetsym()
    {
        super.setText("LET");
    }

    public TLetsym(int line, int pos)
    {
        super.setText("LET");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLetsym(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLetsym(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLetsym text.");
    }
}
