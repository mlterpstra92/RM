/* This file was generated by SableCC (http://www.sablecc.org/). */

package rm.analysis;

import rm.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseADefProgram(ADefProgram node);
    void caseACompProgram(ACompProgram node);
    void caseAEndProgram(AEndProgram node);
    void caseADef(ADef node);
    void caseAComp(AComp node);
    void caseAParlstParlst(AParlstParlst node);
    void caseAEmptyParlst(AEmptyParlst node);
    void caseAIdentPars(AIdentPars node);
    void caseACommaPars(ACommaPars node);
    void caseAComplexexprExpr(AComplexexprExpr node);
    void caseASimpleexprExpr(ASimpleexprExpr node);
    void caseACondisRelcomp(ACondisRelcomp node);
    void caseACondRelcomp(ACondRelcomp node);
    void caseARelexpr(ARelexpr node);
    void caseATermSmplexpr(ATermSmplexpr node);
    void caseAAddSmplexpr(AAddSmplexpr node);
    void caseAFactorTerm(AFactorTerm node);
    void caseAMultTerm(AMultTerm node);
    void caseAParFactor(AParFactor node);
    void caseAIntFactor(AIntFactor node);
    void caseARealFactor(ARealFactor node);
    void caseAIdentFactor(AIdentFactor node);
    void caseAArgsArglst(AArgsArglst node);
    void caseAEmptyArglst(AEmptyArglst node);
    void caseAExprArgs(AExprArgs node);
    void caseAListargsArgs(AListargsArgs node);
    void caseAPlusAddop(APlusAddop node);
    void caseAMinusAddop(AMinusAddop node);
    void caseAMultMulop(AMultMulop node);
    void caseAIntegerdivMulop(AIntegerdivMulop node);
    void caseAIntegermodMulop(AIntegermodMulop node);
    void caseARealdivMulop(ARealdivMulop node);
    void caseALshiftMulop(ALshiftMulop node);
    void caseARshiftMulop(ARshiftMulop node);
    void caseALessthanRelop(ALessthanRelop node);
    void caseALessorequalRelop(ALessorequalRelop node);
    void caseAEqualRelop(AEqualRelop node);
    void caseANotequalRelop(ANotequalRelop node);
    void caseAGreaterequalRelop(AGreaterequalRelop node);
    void caseAGreaterRelop(AGreaterRelop node);
    void caseAEmpty(AEmpty node);

    void caseTBlank(TBlank node);
    void caseTPlussym(TPlussym node);
    void caseTMinussym(TMinussym node);
    void caseTTimessym(TTimessym node);
    void caseTImodsym(TImodsym node);
    void caseTRdivsym(TRdivsym node);
    void caseTIdivsym(TIdivsym node);
    void caseTLesssym(TLesssym node);
    void caseTLseqsym(TLseqsym node);
    void caseTNoteqsym(TNoteqsym node);
    void caseTGreqsym(TGreqsym node);
    void caseTGrtrsym(TGrtrsym node);
    void caseTLshiftsym(TLshiftsym node);
    void caseTRshiftsym(TRshiftsym node);
    void caseTCondis(TCondis node);
    void caseTIfsym(TIfsym node);
    void caseTThensym(TThensym node);
    void caseTElsesym(TElsesym node);
    void caseTLetsym(TLetsym node);
    void caseTLparsym(TLparsym node);
    void caseTRparsym(TRparsym node);
    void caseTEndsym(TEndsym node);
    void caseTCommasym(TCommasym node);
    void caseTSemicolonsym(TSemicolonsym node);
    void caseTEqualsym(TEqualsym node);
    void caseTAssignsym(TAssignsym node);
    void caseTQuestionsym(TQuestionsym node);
    void caseTIntdenotation(TIntdenotation node);
    void caseTRealdenotation(TRealdenotation node);
    void caseTIdent(TIdent node);
    void caseTComment(TComment node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}