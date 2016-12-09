package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.Variable;

/**
 *
 * @author esvux
 */
public abstract class InstruccionAbstracta {

    protected Nodo instruccion;
    protected boolean permiteInter;
    protected static Contexto contextoGlobal = new Contexto();

    InstruccionAbstracta(Nodo instruccion, boolean permiteInter) {
        this.instruccion = instruccion;
        this.permiteInter = permiteInter;
    }

    public static Variable obtenerVariable(Contexto ambito, String nombre) {
        Variable var = ambito.getVariable(nombre);
        if (var != null) {
            return var;
        }
        var = contextoGlobal.getVariable(nombre);
        return var;
    }

    public static void setContextoGlobal() {
        contextoGlobal = new Contexto();
    }
    
    public static Contexto getContextoGlobal() {
        return contextoGlobal;
    }


    public abstract Resultado ejecutar(Contexto ctx, int nivel);

}
