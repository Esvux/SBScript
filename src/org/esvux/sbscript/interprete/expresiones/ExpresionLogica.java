package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class ExpresionLogica extends ExpresionAbstracta {

    public ExpresionLogica(Nodo izq, Nodo der, int operando) {
        super(izq, der, operando);
    }   

    public ExpresionLogica(Nodo izq, int operando) {
        super(izq, null, operando);
    }   

    @Override
    public Resultado resolver(Contexto ctx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
