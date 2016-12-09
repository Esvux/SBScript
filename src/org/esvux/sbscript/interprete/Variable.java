package org.esvux.sbscript.interprete;

import org.esvux.sbscript.ast.Constantes;

/**
 *
 * @author esvux
 */
public class Variable {

    private String nombre;
    private String valor;
    private int tipo;
    private int nivel;

    public Variable(String nombre, String valor, int tipo, int ambito) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
        this.nivel = ambito;
    }

    public Variable(String nombre, int tipo, int ambito) {
        this(nombre, null, tipo, ambito);
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public boolean esGlobal(){
        return this.nivel == Constantes.GLOBAL;
    }

    @Override
    public String toString() {
        return "Variable{" + "nombre=" + nombre + ", valor=" + valor + ", tipo=" + tipo + ", nivel=" + nivel + '}';
    }    

}
