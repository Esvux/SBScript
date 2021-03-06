package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.errores.Errores;
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
            Resultado res = null;
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
                    res = new InstruccionLlamada(nodo).ejecutar(ctx, 1);
                    break;
                case Constantes.SI:
                    res = new InstruccionSi(nodo, permiteInterrupciones).ejecutar(ctx, nivel + 1);
                    break;
                case Constantes.SELECCIONA:
                    res = new InstruccionSelecciona(nodo, true).ejecutar(ctx, nivel + 1);
                    break;
                case Constantes.MIENTRAS:
                    res = new InstruccionMientras(nodo, true).ejecutar(ctx, nivel + 1);
                    break;
                case Constantes.PARA:
                    res = new InstruccionPara(nodo, true).ejecutar(ctx, nivel + 1);
                    break;
                case Constantes.RETORNO:
                    res = new InstruccionRetorno(nodo).ejecutar(ctx, nivel);
                    break;
                case Constantes.DETENER:
                    res = FabricaResultado.creaDetener();
                    break;
                case Constantes.CONTINUAR:
                    res = FabricaResultado.creaContinuar();
                    break;
            }
            if (res == null) {
                Errores.getInstance().nuevoErrorSemantico(nodo.getLinea(), nodo.getColumna(),
                        "Instruccion '" + nodo.getCadena() + "' fuera de control.");
                continue;
            }
            if (res.esRetorno()) {
                return res;
            }
            if (res.esDetener() || res.esContinuar()) {
                if (permiteInterrupciones) {
                    return res;
                } else {
                    Errores.getInstance().nuevoErrorSemantico(nodo.getLinea(), nodo.getColumna(),
                        "Instruccion '" + nodo.getCadena() + "' fuera de contexto.");
                }
            }
        }
        return FabricaResultado.creaOK();
    }

}
