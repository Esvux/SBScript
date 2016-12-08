package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Ambito;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class InstruccionCuerpo extends InstruccionAbstracta {

    public InstruccionCuerpo(Nodo instruccion, boolean permiteInter) {
        super(instruccion, permiteInter);
    }

    @Override
    public Resultado ejecutar(Ambito ctx, int nivel) {
        if (!instruccion.esDeRol(Constantes.CUERPO)) {
            return new Resultado();
        }
        Object[] cuerpo = instruccion.getHijos().toArray();
        for (Object objNodo : cuerpo) {
            Nodo nodo = (Nodo) objNodo;
            switch (nodo.getRol()) {
                case Constantes.DECLARACION:
                    break;
                case Constantes.ASIGNACION:
                    break;
                case Constantes.RETORNO:
                    break;
                case Constantes.MOSTRAR:
                    break;
                case Constantes.SI:
                    break;
                case Constantes.SELECCIONA:
                    break;
                case Constantes.MIENTRAS:
                    break;
                case Constantes.PARA:
                    break;
                case Constantes.DETENER:
                    break;
                case Constantes.CONTINUAR:
                    break;
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
