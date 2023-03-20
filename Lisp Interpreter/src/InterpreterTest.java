import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterpreterTest {
    private LispLogicFuction logic = new LispLogicFuction();
    private Interpreter interpreter = new Interpreter();
    private ArrayList<String> expressions = new ArrayList<String>();
    private ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
    private Tokenizer tk = new Tokenizer();
    private String variable;
    private FunctionHandler fun = new FunctionHandler();

    @Test
    void Test_Quote() {
        assertEquals("5", interpreter.quote("' 5"));
    }

    @Test
    void Test_Set() {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("setq");
        arr.add("var");
        arr.add("10");
        assertEquals("var: 10", interpreter.newVariable(arr));
    }

    @Test
    void Test_Atom() {
        assertEquals("T", logic.isAtom("Atom X"));
    }

    @Test
    void Test_List() {
        assertEquals("T", logic.isList("List (1 2 3 4 5 6 7 8 9)"));
    }

    @Test
    void Test_Equal() {
        assertEquals("verdadero", logic.equals("equals 10 10"));
    }


    @Test
    void Test_Greater_with_vars() {
        expressions.add("(setq var 15)");
        expressions.add("(> var 12)");
        for (String s: expressions)
            tokens.add(tk.Tokens(s));
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = interpreter.operate(al, tk.getCommandType(al));
        }
        assertEquals("verdadero", variable.toString());
    }


    @Test
    void Test_Less() {
        assertEquals("Falso", logic.menorComparar("< 10 5"));
    }
}