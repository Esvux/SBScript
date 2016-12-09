package org.esvux.sbscript.interprete;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.ast.Programa;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.instrucciones.InstruccionAbstracta;
import org.esvux.sbscript.interprete.instrucciones.InstruccionDeclaracion;
import org.esvux.sbscript.parser.ParseException;
import org.esvux.sbscript.parser.ParserSBScript;

/**
 *
 * @author esvux
 */
public class Interprete {

    private final String entrada;
    private Programa programa;
    private String salida;

    public Interprete(String entrada) {
        this.entrada = entrada;
        this.salida = "";
        this.programa = new Programa();
    }
    
    public static void main(String[] args) {
        Interprete i = new Interprete("Num a, b = 8; Num c = (a * 2) ^ (b / 16);");
        i.analizar();
        i.ejecutar();
    }

    public void analizar() {
        try {
            Errores.resetInstance();
            ParserSBScript parser = new ParserSBScript(
                new java.io.StringReader(entrada)
            );
            programa = parser.PROGRAMA();
        } catch (ParseException ex) {
            Errores.getInstance().nuevoError(ex.getMessage());
        }
    }
    
    public void ejecutar() {
        InstruccionAbstracta.setContextoGlobal();
        for(Nodo declaracion : programa.getVariables()){
            InstruccionDeclaracion instr = new InstruccionDeclaracion(declaracion, false);
            instr.ejecutar(new Contexto(), Constantes.GLOBAL);
        }
        Contexto global = InstruccionAbstracta.getContextoGlobal();
        global.reporte();
        System.out.println("Contexto global creado correctamente.");
    }

    public String getSalida() {
        return this.salida;
    }

}
