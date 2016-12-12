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
public class InstruccionSelecciona extends InstruccionAbstracta {

    public InstruccionSelecciona(Nodo instruccion, boolean permiteInter) {
        super(instruccion, permiteInter);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        boolean seCumplioAlguno = false;
        Resultado ejecucion = FabricaResultado.creaOK();
        for (Nodo nodoCaso : instruccion.getHijos()) {
            Nodo nodoCond = nodoCaso.getHijo(0);
            Resultado condicion = new Expresion(nodoCond).resolver(ctx);
            if (condicion.getTipo() != Constantes.T_BOOL) {
                ejecucion = FabricaResultado.creaFAIL();
                break;
            }
            if (!(seCumplioAlguno || condicion.getBooleano())) {
                continue;
            }
            seCumplioAlguno = true;
            Nodo nodoCuerpo = nodoCaso.getHijo(1);
            InstruccionCuerpo instr = new InstruccionCuerpo(nodoCuerpo, true);
            ejecucion = instr.ejecutar(ctx, nivel);
            if (ejecucion.esRetorno()) {
                break;
            }
            if (ejecucion.esDetener()) {
                ejecucion = FabricaResultado.creaOK();
                break;
            }
            ctx.limpiarContexto(nivel);
        }
        ctx.limpiarContexto(nivel);
        return ejecucion;
    }

}
