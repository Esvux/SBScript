package org.esvux.sbscript.errores;

import java.util.ArrayList;
import java.util.List;
import org.esvux.sbscript.ast.Constantes;

/**
 *
 * @author esvux
 */
public class Errores {

    private static Errores errores = null;

    public static Errores resetInstance() {
        errores = new Errores();
        return errores;
    }

    public static Errores getInstance() {
        if (errores == null) {
            errores = new Errores();
        }
        return errores;
    }

    private List<SBError> listaErrores = null;

    private Errores() {
        listaErrores = new ArrayList<>();
    }

    public String[] getReporteErrores() {
        int cant = listaErrores.size();
        String[] detalleErrores = new String[cant];
        for (int i = 0; i < cant; i++) {
            detalleErrores[i] = listaErrores.get(i).toString();
        }
        return detalleErrores;
    }

    public void nuevoErrorLexico(int linea, int columna, String descripcion) {
        SBError err = new SBError(linea, columna, Constantes.ERR_LEXICO, descripcion);
        listaErrores.add(err);
    }

    public void nuevoErrorSintactico(int linea, int columna, String descripcion) {
        SBError err = new SBError(linea, columna, Constantes.ERR_SINTACTICO, descripcion);
        listaErrores.add(err);
    }

    public void nuevoErrorSemantico(int linea, int columna, String descripcion) {
        SBError err = new SBError(linea, columna, Constantes.ERR_SEMANTICO, descripcion);
        listaErrores.add(err);
    }

    public void nuevoError(String descripcion) {
        SBError err = new SBError(-1, -1, Constantes.ERR_GENERAL, descripcion);
        listaErrores.add(err);
    }

    public int cuentaErrores() {
        return listaErrores.size();
    }

}
