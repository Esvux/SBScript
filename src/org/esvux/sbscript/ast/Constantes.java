package org.esvux.sbscript.ast;

/**
 *
 * @author esvux
 */
public class Constantes {

    public static final int ERR_LEXICO = 0;
    public static final int ERR_SINTACTICO = 1;
    public static final int ERR_SEMANTICO = 2;
    public static final int ERR_GENERAL = 3;
    public static final String ERRORES[] = { "Léxico", "Sintáctico", "Semántico", "General" };
    

    public static final int T_NUM = 0;
    public static final int T_STR = 1;
    public static final int T_BOOL = 2;
    public static final int T_VOID = 3;
    public static final int T_ERROR = 4;
    
    public static final int NULO = -1;
    public static final int NUMERO = 0;
    public static final int CADENA = 1;
    public static final int TRUE = 2;
    public static final int FALSE = 3;
    public static final int MENOS = 4;
    public static final int VARIABLE = 5;
    public static final int LLAMADA = 6;
    public static final int ARITMETICA = 7;
    public static final int RELACIONAL = 8;
    public static final int LOGICA = 9;
    
    public static final int CUERPO = 10;
    public static final int DECLARACION = 11;
    public static final int ASIGNACION = 12;
    public static final int RETORNO = 13;
    public static final int MOSTRAR = 14;
    public static final int SI = 15;
    public static final int SELECCIONA = 16;
    public static final int CASO = 17;
    public static final int MIENTRAS = 18;
    public static final int PARA = 19;
    public static final int PARAMETRO = 20;
    
    public static final String OP_POT = "^";
    public static final String OP_MOD = "%";
    public static final String OP_DIV = "/";
    public static final String OP_MUL = "*";
    public static final String OP_RES = "-";
    public static final String OP_SUM = "+";
    
    public static final String OP_EQU = "==";
    public static final String OP_NEQ = "!=";
    public static final String OP_MAY = ">";
    public static final String OP_MYE = ">=";
    public static final String OP_MEN = "<";
    public static final String OP_MNE = "<=";
    
    public static final String OP_AND = "&&";
    public static final String OP_OR = "||";
    public static final String OP_XOR = "¿?";
    public static final String OP_NOT = "!";
    
    public static final String VAL_TRUE = "true";
    public static final String VAL_FALSE = "false";
    
    
        
}
