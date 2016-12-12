package org.esvux.sbscript.interprete.instrucciones;

import java.util.ArrayList;
import java.util.List;
import org.esvux.sbscript.ast.FabricaAST;
import org.esvux.sbscript.ast.Metodo;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Interprete;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.expresiones.Expresion;

/**
 *
 * @author esvux
 */
public class InstruccionLlamada extends InstruccionAbstracta {

    public InstruccionLlamada(Nodo instruccion) {
        super(instruccion, false);
    }

    @Override
    public Resultado ejecutar(Contexto ctx, int nivel) {
        String nombre = instruccion.getCadena();
        Metodo metodo = Interprete.getMetodo(nombre);
        if (metodo == null) {
            //Error, El metodo 'nombre' no ha sido definido.
            return FabricaResultado.creaFAIL();
        }
        int cantParametros = instruccion.getCantidadHijos();
        if (metodo.getCantidadParametros() != cantParametros) {
            //Error, La cantidad de parametros no coincide.
            return FabricaResultado.creaFAIL();
        }
        List<Nodo> parametros = new ArrayList<>(metodo.getParametros());
        Contexto local = new Contexto();
        for (int i = 0; i < cantParametros; i++) {
            Nodo nodoValor = instruccion.getHijo(i);
            Resultado valor = new Expresion(nodoValor).resolver(ctx);
            Nodo nodoParam = parametros.get(i);
            if (valor.esError()) {
                //Error, No se puede evaluar la expresion para el parametro 'nodoParam.getNombre()'
                return FabricaResultado.creaFAIL();
            }
            if (valor.getTipo() != nodoParam.getTipo()) {
                //Error, No coinciden los tipos en la evaluacion del parametro 'nodoParam.getNombre()'
                return FabricaResultado.creaFAIL();
            }
            nodoValor = FabricaAST.creaHoja(valor.getValor(), valor.getTipo());
            Nodo declara = creaDeclaracion(nodoParam, nodoValor);
            new InstruccionDeclaracion(declara).ejecutar(local, nivel);
        }
        InstruccionCuerpo instr = new InstruccionCuerpo(metodo.getCuerpo(), false);
        Resultado ejecucion = instr.ejecutar(local, nivel);
        if (ejecucion.getTipo() != metodo.getTipo()) {
            //Error, El tipo del metodo no coincide con el tipo retornado
            return FabricaResultado.creaFAIL();
        }
        return ejecucion;
    }

    private Nodo creaDeclaracion(Nodo parametro, Nodo expresion) {
        Nodo declaracion = FabricaAST.creaDeclaracion(parametro.getTipo(), parametro.getCadena());
        declaracion.addHijo(expresion);
        return declaracion;
    }

}
