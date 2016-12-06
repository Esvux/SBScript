package org.esvux.sbscript.ast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esvux
 */
public class Metodo {
    
    private String nombre;
    private int tipo, fila, columna;
    private List<Nodo> params;
    private Nodo cuerpo;

    public Metodo(String nombre, int tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.params = new ArrayList<>();
        this.cuerpo = new Nodo();
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

    public List<Nodo> getParams() {
        return params;
    }

    public void setParams(List<Nodo> params) {
        this.params = params;
    }

    public Nodo getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Nodo cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    public void addParam(Nodo param){
        this.params.add(param);
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
    
}
