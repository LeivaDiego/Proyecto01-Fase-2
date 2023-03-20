import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de analizar archivos con codigo Lisp y extraer los comandos que contienen
 * @author diego leiva, pablo orellana
 */
public class FileScanner {

    /**
     * Lee un archivo de Lisp y devuelve una lista de comandos sin espacios.
     *
     * @param filepath El nombre del archivo de Lisp a analizar.
     * @return Una lista de comandos sin espacios.
     * @throws RuntimeException Si hay un error al leer el archivo o si falta algun parentesis en los comandos.
     */
    public ArrayList<String> Parse(String filepath) {
        ArrayList<String> commands = new ArrayList<>(); //

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            String command = "";

            // Lee cada linea del archivo y construye los comandos completos y comienza uno nuevo
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Ignora lineas vacias y comentarios
                if (line.startsWith(";") || line.isEmpty()) {
                    continue;
                }

                // Concatena la linea actual al comando en construccion
                command += line;

                // Si el comando esta completo, es agregado a la lista de comandos
                if (countParentheses(command) == 0) {
                    commands.add(command.replaceAll("\\s+", " "));
                    command = "";
                }
            }

            // Si hay un comando en construccion al final del archivo significa que hace falta un parentesis
            if (!command.isEmpty()) {
                throw new RuntimeException("ERROR: Falta un parentesis de cierre");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + filepath);
        }

        return commands;
    }

    /**
     * Cuenta el numero de parentesis en un comando dado
     * @param command el comando a evaluar
     * @return la cantidad de parentesis en el comando
     */
    private int countParentheses(String command) {
        int count = 0;

        for (int i = 0; i < command.length(); i++) {

            // si hay un parentesis de apertura suma 1 al contador
            if (command.charAt(i) == '(') {
                count++;

                // si hay un parentesis de cierre le resta 1 al contador
            } else if (command.charAt(i) == ')') {
                count--;
            }
        }

        return count;
    }

}
