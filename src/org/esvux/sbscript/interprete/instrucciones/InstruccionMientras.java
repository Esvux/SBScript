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
public class InstruccionMientras extends InstruccionAbstracta {

    public InstruccionMientras(Nodo instruccion, boolean permiteInterrupciones) {
        super(instruccion, permiteInterrupciones);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        Resultado ejecucion = null;
        while (true) {
            Nodo nodoCondicion = instruccion.getHijo(0);
            Resultado condicion = new Expresion(nodoCondicion).resolver(ctx);
            if(condicion.getTipo()!=Constantes.T_BOOL){
                //Error, no es una condición válida
                return FabricaResultado.creaFAIL();
            }
            boolean cumpleCondicion = condicion.getBooleano();
            if(!cumpleCondicion){
                //Termina el ciclo y limpia el contexto de todas las variables 
                //declaradas en el cuerpo de la instrucción Mientras
                ejecucion = FabricaResultado.creaOK();
                break;
            }
            Nodo nodoCuerpo = instruccion.getHijo(1);
            InstruccionCuerpo instr = new InstruccionCuerpo(nodoCuerpo, true);
            ejecucion = instr.ejecutar(ctx, nivel);
            if(ejecucion.esRetorno())
                break;
            if(ejecucion.esDetener()){
                ejecucion = FabricaResultado.creaOK();
                break;
            }
            ctx.limpiarContexto(nivel);
        }
        ctx.limpiarContexto(nivel);
        return ejecucion;
    }

}
