import java.util.ArrayList;

/**
 * @author diego leiva, pablo orellana
 *
 * Clase encargada de evaluar los comandos obtenidos del archivo
 * y generar los tokens pertinentes para su uso
 */
public class Tokenizer {

    /**
     * Convierte el string de un comando dado, en un arreglo de caracteres
     * @param command  el comando a evaluar
     * @return el arreglo de caracteres
     */
    protected ArrayList<Character> stringToChar(String command){
        ArrayList<Character> characters = new ArrayList<Character>();
        for (int i =0 ; i < command.length(); i++){
            characters.add(command.charAt(i));
        }
        return characters;
    }

    /**
     * Crea los tokens respectivos del comando dado
     * @param command el comando a evaluar
     * @return un arreglo con los tokens del comando
     */
    public ArrayList<String> Tokens(String command){
        String temp = "";
        ArrayList<String> commands = new ArrayList<String>();
        ArrayList<Character> characters = new ArrayList<Character>();
        characters.addAll(stringToChar(command));
        if(VerifyTokens(characters)){
            for (Character current_char: characters){
                if(current_char == '(');
                else{
                    if(current_char == ')'){
                        if(temp !=""){
                            commands.add(temp);
                            temp = "";
                        }
                    }
                    else{
                        if (current_char != ' ')
                            temp += current_char+"";
                        else{
                            if(temp != ""){
                                commands.add(temp);
                                temp = "";
                            }
                        }
                    }
                }
            }
        }
        return commands;
    }

    /**
     * Verifica que la cantidad de parentesis de apertura
     * sea la misma que los de cerrado, para evaluar si el comando
     * esta completo o tiene error de sintaxis
     * @param command el comando a evaluar
     * @return verdadero si ambos valores son los mismos
     * falso si no son los mismos
     */
    private boolean VerifyTokens(ArrayList<Character> command) {
        int opener = 0;
        int closer = 0;
        for (Character character : command) {
            if (character.equals('('))
                opener++;
            else if (character.equals(')'))
                closer++;
        }
        return opener == closer;
    }

}
