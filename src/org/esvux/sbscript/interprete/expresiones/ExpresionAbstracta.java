package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Ambito;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public abstract class ExpresionAbstracta {

    protected Nodo izq;
    protected Nodo der;
    protected int operando;

    public ExpresionAbstracta(Nodo izq, Nodo der, int operando) {
        this.izq = izq;
        this.der = der;
        this.operando = operando;
    }

    public abstract Resultado resolver(Ambito ctx);

    protected int validarTipos(int tipoIzq, int tipoDer, int[][] matrizDeTipos) {
        if (tipoIzq < 0 && tipoIzq >= matrizDeTipos.length) {
            return Constantes.T_ERROR;
        }
        if (tipoDer < 0 && tipoDer >= matrizDeTipos[0].length) {
            return Constantes.T_ERROR;
        }
        return matrizDeTipos[tipoIzq][tipoDer];
    }

    protected boolean convertirBooleano(String str) {
        if (str.compareTo(Constantes.VAL_TRUE) == 0) {
            return true;
        }
        return false;
    }
    
    protected String convertirBooleano(boolean bool) {
        return (bool) ? Constantes.VAL_TRUE : Constantes.VAL_FALSE;
    }

}
