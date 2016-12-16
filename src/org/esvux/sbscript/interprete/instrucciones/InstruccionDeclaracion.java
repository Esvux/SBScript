package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Interprete;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.Variable;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionDeclaracion extends InstruccionAbstracta {

    public InstruccionDeclaracion(Nodo instruccion) {
        super(instruccion, false);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        Nodo nodoExp = instruccion.getHijo(0);
        String valor = null;
        int tipo = instruccion.getTipo();
        if (nodoExp != null) {
            Resultado res = new Expresion(nodoExp).resolver(ctx);
            int tipoRes = res.getTipo();
            if (tipoRes == Constantes.T_ERROR) {
                Errores.getInstance().nuevoErrorSemantico(nodoExp.getLinea(), nodoExp.getColumna(),
                        "La expresion presenta errores, las variables existiran pero con valor nulo.");
            } else if (!asignacionValida(tipo, tipoRes)) {
                Errores.getInstance().nuevoErrorSemantico(nodoExp.getLinea(), nodoExp.getColumna(),
                        "El casteo de la asignacion es erroneo, las variables existiran pero con valor nulo.");
            } else {
                valor = res.getValor();
            }
        }
        for (String nombre : instruccion.getListaAux()) {
            Variable var = new Variable(nombre, valor, tipo, nivel);
            if (nivel == Constantes.GLOBAL) {
                if (existeVariableGlobal(nombre)) {
                    Errores.getInstance().nuevoErrorSemantico(instruccion.getLinea(), instruccion.getColumna(),
                            "Imposible redefinir la variable global '" + nombre + "'.");
                    continue;
                }
                crearVariableGlobal(var);
            } else {
                if (existeVariableLocal(ctx, nombre)) {
                    Errores.getInstance().nuevoErrorSemantico(instruccion.getLinea(), instruccion.getColumna(),
                            "Imposible redefinir la variable local '" + nombre + "'.");
                    continue;
                }
                crearVariableLocal(ctx, var);
            }
        }
        return FabricaResultado.creaOK();
    }

    private void crearVariableLocal(Contexto ctx, Variable nueva) {
        ctx.setVariable(nueva);
    }

    private void crearVariableGlobal(Variable nueva) {
        Interprete.getContextoGlobal().setVariable(nueva);
    }

    private boolean existeVariableLocal(Contexto ctx, String nombre) {
        return ctx.existeVariable(nombre);
    }

    private boolean existeVariableGlobal(String nombre) {
        return Interprete.getContextoGlobal().existeVariable(nombre);
    }

}
