package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class InstruccionCuerpo extends InstruccionAbstracta {

    public InstruccionCuerpo(Nodo instruccion, boolean permiteInterrupciones) {
        super(instruccion, permiteInterrupciones);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        if (!instruccion.esDeRol(Constantes.CUERPO)) {
            return new Resultado();
        }
        Object[] cuerpo = instruccion.getHijos().toArray();
        for (Object objNodo : cuerpo) {
            Nodo nodo = (Nodo) objNodo;
            Resultado res = FabricaResultado.creaOK();
            switch (nodo.getRol()) {
                case Constantes.DECLARACION:
                    res = new InstruccionDeclaracion(nodo).ejecutar(ctx, nivel);
                    break;
                case Constantes.ASIGNACION:
                    res = new InstruccionAsignacion(nodo).ejecutar(ctx, nivel);
                    break;
                case Constantes.MOSTRAR:
                    res = new InstruccionMostrar(nodo).ejecutar(ctx, nivel);
                    break;
                case Constantes.LLAMADA:
                    break;
                case Constantes.RETORNO:
                    break;
                case Constantes.SI:
                    break;
                case Constantes.SELECCIONA:
                    break;
                case Constantes.MIENTRAS:
                    res = new InstruccionMientras(nodo, true).ejecutar(ctx, nivel + 1);
                    break;
                case Constantes.PARA:
                    break;
                case Constantes.DETENER:
                    if (permiteInterrupciones) {
                        return FabricaResultado.creaDetener();
                    }
                    //Error, 'Detener' fuera de lugar
                    res = FabricaResultado.creaFAIL();
                    break;
                case Constantes.CONTINUAR:
                    if (permiteInterrupciones) {
                        return FabricaResultado.creaContinuar();
                    }
                    //Error, 'Continuar' fuera de lugar
                    res = FabricaResultado.creaFAIL();
                    break;
            }
        }
        return FabricaResultado.creaOK();
    }

}
