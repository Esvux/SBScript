package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class InstruccionCuerpo extends InstruccionAbstracta {

    public InstruccionCuerpo(Nodo nodo, boolean permiteInter) {
        super(nodo, permiteInter);
    }        

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
