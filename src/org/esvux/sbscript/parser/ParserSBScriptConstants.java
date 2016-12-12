/* Generated By:JavaCC: Do not edit this line. ParserSBScriptConstants.java */
package org.esvux.sbscript.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserSBScriptConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int PYC = 24;
  /** RegularExpression Id. */
  int T_NUM = 25;
  /** RegularExpression Id. */
  int T_STR = 26;
  /** RegularExpression Id. */
  int T_BOOL = 27;
  /** RegularExpression Id. */
  int T_VOID = 28;
  /** RegularExpression Id. */
  int PR_PRINCIPAL = 29;
  /** RegularExpression Id. */
  int PR_MOSTRAR = 30;
  /** RegularExpression Id. */
  int PR_RETORNO = 31;
  /** RegularExpression Id. */
  int PR_SI = 32;
  /** RegularExpression Id. */
  int PR_SINO = 33;
  /** RegularExpression Id. */
  int PR_SELECCIONA = 34;
  /** RegularExpression Id. */
  int PR_MIENTRAS = 35;
  /** RegularExpression Id. */
  int PR_DETENER = 36;
  /** RegularExpression Id. */
  int PR_CONTINUAR = 37;
  /** RegularExpression Id. */
  int PR_PARA = 38;
  /** RegularExpression Id. */
  int DECREMENTO = 39;
  /** RegularExpression Id. */
  int INCREMENTO = 40;
  /** RegularExpression Id. */
  int TRUE = 41;
  /** RegularExpression Id. */
  int FALSE = 42;
  /** RegularExpression Id. */
  int NUMERO = 43;
  /** RegularExpression Id. */
  int CADENA = 44;
  /** RegularExpression Id. */
  int ID = 45;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 53;
  /** RegularExpression Id. */
  int FORMAL_COMMENT = 54;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 55;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;
  /** Lexical state. */
  int IN_FORMAL_COMMENT = 2;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 3;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\"(\"",
    "\")\"",
    "\",\"",
    "\"=\"",
    "\"{\"",
    "\"}\"",
    "\":\"",
    "\"||\"",
    "\"&&\"",
    "\"\\u00bf?\"",
    "\"!\"",
    "\"==\"",
    "\"!=\"",
    "\">\"",
    "\">=\"",
    "\"<\"",
    "\"<=\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"^\"",
    "\";\"",
    "\"Num\"",
    "\"Str\"",
    "\"Bool\"",
    "\"Void\"",
    "\"Principal\"",
    "\"Mostrar\"",
    "\"Retorno\"",
    "\"Si\"",
    "\"SiNo\"",
    "\"Selecciona\"",
    "\"Mientras\"",
    "\"Detener\"",
    "\"Continuar\"",
    "\"Para\"",
    "\"--\"",
    "\"++\"",
    "\"verdadero\"",
    "\"falso\"",
    "<NUMERO>",
    "<CADENA>",
    "<ID>",
    "\" \"",
    "\"\\n\"",
    "\"\\t\"",
    "\"\\r\"",
    "\"//\"",
    "<token of kind 51>",
    "\"*/\"",
    "<SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 56>",
  };

}
