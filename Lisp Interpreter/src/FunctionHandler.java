import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 * @author diego leiva, pablo orellana
 * 
 * Clase para el manejo de funciones dentro del codigo lisp
 */
public class FunctionHandler {

    private HashMap<String, String> functions = new HashMap<String, String>();
    private HashMap<String, LinkedHashMap<String, String>> parameters = new HashMap<String, LinkedHashMap<String, String>>();

/**
 * Crea una nueva funcion
 * @param name nombre de la funcion
 * @param parameters parametros de la funcion
 * @param instructions comandos que estan en la funcion
 */
    public void newFunction(String name, String parameters, String instructions){
        LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
        String[] parametersSplited = parameters.trim().split(",");
        for(String parameter: parametersSplited)
            parametersFunction.put(parameter, "");
        this.parameters.put(name, parametersFunction);
        functions.put(name, instructions);
    }


    /**
     * metodo que usa una funcion creada
     * @param name nombre de la funcion
     * @param parameters parametros de la funcion
     * @return cadena con los comandos de la funcion
     */
    public String useFunction(String name, String parameters){
        String translate = "";
        String[] parametersSplited = parameters.trim().split(",");
        if(functions.containsKey(name)){
            String instructions = functions.get(name);
            LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
            if(parametersSplited.length == parametersFunction.size()){
                int i = 0;
                for(String parameter: parametersFunction.keySet()){
                    parametersFunction.put(parameter, parametersSplited[i]);
                    instructions = instructions.replace(parameter, parametersFunction.get(parameter));
                    i++;
                }
                translate = instructions;
            }
        }
        return translate;
    } 
}
