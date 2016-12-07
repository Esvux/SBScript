package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
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
    public Resultado resolver(Contexto ctx) {
        int rol = izq.getRol();
        int subrol = izq.getSubrol();
        if (rol == Constantes.ARITMETICA_UNARIA) {
            ExpresionAritmetica exp = new ExpresionAritmetica(izq.getHijo(0), subrol);
            return exp.resolver(ctx);
        }
        if (rol == Constantes.ARITMETICA) {
            ExpresionAritmetica exp = new ExpresionAritmetica(izq.getHijo(0), izq.getHijo(1), subrol);
            return exp.resolver(ctx);
        }
        if (rol == Constantes.LOGICA_UNARIA) {
            ExpresionLogica exp = new ExpresionLogica(izq.getHijo(0), operando);
            return exp.resolver(ctx);
        }
        if (rol == Constantes.LOGICA) {
            ExpresionLogica exp = new ExpresionLogica(izq.getHijo(0), izq.getHijo(1), subrol);
            return exp.resolver(ctx);
        }
        if (rol == Constantes.RELACIONAL) {
            ExpresionRelacional exp = new ExpresionRelacional(izq.getHijo(0), izq.getHijo(1), subrol);
            return exp.resolver(ctx);
        }
        if (rol == Constantes.VARIABLE) {
            
        }
        if (izq.esDeRol(Constantes.NUMERO, Constantes.CADENA, Constantes.TRUE, Constantes.FALSE)) {
            return new Resultado(izq.getCadena(), izq.getTipo());
        }
        return new Resultado();
    }

    
}
