package org.esvux.sbscript.interprete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.esvux.sbscript.ast.Constantes;
import org.esvux.sbscript.ast.Metodo;
import org.esvux.sbscript.ast.Nodo;
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

    public Interprete(String entrada) {
        this.entrada = entrada;
    }
    
    public void analizar() {
        try {
            reiniciarAnalisis();
            ParserSBScript parser = new ParserSBScript(
                new java.io.StringReader(entrada)
            );
            parser.PROGRAMA();
        } catch (ParseException ex) {
            Errores.getInstance().nuevoError(ex.getMessage());
        }
    }
    
    public void ejecutar() {
        reiniciarEjecucion();
        Contexto local = new Contexto();
        for(Nodo declaracion : Interprete.variables) {
            InstruccionDeclaracion instr = new InstruccionDeclaracion(declaracion);
            instr.ejecutar(local, Constantes.GLOBAL);
        }
        System.out.println("Contexto global creado correctamente.");
        Metodo principal = Interprete.principal;
        if(principal == null) {
            Errores.getInstance().nuevoError("No se definió el método principal, nada por ejecutar.");
            return;
        }
        InstruccionCuerpo instr = new InstruccionCuerpo(principal.getCuerpo(), false);
        instr.ejecutar(local, Constantes.GLOBAL + 1);
        System.out.println("Ejecución finalizada.\n");
        System.out.println("Salida:");
        System.out.println(Interprete.salida);
    }

    private void reiniciarAnalisis(){
        Errores.resetInstance();
        Interprete.metodos = new HashMap<>();
        Interprete.variables = new ArrayList<>();
        Interprete.principal = null;
    }    
    
    private void reiniciarEjecucion(){
        Interprete.global = new Contexto();
        Interprete.salida = "";
    }
        
    private static Metodo principal = null;
    private static HashMap<String, Metodo> metodos = new HashMap<>();
    private static List<Nodo> variables;
    private static String salida = "";
    private static Contexto global = new Contexto();
    
    public static void concatenarSalida(String cadena){
        Interprete.salida += cadena;
    }
    
    public String getSalida(){
        return Interprete.salida;
    }

    public static Contexto getContextoGlobal(){
        return Interprete.global;
    }
    
    public static Metodo getMetodo(String metodo){
        return Interprete.metodos.get(metodo);
    }
    
    public static void setPrincipal(Metodo principal) {
        if (Interprete.principal == null) {
            Interprete.principal = principal;
        } else {
            Errores.getInstance().nuevoErrorSemantico(
                    principal.getFila(), principal.getColumna(),
                    "No es posible definir más de un método principal en un mismo script.");
        }
    }

    public static void addVariables(Nodo declaracion) {
        Interprete.variables.add(declaracion);
    }

    public static void addMetodo(Metodo metodo) {
        String nom = metodo.getNombre();
        if (metodo.esIncorrecto()) {
            Errores.getInstance().nuevoErrorSemantico(
                    metodo.getFila(), metodo.getColumna(),
                    "No es posible definir el metodo '"+nom+"', existen errores en sus parametros.");
            return;
        }
        if (metodos.containsKey(nom)) {
            Errores.getInstance().nuevoErrorSemantico(
                    metodo.getFila(), metodo.getColumna(),
                    "Un método con el nombre '"+nom+"' ha sido definido previamente.");
            return;
        }
        Interprete.metodos.put(nom, metodo);
    }
    
}
