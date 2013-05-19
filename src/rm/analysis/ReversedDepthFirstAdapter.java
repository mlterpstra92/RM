/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.analysis;

import java.util.*;
import rm.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPProgram().apply(this);
        outStart(node);
    }

    public void inADefProgram(ADefProgram node)
    {
        defaultIn(node);
    }

    public void outADefProgram(ADefProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADefProgram(ADefProgram node)
    {
        inADefProgram(node);
        if(node.getProgram() != null)
        {
            node.getProgram().apply(this);
        }
        if(node.getDef() != null)
        {
            node.getDef().apply(this);
        }
        outADefProgram(node);
    }

    public void inACompProgram(ACompProgram node)
    {
        defaultIn(node);
    }

    public void outACompProgram(ACompProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACompProgram(ACompProgram node)
    {
        inACompProgram(node);
        if(node.getProgram() != null)
        {
            node.getProgram().apply(this);
        }
        if(node.getComp() != null)
        {
            node.getComp().apply(this);
        }
        outACompProgram(node);
    }

    public void inAEndProgram(AEndProgram node)
    {
        defaultIn(node);
    }

    public void outAEndProgram(AEndProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEndProgram(AEndProgram node)
    {
        inAEndProgram(node);
        if(node.getEndsym() != null)
        {
            node.getEndsym().apply(this);
        }
        outAEndProgram(node);
    }

    public void inADef(ADef node)
    {
        defaultIn(node);
    }

    public void outADef(ADef node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADef(ADef node)
    {
        inADef(node);
        if(node.getSemicolonsym() != null)
        {
            node.getSemicolonsym().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getAssignsym() != null)
        {
            node.getAssignsym().apply(this);
        }
        if(node.getParlst() != null)
        {
            node.getParlst().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getLetsym() != null)
        {
            node.getLetsym().apply(this);
        }
        outADef(node);
    }

    public void inAComp(AComp node)
    {
        defaultIn(node);
    }

    public void outAComp(AComp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAComp(AComp node)
    {
        inAComp(node);
        if(node.getQuestionsym() != null)
        {
            node.getQuestionsym().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAComp(node);
    }

    public void inAParlstParlst(AParlstParlst node)
    {
        defaultIn(node);
    }

    public void outAParlstParlst(AParlstParlst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParlstParlst(AParlstParlst node)
    {
        inAParlstParlst(node);
        if(node.getRparsym() != null)
        {
            node.getRparsym().apply(this);
        }
        if(node.getPars() != null)
        {
            node.getPars().apply(this);
        }
        if(node.getLparsym() != null)
        {
            node.getLparsym().apply(this);
        }
        outAParlstParlst(node);
    }

    public void inAEmptyParlst(AEmptyParlst node)
    {
        defaultIn(node);
    }

    public void outAEmptyParlst(AEmptyParlst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyParlst(AEmptyParlst node)
    {
        inAEmptyParlst(node);
        if(node.getEmpty() != null)
        {
            node.getEmpty().apply(this);
        }
        outAEmptyParlst(node);
    }

    public void inAIdentPars(AIdentPars node)
    {
        defaultIn(node);
    }

    public void outAIdentPars(AIdentPars node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdentPars(AIdentPars node)
    {
        inAIdentPars(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAIdentPars(node);
    }

    public void inACommaPars(ACommaPars node)
    {
        defaultIn(node);
    }

    public void outACommaPars(ACommaPars node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACommaPars(ACommaPars node)
    {
        inACommaPars(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getCommasym() != null)
        {
            node.getCommasym().apply(this);
        }
        if(node.getPars() != null)
        {
            node.getPars().apply(this);
        }
        outACommaPars(node);
    }

    public void inAComplexexprExpr(AComplexexprExpr node)
    {
        defaultIn(node);
    }

    public void outAComplexexprExpr(AComplexexprExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAComplexexprExpr(AComplexexprExpr node)
    {
        inAComplexexprExpr(node);
        if(node.getFalseclause() != null)
        {
            node.getFalseclause().apply(this);
        }
        if(node.getElsesym() != null)
        {
            node.getElsesym().apply(this);
        }
        if(node.getTrueclause() != null)
        {
            node.getTrueclause().apply(this);
        }
        if(node.getThensym() != null)
        {
            node.getThensym().apply(this);
        }
        if(node.getRelcomp() != null)
        {
            node.getRelcomp().apply(this);
        }
        if(node.getIfsym() != null)
        {
            node.getIfsym().apply(this);
        }
        outAComplexexprExpr(node);
    }

    public void inASimpleexprExpr(ASimpleexprExpr node)
    {
        defaultIn(node);
    }

    public void outASimpleexprExpr(ASimpleexprExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleexprExpr(ASimpleexprExpr node)
    {
        inASimpleexprExpr(node);
        if(node.getSmplexpr() != null)
        {
            node.getSmplexpr().apply(this);
        }
        outASimpleexprExpr(node);
    }

    public void inACondisRelcomp(ACondisRelcomp node)
    {
        defaultIn(node);
    }

    public void outACondisRelcomp(ACondisRelcomp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACondisRelcomp(ACondisRelcomp node)
    {
        inACondisRelcomp(node);
        if(node.getP2() != null)
        {
            node.getP2().apply(this);
        }
        if(node.getCondis() != null)
        {
            node.getCondis().apply(this);
        }
        if(node.getP1() != null)
        {
            node.getP1().apply(this);
        }
        outACondisRelcomp(node);
    }

    public void inACondRelcomp(ACondRelcomp node)
    {
        defaultIn(node);
    }

    public void outACondRelcomp(ACondRelcomp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACondRelcomp(ACondRelcomp node)
    {
        inACondRelcomp(node);
        if(node.getRelexpr() != null)
        {
            node.getRelexpr().apply(this);
        }
        outACondRelcomp(node);
    }

    public void inARelexpr(ARelexpr node)
    {
        defaultIn(node);
    }

    public void outARelexpr(ARelexpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARelexpr(ARelexpr node)
    {
        inARelexpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getRelop() != null)
        {
            node.getRelop().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outARelexpr(node);
    }

    public void inATermSmplexpr(ATermSmplexpr node)
    {
        defaultIn(node);
    }

    public void outATermSmplexpr(ATermSmplexpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATermSmplexpr(ATermSmplexpr node)
    {
        inATermSmplexpr(node);
        if(node.getTerm() != null)
        {
            node.getTerm().apply(this);
        }
        outATermSmplexpr(node);
    }

    public void inAAddSmplexpr(AAddSmplexpr node)
    {
        defaultIn(node);
    }

    public void outAAddSmplexpr(AAddSmplexpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddSmplexpr(AAddSmplexpr node)
    {
        inAAddSmplexpr(node);
        if(node.getTerm() != null)
        {
            node.getTerm().apply(this);
        }
        if(node.getAddop() != null)
        {
            node.getAddop().apply(this);
        }
        if(node.getSmplexpr() != null)
        {
            node.getSmplexpr().apply(this);
        }
        outAAddSmplexpr(node);
    }

    public void inAFactorTerm(AFactorTerm node)
    {
        defaultIn(node);
    }

    public void outAFactorTerm(AFactorTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFactorTerm(AFactorTerm node)
    {
        inAFactorTerm(node);
        if(node.getFactor() != null)
        {
            node.getFactor().apply(this);
        }
        outAFactorTerm(node);
    }

    public void inAMultTerm(AMultTerm node)
    {
        defaultIn(node);
    }

    public void outAMultTerm(AMultTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultTerm(AMultTerm node)
    {
        inAMultTerm(node);
        if(node.getFactor() != null)
        {
            node.getFactor().apply(this);
        }
        if(node.getMulop() != null)
        {
            node.getMulop().apply(this);
        }
        if(node.getTerm() != null)
        {
            node.getTerm().apply(this);
        }
        outAMultTerm(node);
    }

    public void inAParFactor(AParFactor node)
    {
        defaultIn(node);
    }

    public void outAParFactor(AParFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParFactor(AParFactor node)
    {
        inAParFactor(node);
        if(node.getRparsym() != null)
        {
            node.getRparsym().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getLparsym() != null)
        {
            node.getLparsym().apply(this);
        }
        outAParFactor(node);
    }

    public void inAIntFactor(AIntFactor node)
    {
        defaultIn(node);
    }

    public void outAIntFactor(AIntFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntFactor(AIntFactor node)
    {
        inAIntFactor(node);
        if(node.getIntdenotation() != null)
        {
            node.getIntdenotation().apply(this);
        }
        outAIntFactor(node);
    }

    public void inARealFactor(ARealFactor node)
    {
        defaultIn(node);
    }

    public void outARealFactor(ARealFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARealFactor(ARealFactor node)
    {
        inARealFactor(node);
        if(node.getRealdenotation() != null)
        {
            node.getRealdenotation().apply(this);
        }
        outARealFactor(node);
    }

    public void inAIdentFactor(AIdentFactor node)
    {
        defaultIn(node);
    }

    public void outAIdentFactor(AIdentFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdentFactor(AIdentFactor node)
    {
        inAIdentFactor(node);
        if(node.getArglst() != null)
        {
            node.getArglst().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAIdentFactor(node);
    }

    public void inAArgsArglst(AArgsArglst node)
    {
        defaultIn(node);
    }

    public void outAArgsArglst(AArgsArglst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArgsArglst(AArgsArglst node)
    {
        inAArgsArglst(node);
        if(node.getRparsym() != null)
        {
            node.getRparsym().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        if(node.getLparsym() != null)
        {
            node.getLparsym().apply(this);
        }
        outAArgsArglst(node);
    }

    public void inAEmptyArglst(AEmptyArglst node)
    {
        defaultIn(node);
    }

    public void outAEmptyArglst(AEmptyArglst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyArglst(AEmptyArglst node)
    {
        inAEmptyArglst(node);
        if(node.getEmpty() != null)
        {
            node.getEmpty().apply(this);
        }
        outAEmptyArglst(node);
    }

    public void inAExprArgs(AExprArgs node)
    {
        defaultIn(node);
    }

    public void outAExprArgs(AExprArgs node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprArgs(AExprArgs node)
    {
        inAExprArgs(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAExprArgs(node);
    }

    public void inAListargsArgs(AListargsArgs node)
    {
        defaultIn(node);
    }

    public void outAListargsArgs(AListargsArgs node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListargsArgs(AListargsArgs node)
    {
        inAListargsArgs(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getCommasym() != null)
        {
            node.getCommasym().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        outAListargsArgs(node);
    }

    public void inAPlusAddop(APlusAddop node)
    {
        defaultIn(node);
    }

    public void outAPlusAddop(APlusAddop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusAddop(APlusAddop node)
    {
        inAPlusAddop(node);
        if(node.getPlussym() != null)
        {
            node.getPlussym().apply(this);
        }
        outAPlusAddop(node);
    }

    public void inAMinusAddop(AMinusAddop node)
    {
        defaultIn(node);
    }

    public void outAMinusAddop(AMinusAddop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusAddop(AMinusAddop node)
    {
        inAMinusAddop(node);
        if(node.getMinussym() != null)
        {
            node.getMinussym().apply(this);
        }
        outAMinusAddop(node);
    }

    public void inAMultMulop(AMultMulop node)
    {
        defaultIn(node);
    }

    public void outAMultMulop(AMultMulop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultMulop(AMultMulop node)
    {
        inAMultMulop(node);
        if(node.getTimessym() != null)
        {
            node.getTimessym().apply(this);
        }
        outAMultMulop(node);
    }

    public void inAIntegerdivMulop(AIntegerdivMulop node)
    {
        defaultIn(node);
    }

    public void outAIntegerdivMulop(AIntegerdivMulop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntegerdivMulop(AIntegerdivMulop node)
    {
        inAIntegerdivMulop(node);
        if(node.getIdivsym() != null)
        {
            node.getIdivsym().apply(this);
        }
        outAIntegerdivMulop(node);
    }

    public void inAIntegermodMulop(AIntegermodMulop node)
    {
        defaultIn(node);
    }

    public void outAIntegermodMulop(AIntegermodMulop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntegermodMulop(AIntegermodMulop node)
    {
        inAIntegermodMulop(node);
        if(node.getImodsym() != null)
        {
            node.getImodsym().apply(this);
        }
        outAIntegermodMulop(node);
    }

    public void inARealdivMulop(ARealdivMulop node)
    {
        defaultIn(node);
    }

    public void outARealdivMulop(ARealdivMulop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARealdivMulop(ARealdivMulop node)
    {
        inARealdivMulop(node);
        if(node.getRdivsym() != null)
        {
            node.getRdivsym().apply(this);
        }
        outARealdivMulop(node);
    }

    public void inALshiftMulop(ALshiftMulop node)
    {
        defaultIn(node);
    }

    public void outALshiftMulop(ALshiftMulop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALshiftMulop(ALshiftMulop node)
    {
        inALshiftMulop(node);
        if(node.getLshiftsym() != null)
        {
            node.getLshiftsym().apply(this);
        }
        outALshiftMulop(node);
    }

    public void inARshiftMulop(ARshiftMulop node)
    {
        defaultIn(node);
    }

    public void outARshiftMulop(ARshiftMulop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARshiftMulop(ARshiftMulop node)
    {
        inARshiftMulop(node);
        if(node.getRshiftsym() != null)
        {
            node.getRshiftsym().apply(this);
        }
        outARshiftMulop(node);
    }

    public void inALessthanRelop(ALessthanRelop node)
    {
        defaultIn(node);
    }

    public void outALessthanRelop(ALessthanRelop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALessthanRelop(ALessthanRelop node)
    {
        inALessthanRelop(node);
        if(node.getLesssym() != null)
        {
            node.getLesssym().apply(this);
        }
        outALessthanRelop(node);
    }

    public void inALessorequalRelop(ALessorequalRelop node)
    {
        defaultIn(node);
    }

    public void outALessorequalRelop(ALessorequalRelop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALessorequalRelop(ALessorequalRelop node)
    {
        inALessorequalRelop(node);
        if(node.getLseqsym() != null)
        {
            node.getLseqsym().apply(this);
        }
        outALessorequalRelop(node);
    }

    public void inAEqualRelop(AEqualRelop node)
    {
        defaultIn(node);
    }

    public void outAEqualRelop(AEqualRelop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqualRelop(AEqualRelop node)
    {
        inAEqualRelop(node);
        if(node.getEqualsym() != null)
        {
            node.getEqualsym().apply(this);
        }
        outAEqualRelop(node);
    }

    public void inANotequalRelop(ANotequalRelop node)
    {
        defaultIn(node);
    }

    public void outANotequalRelop(ANotequalRelop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotequalRelop(ANotequalRelop node)
    {
        inANotequalRelop(node);
        if(node.getNoteqsym() != null)
        {
            node.getNoteqsym().apply(this);
        }
        outANotequalRelop(node);
    }

    public void inAGreaterequalRelop(AGreaterequalRelop node)
    {
        defaultIn(node);
    }

    public void outAGreaterequalRelop(AGreaterequalRelop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGreaterequalRelop(AGreaterequalRelop node)
    {
        inAGreaterequalRelop(node);
        if(node.getGreqsym() != null)
        {
            node.getGreqsym().apply(this);
        }
        outAGreaterequalRelop(node);
    }

    public void inAGreaterRelop(AGreaterRelop node)
    {
        defaultIn(node);
    }

    public void outAGreaterRelop(AGreaterRelop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGreaterRelop(AGreaterRelop node)
    {
        inAGreaterRelop(node);
        if(node.getGrtrsym() != null)
        {
            node.getGrtrsym().apply(this);
        }
        outAGreaterRelop(node);
    }

    public void inAEmpty(AEmpty node)
    {
        defaultIn(node);
    }

    public void outAEmpty(AEmpty node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmpty(AEmpty node)
    {
        inAEmpty(node);
        outAEmpty(node);
    }
}
