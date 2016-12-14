package org.esvux.sbscript.interprete;

import java.util.Iterator;
import java.util.HashMap;

/**
 * Esta clase es la encargada de almacenar las variables definidas en el programa
 * que se esta ejecutando, asi mismo debe limpiar la existencia de variables que
 * ya no sean accesibles en el momento de ejecucion.
 * @author esvux
 */
public class Contexto {

    private HashMap<String, Variable> variables;

    /**
     * Constructor de la clase Contexto.
     */
    public Contexto() {
        variables = new HashMap<>();
    }
    
    /**
     * Elimina del contexto actual todas las variables que sean del nivel que se
     * recibe como parametro.
     * @param nivel Nivel que se desea limpiar.
     */
    public void limpiarContexto(int nivel) {
        Iterator<Variable> it = variables.values().iterator();
        HashMap<String, Variable> limpias = new HashMap<>();
        while (it.hasNext()) {
            Variable next = it.next();
            if (next.getNivel() != nivel) {
                limpias.put(next.getNombre(), next);
            }
        }
        variables = new HashMap<>(limpias);
    }

    /**
     * Obtiene una variable por su nombre.
     * @param nombre Nombre a buscar.
     * @return La variable encontrada o null si no existe ninguna variable con el
     * nombre especificado por el parametro.
     */
    public Variable getVariable(String nombre) {
        return variables.get(nombre);
    }
    
    /**
     * Determina si una variable existe en el contexto actual.
     * @param nombre Nombre a buscar.
     * @return Verdadero si existe una variable con el nombre especificado, de 
     * lo contrario falso.
     */
    public boolean existeVariable(String nombre) {
        return variables.containsKey(nombre);
    }
    
    /**
     * Agrega una nueva variable al contexto actual, si ya existe una variable
     * con el mismo nombre, la sobreescribira.
     * @param var Variable a agregar.
     */
    public void setVariable(Variable var) {
        this.variables.put(var.getNombre(), var);
    }
    
    /**
     * Imprime en consola el detalle de las variables existentes en el contexto.
     */
    public void reporte() {
        Iterator<Variable> it = variables.values().iterator();
        while (it.hasNext()) {
            Variable next = it.next();
            System.out.println(next.toString());
        }        
    }

}
