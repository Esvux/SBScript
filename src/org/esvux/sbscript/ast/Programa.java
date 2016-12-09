package org.esvux.sbscript.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.esvux.sbscript.errores.Errores;

/**
 *
 * @author esvux
 */
public class Programa {

    private Metodo principal;
    private HashMap<String, Metodo> metodos;
    private List<Nodo> variables;

    public Programa() {
        this.principal = null;
        this.metodos = new HashMap<>();
        this.variables = new ArrayList<>();
    }

    public Metodo getPrincipal() {
        return principal;
    }

    public void setPrincipal(Metodo principal) {
        if (this.principal == null) {
            this.principal = principal;
        } else {
            Errores.getInstance().nuevoErrorSemantico(
                    principal.getFila(), principal.getColumna(), 
                    "No es posible definir más de un método principal en un mismo archivo.");
        }
    }

    public List<Nodo> getVariables() {
        return variables;
    }

    public void addVariables(Nodo declaracion) {
        variables.add(declaracion);
    }

    public void addMetodo(Metodo metodo) {
        if(metodos.containsKey(metodo.getNombre())) {
            Errores.getInstance().nuevoErrorSemantico(
                    metodo.getFila(), metodo.getColumna(), 
                    "No es posible definir dos o más métodos con el mismo nombre.");
        } else {
            metodos.put(metodo.getNombre(), metodo);
        }
    }

}
