package org.esvux.sbscript.ast;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author esvux
 */
public class Metodo {

    private String nombre;
    private int tipo, fila, columna;
    private HashMap<String, Nodo> params;
    private Nodo cuerpo;
    private boolean correcto;

    public Metodo(String nombre, int tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.params = new HashMap<>();
        this.cuerpo = new Nodo();
        this.correcto = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public HashMap<String, Nodo> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Nodo> params) {
        this.params = params;
    }

    public Nodo getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Nodo cuerpo) {
        this.cuerpo = cuerpo;
    }

    public boolean addParam(Nodo param) {
        String p = param.getCadena();
        if (params.containsKey(p)) {
            return false;
        }
        params.put(p, param);
        return true;
    }

    public void setUbicacion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void marcarComoIncorrecto() {
        correcto = false;
    }

    public boolean esIncorrecto() {
        return !correcto;
    }
    
    public int getCantidadParametros(){
        return this.params.size();
    }
    
    public Collection<Nodo> getParametros(){
        return this.params.values();
    }

}
