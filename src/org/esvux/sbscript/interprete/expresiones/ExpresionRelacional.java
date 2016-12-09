package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class ExpresionRelacional extends ExpresionAbstracta {

    public ExpresionRelacional(Nodo izq, Nodo der, int operando) {
        super(izq, der, operando);
    }

    @Override
    public Resultado resolver(Contexto ctx) {
        Resultado resIzq = new Expresion(izq).resolver(ctx);
        Resultado resDer = new Expresion(der).resolver(ctx);
        int tipoIzq = resIzq.getTipo();
        int tipoDer = resDer.getTipo();
        if (tipoIzq == Constantes.T_NUM && tipoIzq == tipoDer) {
            return resolverNumeros(resIzq, resDer, ctx);
        }
        if (tipoIzq == Constantes.T_STR && tipoIzq == tipoDer) {
            return resolverCadenas(resIzq, resDer, ctx);
        }
        return new Resultado();
    }
    
    private Resultado resolverNumeros(Resultado resIzq, Resultado resDer, Contexto ctx){
        double dblIzq = Double.parseDouble(resIzq.getValor());
        double dblDer = Double.parseDouble(resDer.getValor());
        boolean res = false;
        switch(operando){
            case Constantes.OPR_EQU:
                res = dblIzq == dblDer;
                break;
            case Constantes.OPR_NEQ:
                res = dblIzq != dblDer;
                break;
            case Constantes.OPR_MAY:
                res = dblIzq > dblDer;
                break;
            case Constantes.OPR_MEN:
                res = dblIzq < dblDer;
                break;
            case Constantes.OPR_MYE:
                res = dblIzq >= dblDer;
                break;
            case Constantes.OPR_MNE:
                res = dblIzq <= dblDer;
                break;
        }
        return Resultado.creaBooleano(convertirBooleano(res));
    }

    private Resultado resolverCadenas(Resultado resIzq, Resultado resDer, Contexto ctx){
        int comparacion = resIzq.getValor().compareTo(resDer.getValor());
        boolean res = false;
        switch(operando){
            case Constantes.OPR_EQU:
                res = comparacion == 0;
                break;
            case Constantes.OPR_NEQ:
                res = comparacion != 0;
                break;
            case Constantes.OPR_MAY:
                res = comparacion > 0;
                break;
            case Constantes.OPR_MEN:
                res = comparacion < 0;
                break;
            case Constantes.OPR_MYE:
                res = comparacion >= 0;
                break;
            case Constantes.OPR_MNE:
                res = comparacion <= 0;
                break;
        }
        return Resultado.creaBooleano(convertirBooleano(res));
    }

}
