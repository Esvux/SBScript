package org.esvux.sbscript.errores;

import org.esvux.sbscript.ast.Constantes;

/**
 *
 * @author esvux
 */
class SBError {

    int linea;
    int columna;
    int tipo;
    String descripcion;

    public SBError(int linea, int columna, int tipo, String descripcion) {
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        String ubicacion = (linea < 0 || columna < 0) ? "[sin ubicacion]" : ("[" + linea + ":" + columna + "]");
        return Constantes.ERRORES[tipo] + ubicacion + " - " + descripcion;
    }

}
