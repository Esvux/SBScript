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
                "Num a,b = 10;\n"
              + "Principal(){\n"
              + "Mostrar(\"El resultado de sumar 5 + 18 es:\", Sumar(5, 18));\n"
              + "Mostrar(\"El factorial de 5 es:\", Factorial(5));\n"
              + "}\n"
              + "\n"
              + "Num Sumar(Num a, Num b){\n"
              + "   Retorno a + b;\n"
              + "}\n"
              + "\n"
              + "Num Factorial(Num a){\n"
              + "   Si(a <= 0){\n"
              + "       Retorno 1;\n"
              + "   }Sino{\n"
              + "       Retorno Factorial(a - 1) * a;\n"
              + "   }\n"
              + "}\n"
              + "\n"
              + "Void Pruebas(){\n"
              + "   Num a = b / 3;\n"
              + "   Mostrar(\"b\", \"=\", b);\n"
              + "   Num i = 0;\n"
              + "   Mientras(a < b){\n"
              + "       Mostrar(\"Iteración:\",i);\n"
              + "       Mostrar(\"a =\",a);\n"
              + "       a = a * 1.2;\n"
              + "       i = i + 1;\n"
              + "   }\n"
              + "   i=9;\n"
              + "   Selecciona(i)\n"
              + "       9:{ Mostrar(\"nueve...\"); }\n"
              + "       10:{ Mostrar(\"diez...\"); Detener; }\n"
              + "       11:{ Mostrar(\"once...\"); Detener; }\n"
              + "       12:{ Mostrar(\"doce...\"); }\n"
              + "   \n"
              + "   Para(Num j = 20; j > 13; --){\n"
              + "       Si(j%2==0){\n"
              + "           Continuar;\n"
              + "       }\n"
              + "       Mostrar(\"j vale\", j);\n"
              + "   }\n"
              + "   Mostrar(\"Valor final de 'a':\",a);\n"
              + "}\n"
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
        Metodo main = Interprete.principal;
        if(main == null) {
            Errores.getInstance().nuevoError("No se definió el método principal, nada por ejecutar.");
            return;
        }
        InstruccionCuerpo instr = new InstruccionCuerpo(main.getCuerpo(), false);
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
