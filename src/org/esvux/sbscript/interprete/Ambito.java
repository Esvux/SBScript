package org.esvux.sbscript.interprete;

import java.util.Iterator;
import java.util.HashMap;

/**
 *
 * @author esvux
 */
public class Ambito {

    private HashMap<String, Variable> variables;

    public Ambito() {
        variables = new HashMap<>();
    }
    
    public void limpiarAmbito(int nivel) {
        Iterator<Variable> it = variables.values().iterator();
        HashMap<String, Variable> limpias = new HashMap<>();
        while (it.hasNext()) {
            Variable next = it.next();
            if (next.getAmbito() != nivel) {
                limpias.put(next.getNombre(), next);
            }
        }
        variables.clear();
        variables.putAll(limpias);
    }

    public Variable getVariable(String nombre) {
        return variables.get(nombre);
    }

}
