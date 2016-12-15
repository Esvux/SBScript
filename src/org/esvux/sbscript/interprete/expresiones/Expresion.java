package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;
import org.esvux.sbscript.interprete.Variable;
import org.esvux.sbscript.interprete.instrucciones.InstruccionAbstracta;
import org.esvux.sbscript.interprete.instrucciones.InstruccionLlamada;

/**
 *
 * @author esvux
 */
public class Expresion extends ExpresionAbstracta {

    public Expresion(Nodo izq) {
        super(izq, null, 0);
    }

    @Override
    public Resultado resolver(Contexto ctx) {
        int rol = izq.getRol();
        int subrol = izq.getSubrol();
        Resultado res = new Resultado();
        switch (rol) {
            case Constantes.ARITMETICA_UNARIA:
                res = new ExpresionAritmetica(izq.getHijo(0), subrol).resolver(ctx);
                break;
            case Constantes.ARITMETICA:
                res = new ExpresionAritmetica(izq.getHijo(0), izq.getHijo(1), subrol).resolver(ctx);
                break;
            case Constantes.LOGICA_UNARIA:
                res = new ExpresionLogica(izq.getHijo(0), operando).resolver(ctx);
                break;
            case Constantes.LOGICA:
                res = new ExpresionLogica(izq.getHijo(0), izq.getHijo(1), subrol).resolver(ctx);
                break;
            case Constantes.RELACIONAL:
                res = new ExpresionRelacional(izq.getHijo(0), izq.getHijo(1), subrol).resolver(ctx);
                break;
            case Constantes.LLAMADA:
                res = new InstruccionLlamada(izq).ejecutar(ctx, 1);
                break;
            case Constantes.VARIABLE:
                String nombre = izq.getCadena();
                Variable var = InstruccionAbstracta.obtenerVariable(ctx, nombre);
                if(var==null){
                    Errores.getInstance().nuevoErrorSemantico(izq.getLinea(), izq.getColumna(),
                        "La variable '" + nombre + "' no existe.");
                    return new Resultado();
                }
                if(var.getValor()==null){
                    Errores.getInstance().nuevoErrorSemantico(izq.getLinea(), izq.getColumna(),
                        "La variable '" + nombre + "' no tiene ningun valor asignado.");
                    return new Resultado();
                }
                res = new Resultado(var.getValor(), var.getTipo());
                break;
            default:
                String val = izq.getCadena();
                if (izq.esDeRol(Constantes.NUMERO, Constantes.TRUE, Constantes.FALSE)) {
                    res = new Resultado(val, izq.getTipo());
                }else if (izq.getTipo()==Constantes.CADENA) {
                    res = new Resultado(val.substring(1, val.length()-1), izq.getTipo());
                }
        }
        return res;
    }

}
