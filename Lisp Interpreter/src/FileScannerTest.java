import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileScannerTest {


    @Test
    public void testLispParser() {
        String filename = "C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Proyecto01-Fase-2/Lisp Interpreter/src/test.txt";
        ArrayList<String> expectedCommands = new ArrayList<>();
        expectedCommands.add("(define(squarex)(*xx))");
        expectedCommands.add("(define(sumxy)(+xy))");
        expectedCommands.add("(square5)");
        expectedCommands.add("(sum34)");
        ArrayList<String> actualCommands = FileScanner.Parse(filename);
        assertEquals(expectedCommands, actualCommands);
    }

}