package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionPara extends InstruccionAbstracta {

    public InstruccionPara(Nodo instruccion, boolean permiteInter) {
        super(instruccion, permiteInter);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        Nodo nodoDeclara = instruccion.getHijo(0);
        new InstruccionDeclaracion(nodoDeclara).ejecutar(ctx, nivel);
        Resultado ejecucion = null;
        while(true){
            Nodo nodoCondicion = instruccion.getHijo(1);
            Resultado condicion = new Expresion(nodoCondicion).resolver(ctx);
            if(condicion.getTipo()!=Constantes.T_BOOL){
                Errores.getInstance().nuevoErrorSemantico(nodoCondicion.getLinea(), nodoCondicion.getColumna(),
                        "La condicion de la instruccion PARA no es una condicion valida.");
                return FabricaResultado.creaFAIL();
            }
            boolean cumpleCond = condicion.getBooleano();
            if(!cumpleCond){
                //Sale del ciclo
                ejecucion = FabricaResultado.creaOK();
                break;
            }
            Nodo nodoCuerpo = instruccion.getHijo(2);
            InstruccionCuerpo instr = new InstruccionCuerpo(nodoCuerpo, true);
            ejecucion = instr.ejecutar(ctx, nivel + 1);
            if(ejecucion.esRetorno())
                break;
            if(ejecucion.esDetener()){
                ejecucion = FabricaResultado.creaOK();
                break;
            }
            Nodo nodoCambio = instruccion.getHijo(3);
            new InstruccionAsignacion(nodoCambio).ejecutar(ctx, nivel);
            ctx.limpiarContexto(nivel + 1);
        }
        ctx.limpiarContexto(nivel + 1);
        ctx.limpiarContexto(nivel);
        return ejecucion;
    }
    
}
