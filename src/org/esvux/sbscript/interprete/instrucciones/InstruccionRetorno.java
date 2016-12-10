package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionRetorno extends InstruccionAbstracta {

    public InstruccionRetorno(Nodo instruccion) {
        super(instruccion, false);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        Nodo exp = instruccion.getHijo(0);
        Resultado res = new Expresion(exp).resolver(ctx);
        return FabricaResultado.creaRetorno(res.getValor(), res.getTipo());
    }
    
}
