package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionSi extends InstruccionAbstracta {

    public InstruccionSi(Nodo instruccion, boolean permiteInter) {
        super(instruccion, permiteInter);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        Nodo nodoCondicion = instruccion.getHijo(0);
        Resultado condicion = new Expresion(nodoCondicion).resolver(ctx);
        if (condicion.getTipo() != Constantes.T_BOOL) {
            //Error, no es una condición válida
            return FabricaResultado.creaFAIL();
        }
        Resultado res = FabricaResultado.creaOK();
        boolean cumpleCondicion = condicion.getBooleano();
        if (cumpleCondicion) {
            Nodo nodoThen = instruccion.getHijo(1);
            InstruccionCuerpo instr = new InstruccionCuerpo(nodoThen, permiteInterrupciones);
            res = instr.ejecutar(ctx, nivel + 1);
        } else {
            Nodo nodoElse = instruccion.getHijo(2);
            if (nodoElse != null) {
                InstruccionCuerpo instr = new InstruccionCuerpo(nodoElse, permiteInterrupciones);
                res = instr.ejecutar(ctx, nivel + 1);
            }
        }
        ctx.limpiarContexto(nivel);
        return res;
    }

}
