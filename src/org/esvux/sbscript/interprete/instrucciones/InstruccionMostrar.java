package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Interprete;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionMostrar extends InstruccionAbstracta {

    public InstruccionMostrar(Nodo instruccion) {
        super(instruccion, false);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        for (Nodo exp : instruccion.getHijos()) {
            Resultado res = new Expresion(exp).resolver(ctx);
            if (res.esError()) {
                Errores.getInstance().nuevoErrorSemantico(exp.getLinea(), exp.getColumna(),
                        "La expresion que se desea mostrar cuenta con errores.");
                continue;
            }
            Interprete.concatenarSalida(res.getValor() + " ");
        }
        Interprete.concatenarSalida("\n");
        return FabricaResultado.creaOK();
    }

}
