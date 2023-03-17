import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileScannerTest {


    @Test
    public void testLispParser() {
        String filename = "C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Proyecto01-Fase-2/Lisp Interpreter/src/test.txt";
        ArrayList<String> expectedCommands = new ArrayList<>();
        expectedCommands.add("(define(square x) (* x x))");
        expectedCommands.add("(define(sum x y) (+ x y))");
        expectedCommands.add("(square 5)");
        expectedCommands.add("(sum 3 4)");
        ArrayList<String> actualCommands = FileScanner.Parse(filename);
        assertEquals(expectedCommands, actualCommands);
    }

}