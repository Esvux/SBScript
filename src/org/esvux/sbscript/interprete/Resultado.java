package org.esvux.sbscript.interprete;

import org.esvux.sbscript.ast.Constantes;

/**
 *
 * @author esvux
 */
public class Resultado {

    private String valor;
    private int tipo;
    private boolean retorno;

    public Resultado(String valor, int tipo, boolean retorno) {
        this.valor = valor;
        this.tipo = tipo;
        this.retorno = retorno;
    }

    public Resultado(String valor, int tipo) {
        this(valor, tipo, false);
    }

    public Resultado() {
        this("", Constantes.T_ERROR, false);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getDouble() {
        return Double.parseDouble(valor);
    }

    public boolean getBooleano() {
        return valor.compareTo(Constantes.VAL_TRUE) == 0;
    }

    public void setBooleano(boolean bool) {
        valor = (bool) ? Constantes.VAL_TRUE : Constantes.VAL_FALSE;
    }

    public boolean esContinuar() {
        return tipo == Constantes.T_CONTINUAR;
    }

    public boolean esDetener() {
        return tipo == Constantes.T_DETENER;
    }

    public boolean esRetorno() {
        return retorno;
    }

    public boolean esError() {
        return tipo == Constantes.T_ERROR;
    }
    
    public boolean esFail() {
        return tipo == Constantes.T_ERROR && 
                valor.compareTo(Constantes.VAL_FAIL) == 0;
    }

    public boolean esOk() {
        return tipo == Constantes.T_VOID && 
                valor.compareTo(Constantes.VAL_OK) == 0;
    }

}
