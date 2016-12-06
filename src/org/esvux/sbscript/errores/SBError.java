package org.esvux.sbscript.errores;

import org.esvux.sbscript.ast.Constantes;

/**
 *
 * @author esvux
 */
class SBError {
    int fila;
    int columna;
    int tipo;
    String descripcion;

    public SBError(int fila, int columna, int tipo, String descripcion) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    public String[] detalle() {
        String detalle[] = 
        {
            Constantes.ERRORES[tipo] + ": " + descripcion,
            (fila < 0) ? "Sin ubicación" : "Fila: "+fila + "  Columna: " + columna 
        };
        return detalle;
    }
    
}
