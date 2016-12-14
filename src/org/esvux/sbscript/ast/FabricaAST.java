package org.esvux.sbscript.ast;

import java.util.ArrayList;

/**
 * Siguiendo el patron de diseño de Factory, esta clase es la encargada de la
 * creacion de nodos de forma predeterminada, otorgando la facilidad de crear
 * un nodo especifico (controlando cosas como el rol, subrol, etc) por medio
 * de metodos estaticos con un nombre muy descriptivo.
 * @author esvux
 */
public abstract class FabricaAST {

    /**
     * Crea una copia exacta del nodo que recibe como parametro, pero con otra 
     * direccion de memoria.
     * @param nodo Nodo a ser copiado.
     * @return La copia del nodo.
     */
    public static Nodo copiar(Nodo nodo) {
        Nodo copia = new Nodo();
        copia.setRol(nodo.getRol());
        copia.setSubrol(nodo.getSubrol());
        copia.setTipo(nodo.getTipo());
        copia.setCadena(nodo.getCadena());
        copia.setHijos(new ArrayList<>(nodo.getHijos()));
        if (nodo.getListaAux() != null) {
            copia.setListaAux(new ArrayList<>(nodo.getListaAux()));
        }
        return copia;
    }

    /**
     * Segun el parametro tipo, se crea un nuevo nodo hoja con la ayuda de otros
     * metodos de la misma clase.
     * @param valor El valor que llevara la cadena del nuevo nodo.
     * @param tipo El tipo de dato que llevara el nuevo nodo.
     * @return El nodo hoja creado (si tipo no coincide con los tipos definidos
     * en el lenguaje, se retorna un nodo error).
     */
    public static Nodo creaHoja(String valor, int tipo) {
        switch (tipo) {
            case Constantes.T_NUM:
                return creaNumero(valor);
            case Constantes.T_STR:
                return creaCadena(valor);
            case Constantes.T_BOOL:
                if (valor.compareTo(Constantes.VAL_TRUE) == 0) {
                    return creaTrue();
                }
                if (valor.compareTo(Constantes.VAL_FALSE) == 0) {
                    return creaFalse();
                }
        }
        return new Nodo();
    }

