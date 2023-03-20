import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String file = "C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Proyecto01-Fase-2/Lisp Interpreter/src/LispProgram.txt";
        View view = new View();
        FileScanner fileScanner = new FileScanner();
        Tokenizer tokenizer = new Tokenizer();
        Interpreter interpreter = new Interpreter();
        ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
        String variable;

        view.Welcome();
        ArrayList<String> expresions = fileScanner.Parse(file);
        for (String s: expresions)
            tokens.add(tokenizer.Tokens(s));
        for (ArrayList<String> al: tokens){
            if (al != null){
                String expresion = "";
                for (String s: al)
                    expresion += s + " ";
                view.ShowOnScreen(expresion);
                variable = interpreter.operate(al, tokenizer.getCommandType(al));
                view.ShowOnScreen(variable.toString());
                view.ShowOnScreen("\n");
            } else
                view.ShowOnScreen("ERROR: Ha agregado una cantidad incorrecta de parentesis");
        }
    }
}