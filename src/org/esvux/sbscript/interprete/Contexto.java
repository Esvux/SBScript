package org.esvux.sbscript.interprete;

import java.util.Iterator;
import java.util.HashMap;

/**
 *
 * @author esvux
 */
public class Contexto {

    private HashMap<String, Variable> variables;

    public Contexto() {
        variables = new HashMap<>();
    }
    
    public void limpiarContexto(int nivel) {
        Iterator<Variable> it = variables.values().iterator();
        HashMap<String, Variable> limpias = new HashMap<>();
        while (it.hasNext()) {
            Variable next = it.next();
            if (next.getNivel() != nivel) {
                limpias.put(next.getNombre(), next);
            }
        }
        variables.clear();
        variables.putAll(limpias);
    }

    public Variable getVariable(String nombre) {
        return variables.get(nombre);
    }
    
    public boolean existeVariable(String nombre) {
        return variables.containsKey(nombre);
    }
    
    public void setVariable(Variable var) {
        this.variables.put(var.getNombre(), var);
    }
    
    public void reporte() {
        Iterator<Variable> it = variables.values().iterator();
        while (it.hasNext()) {
            Variable next = it.next();
            System.out.println(next.toString());
        }        
    }

}
