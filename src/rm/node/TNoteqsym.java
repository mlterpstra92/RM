/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.node;

import rm.analysis.*;

@SuppressWarnings("nls")
public final class TNoteqsym extends Token
{
    public TNoteqsym()
    {
        super.setText("!=");
    }

    public TNoteqsym(int line, int pos)
    {
        super.setText("!=");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNoteqsym(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNoteqsym(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TNoteqsym text.");
    }
}