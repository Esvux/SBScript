package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
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
        double dblIzq = resIzq.getDouble();
        double dblDer = resDer.getDouble();
        boolean bool = false;
        switch(operando){
            case Constantes.OPR_EQU:
                bool = dblIzq == dblDer;
                break;
            case Constantes.OPR_NEQ:
                bool = dblIzq != dblDer;
                break;
            case Constantes.OPR_MAY:
                bool = dblIzq > dblDer;
                break;
            case Constantes.OPR_MEN:
                bool = dblIzq < dblDer;
                break;
            case Constantes.OPR_MYE:
                bool = dblIzq >= dblDer;
                break;
            case Constantes.OPR_MNE:
                bool = dblIzq <= dblDer;
                break;
        }
        return FabricaResultado.creaBooleano(bool);
    }

    private Resultado resolverCadenas(Resultado resIzq, Resultado resDer, Contexto ctx){
        int comparacion = resIzq.getValor().compareTo(resDer.getValor());
        boolean bool = false;
        switch(operando){
            case Constantes.OPR_EQU:
                bool = comparacion == 0;
                break;
            case Constantes.OPR_NEQ:
                bool = comparacion != 0;
                break;
            case Constantes.OPR_MAY:
                bool = comparacion > 0;
                break;
            case Constantes.OPR_MEN:
                bool = comparacion < 0;
                break;
            case Constantes.OPR_MYE:
                bool = comparacion >= 0;
                break;
            case Constantes.OPR_MNE:
                bool = comparacion <= 0;
                break;
        }
        return FabricaResultado.creaBooleano(bool);
    }

}
