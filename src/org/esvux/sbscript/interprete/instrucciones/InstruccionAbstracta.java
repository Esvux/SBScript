package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Ambito;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.Variable;

/**
 *
 * @author esvux
 */
public abstract class InstruccionAbstracta {

    protected Nodo instruccion;
    protected boolean permiteInter;
    protected static Ambito ambitoGlobal;

    InstruccionAbstracta(Nodo instruccion, boolean permiteInter) {
        this.instruccion = instruccion;
        this.permiteInter = permiteInter;
    }

    public static Variable buscarVariable(Ambito ambito, String nombre) {
        Variable var = ambito.getVariable(nombre);
        if (var != null) {
            return var;
        }
        var = InstruccionAbstracta.ambitoGlobal.getVariable(nombre);
        if (var == null) {
            //Error, variable inexistente
        }
        return var;
    }

    public static void setAmbitoGlobal(Ambito ambito) {
        ambitoGlobal = ambito;
    }

    public abstract Resultado ejecutar(Ambito ctx, int nivel);

}
