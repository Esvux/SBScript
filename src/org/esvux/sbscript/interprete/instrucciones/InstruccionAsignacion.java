package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.errores.Errores;
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
            Errores.getInstance().nuevoErrorSemantico(instruccion.getLinea(), instruccion.getColumna(), 
                    "La variable destino '"+nombre+"' no existe.");
            return FabricaResultado.creaFAIL();
        }
        Nodo exp = instruccion.getHijo(0);
        Resultado res = new Expresion(exp).resolver(ctx);
        int tipoRes = res.getTipo();
        if (tipoRes == Constantes.T_ERROR) {
            Errores.getInstance().nuevoErrorSemantico(exp.getLinea(), exp.getColumna(), 
                    "Existe un error en la evaluacion de la expresion.");
            return FabricaResultado.creaFAIL();
        }
        Variable destino = obtenerVariable(ctx, nombre);
        int tipoDest = destino.getTipo();
        if (!asignacionValida(tipoDest, tipoRes)) {
            Errores.getInstance().nuevoErrorSemantico(instruccion.getLinea(), instruccion.getColumna(), 
                    "El tipo de la variable '"+nombre+"' no coincide con el valor que se desea asignar.");
            return FabricaResultado.creaFAIL();
        }
        destino.setValor(res.getValor());
        return FabricaResultado.creaOK();
    }

}
