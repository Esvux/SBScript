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
    public static final int ARITMETICA_UNARIA = 8;
    public static final int RELACIONAL = 9;
    public static final int LOGICA = 10;
    public static final int LOGICA_UNARIA = 10;
    
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
    
    //OPA = OPerador Aritmético
    public static final int OPA_POT = 0;
    public static final int OPA_MOD = 1;
    public static final int OPA_DIV = 2;
    public static final int OPA_MUL = 3;
    public static final int OPA_RES = 4;
    public static final int OPA_SUM = 5;
    public static final int OPA_MEN = 6;
    
    //OPR = OPerador Relacional
    public static final int OPR_EQU = 7;
    public static final int OPR_NEQ = 8;
    public static final int OPR_MAY = 9;
    public static final int OPR_MYE = 10;
    public static final int OPR_MEN = 11;
    public static final int OPR_MNE = 12;
    
    //OPL = OPerador Lógico
    public static final int OPL_AND = 13;
    public static final int OPL_OR  = 14;
    public static final int OPL_XOR = 15;
    public static final int OPL_NOT = 16;
        
    public static final String VAL_TRUE = "verdadero";
    public static final String VAL_FALSE = "falso";
    
    
        
}
