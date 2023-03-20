import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author diego leiva, pablo orellana
 *
 * Clase que modula el interprete en Lisp
 * en esta clase se realizan todas las acciones necesarias
 * para que el interprete funcionen correctamente
 */
public class Interpreter {
    private HashMap<String, String> variables = new HashMap<String, String>();
    private AritmethicOperation aritmethicOperation = new AritmethicOperation();
    private LispLogicFuction logicFuction = new LispLogicFuction();
    private ArrayList<String> instructions = new ArrayList<String>(Arrays.asList("setq", "print", "+", "-", "*", "/",
            "'", "quote", ">", "<", "equals", "=", "Atom", "List", "Cond", "defun"));
    private ArrayList<String> FunctionsNames = new ArrayList<String>();

    private Tokenizer tokenizer = new Tokenizer();
    private HashMap<String, ArrayList<String>> functions = new HashMap<String, ArrayList<String>>();
    private HashMap<String, LinkedHashMap<String, String>> parameters = new HashMap<String, LinkedHashMap<String, String>>();
    private boolean end = false;
    private int created_instructions = 1;


    /**
     * Evalua un comando y ejecuta una operacion segun la opcion dada
     * @param commands los comandos
     * @param option la opcion
     * @return el resultado de la expresion
     */
    public String operate(ArrayList<String> commands, int option){
        if (!end){
            String expresion = "";
            for (String s: commands)
                expresion += s + " ";
            if(findVariables(expresion) != null){
                expresion = findVariables(expresion);
                if (option == 1)
                    return newVariable(commands);
                else if (option == 2)
                    return aritmethicOperation.Evaluate(expresion) + "";
                else if (option == 3)
                    return quote(expresion);
                else if (option == 4)
                    return logicFuction.mayorComparar(expresion);
                else if (option == 5)
                    return logicFuction.menorComparar(expresion);
                else if (option == 6)
                    return logicFuction.equals(expresion);
                else if (option == 7)
                    return logicFuction.isAtom(expresion);
                else if (option == 8)
                    return logicFuction.isList(expresion);
                else if (option == 9)
                    return Condicionales(commands);
                else if (option == 10){
                    newFunction(convertToArrayList(expresion));
                    return "";
                }
                else{
                    if (isHere(instructions, commands.get(0)))
                        return useFunction(convertToArrayList(expresion));
                    else
                        return "ERROR: Ha ingresado una instrucción inválida";
                }
            }
            else
                return "ERROR: La variable no ha sido creada";
        }
        else{
            return "-----------";
        }
    }


