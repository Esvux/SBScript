package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Interprete;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.Variable;

/**
 *
 * @author esvux
 */
public abstract class InstruccionAbstracta {

    protected Nodo instruccion;
    protected boolean permiteInterrupciones;

    InstruccionAbstracta(Nodo instruccion, boolean permiteInterrupciones) {
        this.instruccion = instruccion;
        this.permiteInterrupciones = permiteInterrupciones;
    }

    public abstract Resultado ejecutar(Contexto ctx, int nivel);

    public static Variable obtenerVariable(Contexto ctx, String nombre) {
        Variable var = ctx.getVariable(nombre);
        if (var != null) {
            return var;
        }
        var = Interprete.getContextoGlobal().getVariable(nombre);
        return var;
    }

    protected void setVariable(Contexto ctx, Variable var) {
        if (ctx.existeVariable(var.getNombre())) {
            ctx.setVariable(var);
        } else if (Interprete.getContextoGlobal().existeVariable(var.getNombre())) {
            Interprete.getContextoGlobal().setVariable(var);
        }
    }

    protected boolean existeVariable(Contexto ctx, String nombre) {
        return ctx.existeVariable(nombre) || Interprete.getContextoGlobal().existeVariable(nombre);
    }

    protected boolean asignacionValida(int tipoDestino, int tipoFuente) {
        //Una comprobación más extensa sería bien resuelta por una 
        //matriz de tipos, ver resolverSuma
        return tipoDestino == tipoFuente;
    }

}
