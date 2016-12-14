package org.esvux.sbscript.interprete;

import org.esvux.sbscript.ast.Constantes;

/**
 * Siguiendo el patron de diseño de Factory, esta clase es la encargada de la
 * creacion de objetos de tipo Resultado de forma predeterminada.
 * @author esvux
 */
public abstract class FabricaResultado {

    /**
     * Crea un nuevo resultado que indica que la ejecucion de una o varias 
     * instrucciones termino de manera satisfactoria.
     * @return El resultado OK.
     */
    public static Resultado creaOK() {
        return new Resultado(Constantes.VAL_OK, Constantes.T_VOID);
    }

    /**
     * Crea un nuevo resultado que indica que la ejecucion ha terminado de 
     * manera erronea.
     * @return El resultado FAIL.
     */
    public static Resultado creaFAIL() {
        return new Resultado(Constantes.VAL_FAIL, Constantes.T_ERROR);
    }

    /**
     * Crea un nuevo resultado con valor y tipo determinados por los parametros
     * que recibe y marca el resultado como retorno.
     * @param valor Valor retornado.
     * @param tipo Tipo del retorno.
     * @return El resultado RETORNO.
     */
    public static Resultado creaRetorno(String valor, int tipo) {
        return new Resultado(valor, tipo, true);
    }

    /**
     * Crea un resultado con el tipo Constantes.T_DETENER.
     * @return El resultado DETENER.
     */
    public static Resultado creaDetener() {
        return new Resultado("", Constantes.T_DETENER);
    }
    
    /**
     * Crea un resultado con el tipo Constantes.T_CONTINUAR.
     * @return El resultado CONTINUAR.
     */
    public static Resultado creaContinuar() {
        return new Resultado("", Constantes.T_CONTINUAR);
    }
    
    /**
     * Crea un resultado con el tipo Constantes.T_STR y con el valor indicado
     * por el atributo cadena.
     * @param cadena Valor de la cadena.
     * @return El resultado CADENA.
     */
    public static Resultado creaCadena(String cadena) {
        return new Resultado(cadena, Constantes.T_STR);
    }

    /**
     * Crea un resultado con el tipo Constantes.T_NUM y con el valor numerico
     * que trae el parametro numero, si la cadena termina con ".0" se elimina
     * el ".0" para que el valor pueda mostrarse como entero (sin parte decimal).
     * @param numero Valor del numero.
     * @return El resultado NUMERO.
     */
    public static Resultado creaNumero(String numero) {
        if(numero.endsWith(".0")){
            numero = numero.replace(".0", "");
        }
        return new Resultado(numero, Constantes.T_NUM);
    }

    /**
     * Crea un resultado con el tipo Constantes.T_BOOL y se modifica la cadena
     * segun el valor del parametro bool.
     * @param bool Valor del booleano.
     * @return El resultado BOOLEANO.
     */
    public static Resultado creaBooleano(boolean bool) {
        Resultado res = new Resultado("", Constantes.T_BOOL);
        res.setBooleano(bool);
        return res;
    }

}
