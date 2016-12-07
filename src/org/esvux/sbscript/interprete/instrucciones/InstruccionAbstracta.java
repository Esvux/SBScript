package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public abstract class InstruccionAbstracta {
    protected Nodo nodo;
    protected boolean permiteInter;

    InstruccionAbstracta(Nodo nodo, boolean permiteInter) {
        this.nodo = nodo;
        this.permiteInter = permiteInter;
    }
    
    public abstract Resultado ejecutar(Contexto ctx, int nivel);
    
}
