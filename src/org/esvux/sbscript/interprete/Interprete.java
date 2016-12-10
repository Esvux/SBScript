package org.esvux.sbscript.interprete;

import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Metodo;
import org.esvux.sbscript.ast.Nodo;
import org.esvux.sbscript.ast.Programa;
import org.esvux.sbscript.errores.Errores;
import org.esvux.sbscript.interprete.instrucciones.InstruccionCuerpo;
import org.esvux.sbscript.interprete.instrucciones.InstruccionDeclaracion;
import org.esvux.sbscript.parser.ParseException;
import org.esvux.sbscript.parser.ParserSBScript;

/**
 *
 * @author esvux
 */
public class Interprete {
    
    public static void main(String[] args) {
        Interprete i = new Interprete(
                "Num a,b = 10;"
              + "Principal(){"
              + "   Num a = b / 3;"
              + "   Mostrar(\"b\", \"=\", b);"
              + "   Num i = 0;"
              + "   Mientras(a < b){"
              + "       Mostrar(\"Iteración:\",i);"
              + "       Mostrar(\"a =\",a);"
              + "       a = a * 1.2;"
              + "       i = i + 1;"
              + "   }"
              + "   Mostrar(\"Valor final de 'a':\",a);"
              + "}"
        );
        i.analizar();
        i.ejecutar();
    }

    private final String entrada;
    private Programa programa;

    public Interprete(String entrada) {
        this.entrada = entrada;
        this.programa = new Programa();
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
        reiniciarEjecucion();
        Contexto local = new Contexto();
        for(Nodo declaracion : programa.getVariables()) {
            InstruccionDeclaracion instr = new InstruccionDeclaracion(declaracion);
            instr.ejecutar(local, Constantes.GLOBAL);
        }
        System.out.println("Contexto global creado correctamente.");
        Metodo principal = programa.getPrincipal();
        if(principal == null) {
            //Error, no se definió ningún método principal, nada por ejecutar.
            return;
        }
        InstruccionCuerpo instr = new InstruccionCuerpo(principal.getCuerpo(), false);
        instr.ejecutar(local, Constantes.GLOBAL + 1);
        System.out.println("Ejecución finalizada.\n");
        
        System.out.println("Salida:");
        System.out.println(Interprete.salida);
    }

    private void reiniciarEjecucion(){
        Interprete.global = new Contexto();
        Interprete.salida = "";
    }
    
    private static String salida = "";
    private static Contexto global = new Contexto();
    
    public static void concatenarSalida(String cadena){
        salida += cadena;
    }
    
    public String getSalida(){
        return salida;
    }

    public static Contexto getContextoGlobal(){
        return global;
    }
    
}
