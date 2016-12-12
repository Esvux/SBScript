package org.esvux.sbscript.ast;

import java.util.ArrayList;
import java.util.List;
import org.esvux.sbscript.parser.Token;

/**
 *
 * @author esvux
 */
public class Nodo {

    private String cadena;
    private int rol;
    private int subrol;
    private int tipo;
    private int linea;
    private int columna;
    private List<Nodo> hijos;
    private List<String> listaAux;

    public Nodo(int rol, int tipo, String cadena) {
        this.cadena = cadena;
        this.rol = rol;
        this.subrol = Constantes.NULO;
        this.tipo = tipo;
        this.linea = 0;
        this.columna = 0;
        this.hijos = new ArrayList<>();
        this.listaAux = null;
    }

    public Nodo(int rol, String cadena) {
        this(rol, Constantes.T_ERROR, cadena);
    }

    public Nodo() {
        this(Constantes.NULO, Constantes.T_ERROR, "");
    }

    public boolean esDeRol(int ... roles) {
        for (int i = 0; i < roles.length; i++) {
            if (this.rol == roles[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean esDeTipo(int ... tipos) {
        for (int i = 0; i < tipos.length; i++) {
            if (this.tipo == tipos[i]) {
                return true;
            }
        }
        return false;
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

    public int getSubrol() {
        return subrol;
    }

    public void setSubrol(int subrol) {
        this.subrol = subrol;
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

    public void addHijo(Nodo nodo) {
        this.hijos.add(nodo);
    }

    public Nodo getHijo(int i) {
        if (i >= 0 && i < this.hijos.size()) {
            return this.hijos.get(i);
        }
        return null;
    }
    
    public void setUbicacion(Token tok){
        this.linea = tok.beginLine;
        this.columna = tok.beginColumn;
    }
    
    public int getCantidadHijos(){
        return this.hijos.size();
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
    
}
