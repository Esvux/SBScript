package org.esvux.sbscript.ast;

import java.util.ArrayList;
import java.util.List;
import org.esvux.sbscript.parser.Token;

/**
 * Clase que representa al AST, cuenta con atributos basicos para la puesta en
 * marcha de comparaciones segun tip, rol o subrol; Ademas cuenta con los atributos
 * linea y columna para contar con un reporte de errores mas detallado, por 
 * ultimo para contar con el comportamiento de arbol se agrega una lista de 
 * objetos tipo Nodo que representan los hijos del nodo actual.
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

    /**
     * Constructor principal.
     * @param rol El rol del nodo.
     * @param tipo El tipo de dato vinculado al nodo.
     * @param cadena La cadena del nodo.
     */
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

    /**
     * Constructor derivado, el tipo por defecto es Constantes.T_ERROR.
     * @param rol El rol del nodo.
     * @param cadena La cadena del nodo.
     */
    public Nodo(int rol, String cadena) {
        this(rol, Constantes.T_ERROR, cadena);
    }

    /**
     * Constructor derivado, el rol por defecto es Constantes.NULO, el tipo 
     * por defecto es Constantes.T_ERROR y la cadena es una cadena vacia (""),
     * este constructor es utilizado para generar un nodo que represente un error.
     */
    public Nodo() {
        this(Constantes.NULO, Constantes.T_ERROR, "");
    }

    /**
     * Simplifica la pregunta ¿Este nodo es de rol X, Y o Z? realiza una comparacion
     * simulando un OR dinamico (en cortocircuito).
     * @param roles Arreglo de enteros a comparar
     * @return true con la primera coincidencia sin importar las demas, false si 
     * ninguno coincide.
     */
    public boolean esDeRol(int ... roles) {
        for (int i = 0; i < roles.length; i++) {
            if (this.rol == roles[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simplifica la pregunta ¿Este nodo es de tipo X, Y o Z? realiza una comparacion
     * simulando un OR dinamico (en cortocircuito).
     * @param tipos Arreglo de enteros a comparar
     * @return true con la primera coincidencia sin importar las demas, false si 
     * ninguno coincide.
     */
    public boolean esDeTipo(int ... tipos) {
        for (int i = 0; i < tipos.length; i++) {
            if (this.tipo == tipos[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return El atributo cadena (por defecto una cadena vacia "").
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * Setea el atributo cadena del nodo.
     * @param cadena Nueva cadena.
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     * @return El rol del nodo (por defecto Constantes.NULO).
     */
    public int getRol() {
        return rol;
    }

    /**
     * Setea el atributo rol del nodo.
     * @param rol Nuevo rol.
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

    /**
     * @return El subrol del nodo (por defecto Constantes.NULO).
     */
    public int getSubrol() {
        return subrol;
    }

    /**
     * Setea el atributo subrol del nodo.
     * @param subrol El nuevo subrol.
     */
    public void setSubrol(int subrol) {
        this.subrol = subrol;
    }

    /**
     * @return El tipo del nodo (por defecto Constantes.T_ERROR).
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Setea el atributo tipo del nodo.
     * @param tipo El nuevo tipo.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return La lista de hijos del nodo.
     */
    public List<Nodo> getHijos() {
        return hijos;
    }

    /**
     * Crea el atributo hijos, como una nueva lista con los elementos de la lista
     * que recibe como parametro.
     * @param hijos Nueva lista de hijos.
     */    
    public void setHijos(List<Nodo> hijos) {
        this.hijos = new ArrayList<>(hijos);
    }

    /**
     * @return La lista auxiliar de cadenas del nodo (por defecto null).
     */
    public List<String> getListaAux() {
        return listaAux;
    }

    /**
     * Crea el atributo listaAux, como una nueva lista con los elementos de la lista
     * que recibe como parametro.
     * @param listaAux Nueva lista auxiliar de cadenas.
     */    
    public void setListaAux(List<String> listaAux) {
        this.listaAux = new ArrayList<>(listaAux);
    }

    /**
     * Crea el atributo listaAux, como una lista vacia.
     */    
    public void initListaAux() {
        this.listaAux = new ArrayList<>();
    }

    /**
     * Agrega la cadena que recibe como parametro a listaAux.
     * @param nuevo Nueva cadena.
     */
    public void addAux(String nuevo) {
        this.listaAux.add(nuevo);
    }

    /**
     * Agrega el nodo que recibe como parametro a hijos.
     * @param nodo Nuevo nodo.
     */
    public void addHijo(Nodo nodo) {
        this.hijos.add(nodo);
    }

    /**
     * Se encarga de obtener el hijo del nodo en una posicion especifica, controlando
     * que el indice este dentro de los elementos de la lista.
     * @param i Indice del i-esimo elemento que se desea obtener.
     * @return El i-esimo nodo o null si el indice esta fuera de lo permitido.
     */
    public Nodo getHijo(int i) {
        if (i >= 0 && i < this.hijos.size()) {
            return this.hijos.get(i);
        }
        return null;
    }
    
    /**
     * Substrae la linea y la columna del token que recibe como parametro.
     * @param tok Token vinculado al nodo.
     */
    public void setUbicacion(Token tok){
        this.linea = tok.beginLine;
        this.columna = tok.beginColumn;
    }
    
    /**
     * @return La cantidad de hijos del nodo.
     */
    public int getCantidadHijos(){
        return this.hijos.size();
    }

    /**
     * @return La linea vinculada al nodo (por defecto 0)
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @return La linea vinculada al nodo (por defecto 0)
     */
    public int getColumna() {
        return columna;
    }
    
}
