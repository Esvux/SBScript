package org.esvux.sbscript.ast;

import java.util.Collection;
import java.util.HashMap;

/**
 * Representacion de un metodo, cuenta con nombre propio, un tipo, una estructura
 * para almacenar los parametros, un nodo para representar el conjunto de instrucciones
 * denominado cuerpo, y un atributo para validar el estado de su definicion.
 * Ademas de un atributo linea y otro columna para apoyar el reporte de errores.
 * @author esvux
 */
public class Metodo {

    private String nombre;
    private int tipo, linea, columna;
    private HashMap<String, Nodo> params;
    private Nodo cuerpo;
    private boolean correcto;

    /**
     * Constructor de la clase Metodo, recibe el nombre y el tipo.
     * @param nombre Nombre propio del metodo.
     * @param tipo Tipo asociado al metodo (inclusive Constantes.T_VOID).
     */
    public Metodo(String nombre, int tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.params = new HashMap<>();
        this.cuerpo = new Nodo();
        this.correcto = true;
    }

    /**
     * @return El nombre del metodo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setea el nombre del metodo.
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El tipo del metodo.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Setea el tipo del metodo.
     * @param tipo Nuevo tipo del metodo.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return La estructura de los parametros del metodo.
     */
    public HashMap<String, Nodo> getParams() {
        return params;
    }

    /**
     * Setea los parametros de un metodo.
     * @param params Estructura de parametros para copiar.
     */
    public void setParams(HashMap<String, Nodo> params) {
        this.params = params;
    }

    /**
     * @return El nodo que representa el cuerpo del metodo.
     */
    public Nodo getCuerpo() {
        return cuerpo;
    }

    /**
     * Setea el nodo que representa el cuerpo del metodo.
     * @param cuerpo Nodo del cuerpo a asignar.
     */
    public void setCuerpo(Nodo cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * Agrega el nodo que representa un parametro a la estructura de parametros
     * del metodo, si y solo si, no existe previamente un parametro con el mismo
     * nombre del que se desea agregar.
     * @param param Parametro a agregar.
     * @return Verdadero si no existe un parametro con el mismo nombre, de lo 
     * contrario falso.
     */
    public boolean addParam(Nodo param) {
        String p = param.getCadena();
        if (params.containsKey(p)) {
            return false;
        }
        params.put(p, param);
        return true;
    }

    /**
     * Setea la ubicacion del metodo.
     * @param linea Linea en que fue definido el metodo.
     * @param columna Columna en que fue definido el metodo.
     */
    public void setUbicacion(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }

    /**
     * @return La linea en que se define el metodo.
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @return La columna en que se define el metodo.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Cambia el estado del metodo a 'incorrecto'.
     */
    public void marcarComoIncorrecto() {
        correcto = false;
    }

    /**
     * @return Verdadero si el estado es incorrecto, de lo contrario falso.
     */
    public boolean esIncorrecto() {
        return !correcto;
    }
    
    /**
     * @return La cantidad de parametros del metodo.
     */
    public int getCantidadParametros(){
        return this.params.size();
    }
    
    /**
     * @return Lista de parametros.
     */
    public Collection<Nodo> getParametros(){
        return this.params.values();
    }

}
