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
    private List<String> cadenas;

    public Nodo() {
        this.cadena = "";
        this.rol = Constantes.NULO;
        this.tipo = Constantes.T_ERROR;
        this.hijos = new ArrayList<>();
        this.cadenas = new ArrayList<>();
    }

    public Nodo(int rol, String cadena) {
        this.cadena = cadena;
        this.rol = rol;
        this.tipo = Constantes.T_ERROR;
        this.hijos = new ArrayList<>();
        this.cadenas = new ArrayList<>();
    }

    public Nodo(int rol, int tipo, String cadena) {
        this.cadena = cadena;
        this.rol = rol;
        this.tipo = tipo;
        this.hijos = new ArrayList<>();
        this.cadenas = new ArrayList<>();
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

    public List<String> getCadenas() {
        return cadenas;
    }

    public void setCadenas(List<String> cadenas) {
        this.cadenas = cadenas;
    }
    
    public void addHijo(Nodo nodo){
        this.hijos.add(nodo);
    }
    
}
