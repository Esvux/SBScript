package org.esvux.sbscript.ast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esvux
 */
public class Nodo {

    private String cadena;
    private int rol;
    private int tipo;    
    private List<Nodo> hijos;
    private List<String> listaAux;

    public Nodo() {
        this.cadena = "";
        this.rol = Constantes.NULO;
        this.tipo = Constantes.T_ERROR;
        this.hijos = new ArrayList<>();
        this.listaAux = null;
    }

    public Nodo(int rol, String cadena) {
        this.cadena = cadena;
        this.rol = rol;
        this.tipo = Constantes.T_ERROR;
        this.hijos = new ArrayList<>();
        this.listaAux = null;
    }

    public Nodo(int rol, int tipo, String cadena) {
        this.cadena = cadena;
        this.rol = rol;
        this.tipo = tipo;
        this.hijos = new ArrayList<>();
        this.listaAux = null;
    }
    
    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Nodo> hijos) {
        this.hijos = hijos;
    }

    public List<String> getListaAux() {
        return listaAux;
    }

    public void setListaAux(List<String> listaAux) {
        this.listaAux = listaAux;
    }

    public void initListaAux() {
        this.listaAux = new ArrayList<>();
    }
    
    public void addAux(String nuevo) {
        this.listaAux.add(nuevo);
    }
    
    public void addHijo(Nodo nodo){
        this.hijos.add(nodo);
    }
    
}
