package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class InstruccionMostrar extends InstruccionAbstracta {

    public InstruccionMostrar(Nodo instruccion, boolean permiteInter) {
        super(instruccion, permiteInter);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