    /**
     * Crea un nodo de rol Constantes.NUMERO, tipo Contantes.T_NUM y con el valor
     * del numero asignado en el atributo cadena.
     * @param numero Cadena que representa el valor del numero.
     * @return El nodo NUMERO.
     */
    public static Nodo creaNumero(String numero) {
        Nodo nodo = new Nodo(Constantes.NUMERO, Constantes.T_NUM, numero);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.CADENA, tipo Contantes.T_STR y con el valor
     * de la cadena (sin comillas dobles) asignado en el atributo cadena.
     * @param cadena La cadena que se desea guardar.
     * @return El nodo CADENA.
     */
    public static Nodo creaCadena(String cadena) {
        Nodo nodo = new Nodo(Constantes.CADENA, Constantes.T_STR, cadena);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.TRUE, tipo Contantes.T_BOOL y con cadena 
     * Constantes.VAL_TRUE.
     * @return El nodo TRUE.
     */
    public static Nodo creaTrue() {
        Nodo nodo = new Nodo(Constantes.TRUE, Constantes.T_BOOL, Constantes.VAL_TRUE);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.FALSE, tipo Contantes.T_BOOL y con cadena 
     * Constantes.VAL_FALSE.
     * @return El nodo FALSE.
     */
    public static Nodo creaFalse() {
        Nodo nodo = new Nodo(Constantes.FALSE, Constantes.T_BOOL, Constantes.VAL_FALSE);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.VARIABLE y con el id almacenado en el 
     * atributo cadena.
     * @param id Identificador asociado al acceso.
     * @return El nodo ACCESO.
     */
    public static Nodo creaAccesoID(String id) {
        Nodo nodo = new Nodo(Constantes.VARIABLE, id);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.ARITMETICA_UNARIA y subrol Constantes.OPA_MEN,
     * y coloca al nodo que recibe como parametro del nodo recien creado.
     * @param val Nodo que va a ser afectado por el menos.
     * @return El nodo MENOS(UNARIO).
     */
    public static Nodo creaMenosUnario(Nodo val) {
        Nodo nodo = new Nodo(Constantes.ARITMETICA_UNARIA, "-(unario)");
        nodo.setSubrol(Constantes.OPA_MEN);
        nodo.addHijo(val);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.ARITMETICA, cambia el subrol por el valor
     * recibido en el parametro op; agrega a izq y a der, a sus hijos en los 
     * indice 0 y 1 respectivamente.
     * @param operador Cadena que representa la operacion, "+", "*", etc.
     * @param op Subrol que se asignara como representacion de la operacion.
     * @param izq Nodo que representa al subarbol izquierdo de la operacion.
     * @param der Nodo que representa al subarbol derecho de la operacion.
     * @return El nodo ARITMETICO.
     */
    public static Nodo creaAritmetica(String operador, int op, Nodo izq, Nodo der) {
        Nodo nodo = new Nodo(Constantes.ARITMETICA, operador);
        nodo.setSubrol(op);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.RELACIONAL, cambia el subrol por el valor
     * recibido en el parametro op; agrega a izq y a der, a sus hijos en los 
     * indice 0 y 1 respectivamente.
     * @param operador Cadena que representa la operacion, "+", "*", etc.
     * @param op Subrol que se asignara como representacion de la operacion.
     * @param izq Nodo que representa al subarbol izquierdo de la operacion.
     * @param der Nodo que representa al subarbol derecho de la operacion.
     * @return El nodo RELACIONAL.
     */
    public static Nodo creaRelacional(String operador, int op, Nodo izq, Nodo der) {
        Nodo nodo = new Nodo(Constantes.RELACIONAL, operador);
        nodo.setSubrol(op);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.LOGICA, cambia el subrol por el valor
     * recibido en el parametro op; agrega a izq y a der, a sus hijos en los 
     * indice 0 y 1 respectivamente.
     * @param operador Cadena que representa la operacion, "+", "*", etc.
     * @param op Subrol que se asignara como representacion de la operacion.
     * @param izq Nodo que representa al subarbol izquierdo de la operacion.
     * @param der Nodo que representa al subarbol derecho de la operacion.
     * @return El nodo LOGICO.
     */    
    public static Nodo creaLogica(String operador, int op, Nodo izq, Nodo der) {
        Nodo nodo = new Nodo(Constantes.LOGICA, operador);
        nodo.setSubrol(op);
        nodo.addHijo(izq);
        nodo.addHijo(der);
        return nodo;
    }

    /**
     * Crea un nodo de rol Constantes.LOGICA_UNARIA, cambia el subrol por 
     * Constantes.OPL_NOT y agrega el nodo negado a los hijos del nodo principal.
     * @param negado Nodo que sera negado logicamente.
     * @param str Cadena que representa al operador de negacion.
     * @return El nodo LOGICO DE LA NEGACION.
     */
    public static Nodo creaNot(Nodo negado, String str) {
        Nodo nodo = new Nodo(Constantes.LOGICA_UNARIA, str);
        nodo.setSubrol(Constantes.OPL_NOT);
        nodo.addHijo(negado);
        return nodo;
    }

    /**
     * Crea un nodo con rol Constantes.CUERPO, sin hijos.
     * @return El nodo CUERPO.
     */
    public static Nodo creaCuerpo() {
        Nodo nodo = new Nodo(Constantes.CUERPO, "Cuerpo");
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.DECLARACION, con el tipo indicado por el 
     * parametro tipo; Inicia la lista auxiliar de cadenas y agrega el id recibido
     * como parametro.
     * @param tipo El tipo que se reconocio en la declaracion.
     * @param id Primer (o unico) identificador reconocido en la declaracion.
     * @return El nodo DECLARACION.
     */
    public static Nodo creaDeclaracion(int tipo, String id) {
        Nodo nodo = new Nodo(Constantes.DECLARACION, tipo, "Declara");
        nodo.initListaAux();
        nodo.addAux(id);
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.ASIGNACION, con el identificador asociado
     * a la asignacion almacenado en el atributo cadena y con el nodo exp como 
     * unico hijo.
     * @param id Identificador de la variable que recibira el valor.
     * @param exp Expresion que representa el valor que recibira la variable.
     * @return El nodo ASIGNACION.
     */
    public static Nodo creaAsignacion(String id, Nodo exp) {
        Nodo nodo = new Nodo(Constantes.ASIGNACION, id);
        nodo.addHijo(exp);
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.LLAMADA, con el identificador asociado al
     * metodo, objetivo de la llamada, en el atributo cadena.
     * @param id Identificador del metodo llamado.
     * @return El nodo LLAMADA.
     */
    public static Nodo creaLlamada(String id) {
        Nodo nodo = new Nodo(Constantes.LLAMADA, id);
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.RETORNO, con la expresion que retornara 
     * como unico hijo.
     * @param exp Expresion que retorna la instruccion.
     * @return El nodo RETORNO.
     */
    public static Nodo creaRetorno(Nodo exp) {
        Nodo nodo = new Nodo(Constantes.RETORNO, "Retorno");
        nodo.addHijo(exp);
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.MOSTRAR, con la primera (y posiblemente
     * unica) expresion que mostrara como hijo.
     * @param exp Expresion que mostrara la instruccion.
     * @return El nodo MOSTRAR.
     */
    public static Nodo creaMostrar(Nodo exp) {
        Nodo nodo = new Nodo(Constantes.MOSTRAR, "Mostrar");
        nodo.addHijo(exp);
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.SI, su primer hijo sera la condicion, el 
     * segundo hijo el cuerpo en caso de que la condicion se cumpla (then) y agrega
     * el tercer hijo (cuerpo en caso de que NO se cumpla la condicion) si el 
     * parametro cuerpoElse es distinto de null.
     * @param cond Nodo que representa la expresion que fungira como condicion.
     * @param cuerpoThen Nodo cuerpo asociado al SI
     * @param cuerpoElse null o Nodo cuerpo asociado al SINO
     * @return El nodo SI.
     */
    public static Nodo creaSi(Nodo cond, Nodo cuerpoThen, Nodo cuerpoElse) {
        Nodo nodo = new Nodo(Constantes.SI, "Si[Sino]");
        nodo.addHijo(cond);
        nodo.addHijo(cuerpoThen);
        if (cuerpoElse != null) {
            nodo.addHijo(cuerpoElse);
        }
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.SELECCIONA, sin hijos.
     * @return El nodo SELECCIONA.
     */
    public static Nodo creaSelecciona() {
        Nodo nodo = new Nodo(Constantes.SELECCIONA, "Selecciona");
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.CASO, crea adicionalmente un nuevo nodo
     * para representar la condicion del caso; agrega dicha condicion y el cuerpo
     * como hijos en las posiciones 0 y 1 respectivamente.
     * @param exp Expresion del lado izquierdo de la comparacion (igualdad).
     * @param val Valor nativo del lado derecho de la comparacion (igualdad).
     * @param cuerpo El cuerpo asociado al caso.
     * @return El nodo CASO.
     */
    public static Nodo creaCaso(Nodo exp, Nodo val, Nodo cuerpo) {
        Nodo nodo = new Nodo(Constantes.CASO, "Caso");
        Nodo cond = creaRelacional("==", Constantes.OPR_EQU, exp, val);
        nodo.addHijo(cond);
        nodo.addHijo(cuerpo);
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.MIENTRAS, asigna la condicion y el cuerpo
     * como hijos 0 y 1 respectivamente.
     * @param cond Nodo que representa la condicion asociada a la instruccion.
     * @param cuerpo Nodo cuerpo asociado al MIENTRAS.
     * @return El nodo MIENTRAS.
     */
    public static Nodo creaMientras(Nodo cond, Nodo cuerpo) {
        Nodo nodo = new Nodo(Constantes.MIENTRAS, "Mientras");
        nodo.addHijo(cond);
        nodo.addHijo(cuerpo);
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.PARA, asigna en el subrol el tipo de operacion
     * que se realizara despues de cada iteracion sobre la variable que asociada al ciclo
     * (Constantes:INCREMENTO o Constantes.DECREMENTO); Agrega como hijos al nodo
     * declaracion, luego la condicion, seguido del cuerpo y por ultimo crea una 
     * asignacion para realizar la operacion de incremento o decremento.
     * @param dec Nodo declaracion de la variable asociada al ciclo.
     * @param cond Nodo condicion (expresion).
     * @param cuerpo Nodo cuerpo asociado al PARA.
     * @param id Identificador de la variable asociada al ciclo.
     * @param subrol Tipo de modificacion a la variable (incremento o decremento.
     * @return El nodo PARA.
     */
    public static Nodo creaPara(Nodo dec, Nodo cond, Nodo cuerpo, String id, int subrol) {
        Nodo nodo = new Nodo(Constantes.PARA, "Para");
        nodo.setSubrol(subrol);
        nodo.addHijo(dec);
        nodo.addHijo(cond);
        nodo.addHijo(cuerpo);
        Nodo exp;
        if (subrol == Constantes.INCREMENTO) {
            exp = creaAritmetica("+", Constantes.OPA_SUM, creaAccesoID(id), creaNumero("1"));
        } else {
            exp = creaAritmetica("-", Constantes.OPA_RES, creaAccesoID(id), creaNumero("1"));
        }
        nodo.addHijo(creaAsignacion(id, exp));
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.DETENER, sin hijos.
     * @return El nodo DETENER.
     */
    public static Nodo creaDetener() {
        Nodo nodo = new Nodo(Constantes.DETENER, "Detener");
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.CONTINUAR, sin hijos.
     * @return El nodo CONTINUAR.
     */
    public static Nodo creaContinuar() {
        Nodo nodo = new Nodo(Constantes.CONTINUAR, "Continuar");
        return nodo;
    }

    /**
     * Crea el nodo con rol Constantes.PARAMETRO, con el nombre y tipo del
     * parametro declarado.
     * @param nombre Nombre del parametro
     * @param tipo Tipo del parametro
     * @return El nodo PARAMETRO.
     */
    public static Nodo creaParametro(String nombre, int tipo) {
        Nodo nodo = new Nodo(Constantes.PARAMETRO, tipo, nombre);
        return nodo;
    }

    /**
     * Genera un nuevo metodo de nombre 'Principal' y tipo Constantes.T_VOID con
     * el cuerpo del metodo vacio.
     * @return El metodo principal.
     */
    public static Metodo creaPrincipal() {
        Metodo metodo = new Metodo("Principal", Constantes.T_VOID);
        return metodo;
    }

    /**
     * Genera un nuevo metodo con nombre y tipo especificados por los parametros
     * que recibe y con el cuerpo del metodo vacio.
     * @param nombre Nombre del metodo.
     * @param tipo Tipo del metodo (inclusive Constantes.T_VOID).
     * @return El metodo declarado.
     */
    public static Metodo creaMetodo(String nombre, int tipo) {
        Metodo metodo = new Metodo(nombre, tipo);
        return metodo;
    }

}
