package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.Variable;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionAsignacion extends InstruccionAbstracta {

    public InstruccionAsignacion(Nodo instruccion) {
        super(instruccion, false);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        String nombre = instruccion.getCadena();
        if (!existeVariable(ctx, nombre)) {
            //Error, la variable destino no existe
            return FabricaResultado.creaFAIL();
        }
        Nodo exp = instruccion.getHijo(0);
        Resultado res = new Expresion(exp).resolver(ctx);
        int tipoRes = res.getTipo();
        if (tipoRes == Constantes.T_ERROR) {
            //Error en la evaluacion de la expresion
            return FabricaResultado.creaFAIL();
        }
        Variable destino = obtenerVariable(ctx, nombre);
        int tipoDest = destino.getTipo();
        if (!asignacionValida(tipoDest, tipoRes)) {
            //Error en los tipos de la asignación
            return FabricaResultado.creaFAIL();
        }
        destino.setValor(res.getValor());
        return FabricaResultado.creaOK();
    }

}
