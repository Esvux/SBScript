package org.esvux.sbscript.interprete;

import org.esvux.sbscript.ast.Constantes;

/**
 *
 * @author esvux
 */
public abstract class FabricaResultado {

    public static Resultado creaOK() {
        return new Resultado(Constantes.VAL_OK, Constantes.T_VOID);
    }

    public static Resultado creaFAIL() {
        return new Resultado(Constantes.VAL_FAIL, Constantes.T_ERROR);
    }

    public static Resultado creaRetorno(String valor, int tipo) {
        return new Resultado(valor, tipo, true);
    }

    public static Resultado creaDetener() {
        return new Resultado("", Constantes.T_DETENER);
    }
    
    public static Resultado creaContinuar() {
        return new Resultado("", Constantes.T_CONTINUAR);
    }
    
    public static Resultado creaCadena(String cadena) {
        return new Resultado(cadena, Constantes.T_STR);
    }

    public static Resultado creaNumero(String cadena) {
        return new Resultado(cadena, Constantes.T_NUM);
    }

    public static Resultado creaBooleano(boolean bool) {
        Resultado res = new Resultado("", Constantes.T_BOOL);
        res.setBooleano(bool);
        return res;
    }

}
