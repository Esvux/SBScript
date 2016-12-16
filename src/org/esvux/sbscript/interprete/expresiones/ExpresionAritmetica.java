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
public class ExpresionAritmetica extends ExpresionAbstracta {

    public ExpresionAritmetica(Nodo izq, Nodo der, int operando) {
        super(izq, der, operando);
    }

    public ExpresionAritmetica(Nodo izq, int operando) {
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
        if (resIzq.esError()) {
            return new Resultado();
        }
        Resultado resDer = new Expresion(der).resolver(ctx);
        if (resDer.esError()) {
            return new Resultado();
        }
        if (operando == Constantes.OPA_SUM) {
            return resolverSuma(resIzq, resDer);
        } else if (Constantes.esAlgunoDeEstos(operando,
                Constantes.OPA_RES,
                Constantes.OPA_MUL,
                Constantes.OPA_DIV,
                Constantes.OPA_MOD,
                Constantes.OPA_POT)) {
            return resolverNumerica(resIzq, resDer);
        }
        return new Resultado();
    }

    private Resultado resolverUnaria(Contexto ctx) {
        Resultado resIzq = new Expresion(izq).resolver(ctx);
        if (resIzq.getTipo() != Constantes.T_NUM) {
            return new Resultado();
        }
        if (operando == Constantes.OPA_MEN) {
            return resolverMenos(resIzq);
        }
        return new Resultado();
    }

    private Resultado resolverSuma(Resultado resIzq, Resultado resDer) {
        int tipo = validarTipos(resIzq.getTipo(), resDer.getTipo(), Constantes.MT_SUMA);
        //Suma aritmética
        if (tipo == Constantes.T_NUM) {
            return resolverNumerica(resIzq, resDer);
        }
        //Concatenación
        if (tipo == Constantes.T_STR) {
            return FabricaResultado.creaCadena(resIzq.getValor().concat(resDer.getValor()));
        }
        return new Resultado();
    }

    private Resultado resolverNumerica(Resultado resIzq, Resultado resDer) {
        double dblIzq = resIzq.getDouble();
        double dblDer = resDer.getDouble();
        switch (operando) {
            case Constantes.OPA_SUM:
                dblIzq += dblDer;
                break;
            case Constantes.OPA_RES:
                dblIzq -= dblDer;
                break;
            case Constantes.OPA_MUL:
                dblIzq *= dblDer;
                break;
            case Constantes.OPA_DIV:
                if (dblDer == 0) {
                    Errores.getInstance().nuevoErrorSemantico(der.getLinea(), der.getColumna(),
                            "No es posible realizar una division entre 0.");
                    return new Resultado();
                }
                dblIzq /= dblDer;
                break;
            case Constantes.OPA_MOD:
                if (dblDer == 0) {
                    Errores.getInstance().nuevoErrorSemantico(der.getLinea(), der.getColumna(),
                            "No es posible realizar una operacion de modulo entre 0.");
                    return new Resultado();
                }
                int mod = (int) dblIzq % (int) dblDer;
                return FabricaResultado.creaNumero(String.valueOf(mod));
            case Constantes.OPA_POT:
                dblIzq = Math.pow(dblIzq, dblDer);
                break;
        }
        return FabricaResultado.creaNumero(String.valueOf(dblIzq));
    }

    private Resultado resolverMenos(Resultado resIzq) {
        double dblIzq = resIzq.getDouble();
        dblIzq *= -1;
        return FabricaResultado.creaNumero(String.valueOf(dblIzq));
    }

}
