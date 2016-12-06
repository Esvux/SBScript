package org.esvux.sbscript.interprete;

import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.parser.ParseException;
import org.esvux.sbscript.parser.ParserSBScript;

/**
 *
 * @author esvux
 */
public class Interprete {

    private final String entrada;
    private String salida;

    public Interprete(String entrada) {
        this.entrada = entrada;
        this.salida = "";
    }

    public void analizar() {
        try {
            Errores.resetInstance();
            ParserSBScript parser = new ParserSBScript(
                new java.io.StringReader(entrada)
            );
            parser.PROGRAMA();
        } catch (ParseException ex) {
            Errores.getInstance().nuevoError(ex.getMessage());
        }
    }

    public String getSalida() {
        return this.salida;
    }

}
