import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileScanner {

    public static ArrayList<String> parse(String filepath) {
        ArrayList<String> commands = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            String command = "";

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith(";") || line.isEmpty()) {
                    continue;
                }

                command += line;

                if (countParentheses(command) == 0) {
                    commands.add(command.replaceAll("\\s", ""));
                    command = "";
                }
            }

            if (!command.isEmpty()) {
                throw new RuntimeException("ERROR: Falta un parentesis de cierre");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + filepath);
        }

        return commands;
    }

    private static int countParentheses(String command) {
        int count = 0;

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == '(') {
                count++;
            } else if (command.charAt(i) == ')') {
                count--;
            }
        }

        return count;
    }

}
