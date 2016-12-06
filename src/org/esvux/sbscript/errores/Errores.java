package org.esvux.sbscript.errores;

import java.util.ArrayList;
import java.util.Iterator;
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
    
    public String[][] getReporteErrores(){
        int i = 0, cant = listaErrores.size();
        String [][] detalleErrores = new String[cant][2];
        Iterator<SBError> it = listaErrores.iterator();
        while(it.hasNext()){
            detalleErrores[i] = it.next().detalle();
            i++;
        }
        return detalleErrores;
    }

    public void nuevoErrorLexico(int fila, int columna, String descripcion){
        SBError err = new SBError(fila, columna, Constantes.ERR_LEXICO, descripcion);
        listaErrores.add(err);
    }
    
    public void nuevoErrorSintactico(int fila, int columna, String descripcion){
        SBError err = new SBError(fila, columna, Constantes.ERR_SINTACTICO, descripcion);
        listaErrores.add(err);
    }
    
    public void nuevoErrorSemantico(int fila, int columna, String descripcion){
        SBError err = new SBError(fila, columna, Constantes.ERR_SEMANTICO, descripcion);
        listaErrores.add(err);
    }
        
    public void nuevoError(String descripcion){
        SBError err = new SBError(-1, -1, Constantes.ERR_GENERAL, descripcion);
        listaErrores.add(err);
    }

    public int cuentaErrores(){
        return listaErrores.size();
    }

}