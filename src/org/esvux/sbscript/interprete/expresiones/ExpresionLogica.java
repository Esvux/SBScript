package org.esvux.sbscript.interprete.expresiones;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.Contexto;
import org.esvux.sbscript.interprete.FabricaResultado;
import org.esvux.sbscript.interprete.Resultado;

/**
 *
 * @author esvux
 */
public class ExpresionLogica extends ExpresionAbstracta {

    public ExpresionLogica(Nodo izq, Nodo der, int operando) {
        super(izq, der, operando);
    }

    public ExpresionLogica(Nodo izq, int operando) {
        super(izq, null, operando);
    }

    @Override
    public Resultado resolver(Contexto ctx) {
        if (der == null) {
            return resolverUnaria(ctx);
        }
        return resolverBinaria(ctx);
    }

    private Resultado resolverBinaria(Contexto ctx) {
        Resultado resIzq = new Expresion(izq).resolver(ctx);
        Resultado resDer = new Expresion(der).resolver(ctx);
        int tipoIzq = resIzq.getTipo();
        int tipoDer = resDer.getTipo();
        if (!(tipoIzq == Constantes.T_BOOL && tipoIzq == tipoDer)) {
            return new Resultado();
        }
        boolean blnIzq = resIzq.getBooleano();
        boolean blnDer = resDer.getBooleano();
        switch (operando) {
            case Constantes.OPL_AND:
                blnIzq = Boolean.logicalAnd(blnIzq, blnDer);
                break;
            case Constantes.OPL_OR:
                blnIzq = Boolean.logicalOr(blnIzq, blnDer);
                break;
            case Constantes.OPL_XOR:
                blnIzq = Boolean.logicalXor(blnIzq, blnDer);
                break;
            default:
                Errores.getInstance().nuevoErrorSemantico(der.getLinea(), der.getColumna(),
                        "Operador fuera de control.");

        }
        return FabricaResultado.creaBooleano(blnIzq);
    }

    private Resultado resolverUnaria(Contexto ctx) {
        Resultado resIzq = new Expresion(izq).resolver(ctx);
        if (resIzq.getTipo() != Constantes.T_BOOL) {
            return new Resultado();
        }
        if (operando == Constantes.OPA_MEN) {
            return resolverNot(resIzq);
        }
        return new Resultado();
    }

    private Resultado resolverNot(Resultado resIzq) {
        boolean bool = resIzq.getBooleano();
        bool = !bool;
        return FabricaResultado.creaBooleano(bool);
    }

}
