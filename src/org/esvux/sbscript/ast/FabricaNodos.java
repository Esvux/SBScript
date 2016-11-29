package org.esvux.sbscript.ast;

import java.util.ArrayList;

/**
 *
 * @author esvux
 */
public class FabricaNodos {
    
    public static Nodo copiar(Nodo nodo){
        Nodo copia = new Nodo();
        copia.setRol(nodo.getRol());
        copia.setTipo(nodo.getTipo());
        copia.setCadena(nodo.getCadena());
        copia.setHijos(new ArrayList<>(nodo.getHijos()));
        copia.setCadenas(new ArrayList<>(nodo.getCadenas()));
        return copia;
    }
    
    public static Nodo creaNumero(String numero){
        Nodo nodo = new Nodo(Constantes.NUMERO, Constantes.T_NUM, numero);
        return nodo;
    }

    public static Nodo creaCadena(String cadena){
        Nodo nodo = new Nodo(Constantes.CADENA, Constantes.T_STR, cadena);
        return nodo;
    }
    
    public static Nodo creaTrue(){
        Nodo nodo = new Nodo(Constantes.TRUE, Constantes.T_BOOL, "true");
        return nodo;
    }

    public static Nodo creaFalse(){
        Nodo nodo = new Nodo(Constantes.FALSE, Constantes.T_BOOL, "false");
        return nodo;
    }
    
    public static Nodo creaMenosUnario(Nodo val){
        Nodo nodo = new Nodo(Constantes.MENOS, "-val");
        nodo.addHijo(val);
        return nodo;
    }
    
    public static Nodo creaAccesoID(String id){
        Nodo nodo = new Nodo(Constantes.VARIABLE, id);
        return nodo;
    }
    
    public static Nodo creaAritmetica(String operador, Nodo izq, Nodo der){
        Nodo nodo = new Nodo(Constantes.ARITMETICA, operador);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    public static Nodo creaRelacional(String operador, Nodo izq, Nodo der){
        Nodo nodo = new Nodo(Constantes.RELACIONAL, operador);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }
    
    public static Nodo creaLogica(String operador, Nodo izq, Nodo der){
        Nodo nodo = new Nodo(Constantes.LOGICA, operador);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    public static Nodo creaNot(Nodo negado){
        Nodo nodo = new Nodo(Constantes.LOGICA, Constantes.OP_NOT);
        nodo.addHijo(negado);
        return nodo;
    }
    
}
