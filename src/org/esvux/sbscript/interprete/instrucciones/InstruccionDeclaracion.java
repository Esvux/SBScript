package org.esvux.sbscript.interprete.instrucciones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.Variable;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionDeclaracion extends InstruccionAbstracta {

    public InstruccionDeclaracion(Nodo instruccion, boolean permiteInter) {
        super(instruccion, permiteInter);
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
                //Error en la expresion, las variables existiran, pero con valor null
            } else if (tipoRes != tipo) {
                //Error de casteo en la asignacion, las variables existiran, pero con valor null
            } else {
                valor = res.getValor();
            }
        }
        for (String nombre : instruccion.getListaAux()) {
            Variable var = new Variable(nombre, valor, tipo, nivel);
            if (nivel == Constantes.GLOBAL) {
                if(existeVariableGlobal(nombre)){
                    //Reportar error, redefiniendo una variable global
                    continue;
                }
                crearVariableGlobal(var);
            }else{
                if(existeVariableLocal(ctx, nombre)){
                    //Reportar error, redefiniendo una variable local
                    continue;                    
                }
                crearVariableLocal(ctx, var);
            }
        }
        return Resultado.creaOK();
    }
    
    private void crearVariableLocal(Contexto ctx, Variable nueva) {
        ctx.addVariable(nueva);
    }

    private void crearVariableGlobal(Variable nueva) {
        contextoGlobal.addVariable(nueva);
    }

    private boolean existeVariableLocal(Contexto ctx, String nombre) {
        return ctx.existeVariable(nombre);
    }

    private boolean existeVariableGlobal(String nombre) {
        return contextoGlobal.existeVariable(nombre);
    }

}
