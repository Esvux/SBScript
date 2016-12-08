package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Ambito;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class Expresion extends ExpresionAbstracta {

    public Expresion(Nodo izq) {
        super(izq, null, 0);
    }

    @Override
    public Resultado resolver(Ambito ctx) {
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
            case Constantes.VARIABLE:
                //Resolver variable con el contexto actual
                break;
            default:
                if (izq.esDeRol(Constantes.NUMERO, Constantes.CADENA, Constantes.TRUE, Constantes.FALSE)) {
                    res = new Resultado(izq.getCadena(), izq.getTipo());
                }
        }
        return res;
    }

}
