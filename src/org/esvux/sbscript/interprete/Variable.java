package org.esvux.sbscript.interprete;

/**
 *
 * @author esvux
 */
public class Variable {

    private String nombre;
    private String valor;
    private int tipo;
    private int ambito;

    public Variable(String nombre, String valor, int tipo, int ambito) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
        this.ambito = ambito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getAmbito() {
        return ambito;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }

}
