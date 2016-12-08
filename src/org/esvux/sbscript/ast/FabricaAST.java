package org.esvux.sbscript.ast;

import java.util.ArrayList;

/**
 *
 * @author esvux
 */
public class FabricaAST {

    public static Nodo copiar(Nodo nodo) {
        Nodo copia = new Nodo();
        copia.setRol(nodo.getRol());
        copia.setSubrol(nodo.getSubrol());
        copia.setTipo(nodo.getTipo());
        copia.setCadena(nodo.getCadena());
        copia.setHijos(new ArrayList<>(nodo.getHijos()));
        if (nodo.getListaAux() != null) {
            copia.setListaAux(new ArrayList<>(nodo.getListaAux()));
        }
        return copia;
    }

    public static Nodo creaNumero(String numero) {
        Nodo nodo = new Nodo(Constantes.NUMERO, Constantes.T_NUM, numero);
        return nodo;
    }

    public static Nodo creaCadena(String cadena) {
        Nodo nodo = new Nodo(Constantes.CADENA, Constantes.T_STR, cadena);
        return nodo;
    }

    public static Nodo creaTrue() {
        Nodo nodo = new Nodo(Constantes.TRUE, Constantes.T_BOOL, Constantes.VAL_TRUE);
        return nodo;
    }

    public static Nodo creaFalse() {
        Nodo nodo = new Nodo(Constantes.FALSE, Constantes.T_BOOL, Constantes.VAL_FALSE);
        return nodo;
    }

    public static Nodo creaMenosUnario(Nodo val) {
        Nodo nodo = new Nodo(Constantes.ARITMETICA_UNARIA, "-(unario)");
        nodo.setSubrol(Constantes.OPA_MEN);
        nodo.addHijo(val);
        return nodo;
    }

    public static Nodo creaAccesoID(String id) {
        Nodo nodo = new Nodo(Constantes.VARIABLE, id);
        return nodo;
    }

    public static Nodo creaAritmetica(String operador, int op, Nodo izq, Nodo der) {
        Nodo nodo = new Nodo(Constantes.ARITMETICA, operador);
        nodo.setSubrol(op);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    public static Nodo creaRelacional(String operador, int op, Nodo izq, Nodo der) {
        Nodo nodo = new Nodo(Constantes.RELACIONAL, operador);
        nodo.setSubrol(op);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    public static Nodo creaLogica(String operador, int op, Nodo izq, Nodo der) {
        Nodo nodo = new Nodo(Constantes.LOGICA, operador);
        nodo.setSubrol(op);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    public static Nodo creaNot(Nodo negado, String str) {
        Nodo nodo = new Nodo(Constantes.LOGICA_UNARIA, str);
        nodo.setSubrol(Constantes.OPL_NOT);
        nodo.addHijo(negado);
        return nodo;
    }

    public static Nodo creaCuerpo() {
        Nodo nodo = new Nodo(Constantes.CUERPO, "Cuerpo");
        return nodo;
    }

    public static Nodo creaDeclaracion(int tipo, String id) {
        Nodo nodo = new Nodo(Constantes.DECLARACION, tipo, "Declara");
        nodo.initListaAux();
        nodo.addAux(id);
        return nodo;
    }

    public static Nodo creaAsignacion(String id, Nodo exp) {
        Nodo nodo = new Nodo(Constantes.ASIGNACION, id);
        nodo.addHijo(exp);
        return nodo;
    }

    public static Nodo creaLlamada(String id) {
        Nodo nodo = new Nodo(Constantes.LLAMADA, id);
        return nodo;
    }

    public static Nodo creaRetorno(Nodo exp) {
        Nodo nodo = new Nodo(Constantes.RETORNO, "Retorno");
        nodo.addHijo(exp);
        return nodo;
    }

    public static Nodo creaMostrar(Nodo exp) {
        Nodo nodo = new Nodo(Constantes.MOSTRAR, "Mostrar");
        nodo.addHijo(exp);
        return nodo;
    }

    public static Nodo creaSi(Nodo cond, Nodo cuerpoThen, Nodo cuerpoElse) {
        Nodo nodo = new Nodo(Constantes.SI, "Si[Sino]");
        nodo.addHijo(cond);
        nodo.addHijo(cuerpoThen);
        if (cuerpoElse != null) {
            nodo.addHijo(cuerpoElse);
        }
        return nodo;
    }

    public static Nodo creaSelecciona(Nodo exp) {
        Nodo nodo = new Nodo(Constantes.SELECCIONA, "Selecciona");
        nodo.addHijo(exp);
        return nodo;
    }

    public static Nodo creaCaso(Nodo val, Nodo cuerpo) {
        Nodo nodo = new Nodo(Constantes.CASO, "Caso");
        nodo.addHijo(val);
        nodo.addHijo(cuerpo);
        return nodo;
    }

    public static Nodo creaMientras(Nodo cond, Nodo cuerpo) {
        Nodo nodo = new Nodo(Constantes.MIENTRAS, "Mientras");
        nodo.addHijo(cond);
        nodo.addHijo(cuerpo);
        return nodo;
    }

    public static Nodo creaPara(Nodo dec, Nodo cond, Nodo cuerpo, int subrol) {
        Nodo nodo = new Nodo(Constantes.PARA, "Para");
        nodo.setSubrol(subrol);
        nodo.addHijo(dec);
        nodo.addHijo(cond);
        nodo.addHijo(cuerpo);
        return nodo;
    }
    
    public static Nodo creaDetener() {
        Nodo nodo = new Nodo(Constantes.DETENER, "Detener");
        return nodo;
    }

    public static Nodo creaContinuar() {
        Nodo nodo = new Nodo(Constantes.CONTINUAR, "Continuar");
        return nodo;
    }

    public static Metodo creaPrincipal() {
        Metodo metodo = new Metodo("Principal", Constantes.T_VOID);
        return metodo;
    }

    public static Metodo creaMetodo(String nombre, int tipo) {
        Metodo metodo = new Metodo(nombre, tipo);
        return metodo;
    }

    public static Nodo creaParametro(String nombre, int tipo) {
        Nodo nodo = new Nodo(Constantes.PARAMETRO, tipo, nombre);
        return nodo;
    }

}