    /**
     * Crea una nueva funcion a partir de un comando
     * @param command el comando de la creacion de la funcion
     */
    public void newFunction(ArrayList<String> command){
        String name = command.get(1);
        this.instructions.add(name);
        FunctionsNames.add(name);
        ArrayList<String> instrucciones = new ArrayList<String>();
        LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
        String[] parametersSplited = command.get(2).trim().split(",");
        for(String parameter: parametersSplited)
            parametersFunction.put(parameter, " ");
        this.parameters.put(name, parametersFunction);

        //Verifica si existen otras instrucciones
        for (int i = 3; i < command.size(); i++){
            String expresion = "";
            if (isHere(getInstructions(), command.get(i))){
                expresion = command.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < command.size() && flag; j++){
                    if (!isHere(getInstructions(),command.get(j))){
                        expresion += command.get(j) + "";
                        cont++;
                    }
                    else
                        flag = false;
                }
                i += cont;
            }
            instrucciones.add(expresion);
        }
        functions.put(name, instrucciones);
    }

    /**
     * Metodo que utiliza una funcion anteriormente creada
     * @param commands el comando con la funcion
     * @return el resultado
     */
    public String useFunction(ArrayList<String> commands){
        String name = commands.get(0);
        String result = "";
        ArrayList<String> newParameters = new ArrayList<String>();
        String parameters = "";
        for (int i = 1; i <commands.size(); i++){
            if (isHere(getInstructions(), commands.get(i))){
                String expression = commands.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < commands.size() && flag; j++){
                    if (!isHere(getInstructions(),commands.get(j))){
                        expression += commands.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                i += cont;
                parameters += operate(convertToArrayList(expression), tokenizer.getCommandType(convertToArrayList(expression)));
            }
            else
                parameters += commands.get(i) + " ";

            newParameters.add(parameters);
        }
        //Asignacion de parametros de la funcion
        String[] parametersSplited = parameters.split(" ");
        ArrayList<String> instructions = functions.get(name);
        LinkedHashMap<String, String> parametersFunction = this.parameters.get(name);
        String instrucciones = "";

        //Separacion de instrucciones de la funcion
        for (int i = 0; i < instructions.size(); i++){
            instrucciones += instructions.get(i).trim() + " ";
        }

        if(parametersSplited.length == parametersFunction.size()){
            int i = 0;
            for(String parameter: parametersFunction.keySet()){
                parametersFunction.put(parameter, parametersSplited[i]);
                instrucciones = instrucciones.replace(parameter, parametersFunction.get(parameter));
                i++;
            }
        }
        //Ejecucion de comandos
        ArrayList<String> evaExpression = convertToArrayList(instrucciones);
        ArrayList<String> newInstructions = new ArrayList<String>();
        for (int i = 0; i < evaExpression.size(); i++){
            String expresion = "";
            if (isHere(getInstructions(), evaExpression.get(i))){
                if (evaExpression.get(i).equals("Cond")){
                    String condition = "";
                    for (int j = i; j < evaExpression.size(); j++){
                        condition += evaExpression.get(j) + " ";
                    }
                    expresion += operate(convertToArrayList(condition), tokenizer.getCommandType(convertToArrayList(condition)));
                    i = evaExpression.size();
                    end = true;
                } else{
                    expresion = evaExpression.get(i) + " ";
                    boolean flag = true;
                    int cont = 0;
                    for (int j = i+1; j < evaExpression.size() && flag; j++){
                        if (!isHere(getInstructions(),evaExpression.get(j))){
                            expresion += evaExpression.get(j) + " ";
                            cont++;
                        }
                        else
                            flag = false;
                    }
                    i += cont;
                }
            }
            newInstructions.add(expresion);
        }
        for (String ins: newInstructions)
            result += operate(convertToArrayList(ins), tokenizer.getCommandType(convertToArrayList(ins))) + "\n";
        if (created_instructions != 1){
            result = created_instructions + "\n";
        }
        return result;
    }


    /**
     * Crea una nueva variable basada en el comando
     * @param command el comando
     * @return la nueva variable creada
     */
    public String newVariable(ArrayList<String> command){
        String name = command.get(1);
        String value = "";
        for (int i = 2; i < command.size(); i++){
            String operation = "";
            if (isHere(instructions, command.get(i))){
                for (int j = i; j < command.size(); j++){
                    operation += command.get(j) + " ";
                }
                value = operate(convertToArrayList(operation), tokenizer.getCommandType(convertToArrayList(operation)));
                i = command.size();
            }
            else
                value = command.get(i);
        }
        variables.put(name, value);
        return name +": " + value;
    }


    /**
     * Ejecucion del comando quote
     * @param command el comando
     * @return el string de la expresion quotada
     */
    public String quote(String command){
        String finalExpression ="";
        String[] expresionSplit = command.split(" ");
        int i =0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals("quote")||expresionSplit[i].equals("'")){
                i++;
                break;
            }
        }
        for(int j = i ; j<=expresionSplit.length-1; j++) {
            finalExpression += expresionSplit[j] + " ";
        }
        return finalExpression.trim();
    }


    /**
     * Verifica si existe la variable
     * @param name el nombre de la variable
     * @return la variable
     */
    private String verifyVariable(String name){
        String variable = null;
        if(variables.containsKey(name))
            variable = variables.get(name);
        return variable;
    }


    /**
     * Devuelve el listado de instrucciones
     * @return el listado de instrucciones
     */
    public ArrayList<String> getInstructions(){
        return this.instructions;
    }



    /**
     * Metodo que evalua condicionales
     * @param cond_exp la expresion
     * @return resultado del condicional
     */
    public String Condicionales(ArrayList<String> cond_exp){
        String conditional = "";
        String condition = cond_exp.get(1) + " ";
        int numeroCondiciones = 0;
        String positive = "";
        String negative = "";
        boolean positivo = false;
        boolean optimizar = false;

        for (int i = 2; i < cond_exp.size(); i++) {

            //Optimizar la condicion
            if (numeroCondiciones == 2)
                if (operate(convertToArrayList(condition), tokenizer.getCommandType(convertToArrayList(condition))).equals("verdadero"))
                    optimizar = true;
            if (!isHere(getInstructions(), cond_exp.get(i)) && numeroCondiciones != 2){
                condition += cond_exp.get(i) + " ";
                numeroCondiciones++;
            }
            else if(numeroCondiciones != 2){
                String expression = cond_exp.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < cond_exp.size() && flag; j++){
                    if (!isHere(getInstructions(),cond_exp.get(j))){
                        expression += cond_exp.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                condition += operate(convertToArrayList(expression), tokenizer.getCommandType(convertToArrayList(expression)));
                numeroCondiciones++;
                i += cont;
            }

            //Expresion positiva
            else if (numeroCondiciones == 2 && !positivo){
                if (isHere(getInstructions(),cond_exp.get(i)))
                    positive = cond_exp.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < cond_exp.size() && flag; j++){
                    if (!isHere(getInstructions(),cond_exp.get(j))){
                        positive += cond_exp.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                positivo = true;
                i += cont;
            }

            //Expresion negativa
            else if (numeroCondiciones == 2 && positivo && !optimizar){
                if (isHere(getInstructions(),cond_exp.get(i)))
                    negative = cond_exp.get(i) + " ";
                String function = "";
                for (int j = i+1; j < cond_exp.size(); j++){
                    if (isHere(instructions, cond_exp.get(j))){
                        for (int k = j; k < cond_exp.size(); k++){
                            function += cond_exp.get(k) + " ";
                        }
                        created_instructions *= Integer.parseInt(cond_exp.get(j+2));
                        negative += operate(convertToArrayList(function), tokenizer.getCommandType(convertToArrayList(function)));
                    }
                    else{
                        negative += cond_exp.get(j) + " ";
                    }
                }
                break;
            }
        }
        if (operate(convertToArrayList(condition), tokenizer.getCommandType(convertToArrayList(condition))).equals("verdadero"))
            conditional = operate(convertToArrayList(positive), tokenizer.getCommandType(convertToArrayList(positive))); //Condicion positiva
        else
            conditional = operate(convertToArrayList(negative), tokenizer.getCommandType(convertToArrayList(negative))); //Condicion negativa
        return conditional;
    }


    /**
     * verificar si es una instruccion o no
     * @param instructions las instrucciones a evaluar
     * @param default_instructions instrucciones del interprete
     * @return verdaderi si es instruccion y falso si no
     */
    private boolean isHere(ArrayList<String> instructions, String default_instructions){
        boolean flag = false;
        for (int i = 0; i < instructions.size() && flag == false; i++)
            if (default_instructions.contains(instructions.get(i)))
                flag = true;
        return flag;
    }

    /**
     * convertir un string a un arraylist
     * @param command el comando a transformar
     * @return el arrayList del string
     */
    private ArrayList<String> convertToArrayList(String command){
        String[] splitedExpression = command.split(" ");
        ArrayList<String> evaExpression = new ArrayList<String>();
        for (int j = 0; j < splitedExpression.length; j++)
            evaExpression.add(splitedExpression[j]);
        return evaExpression;
    }

    /**
     * Encontrar las variables en una command y convertirlas a su valor
     * @param command el comando con variables
     * @return el nuevo comando con valores
     */
    private String findVariables(String command){
        String newExpression = "";
        String variable = "";
        String[] parts = command.split(" ");
        for (int i = 0; i < parts.length; i++){
            //Valores de variables
            Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(parts[i]);

            if (matcher.find()){
                variable = matcher.group().trim();
                if(!variable.matches("[+-]?\\d*(\\.\\d+)?"))
                    if(verifyVariable(variable) != null)
                        parts[i] = verifyVariable(variable).toString();

            }
        }
        for (int i = 0; i < parts.length; i++)
            newExpression += parts[i] + " ";

        return newExpression;
    }
}