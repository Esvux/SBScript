package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class ExpresionRelacional extends ExpresionAbstracta {

    public ExpresionRelacional(Nodo izq, Nodo der, int operando) {
        super(izq, der, operando);
    }    

    @Override
    public Resultado resolver(Contexto ctx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
