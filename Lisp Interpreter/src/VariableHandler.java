/**
 * Clase encargada del manejo de instancia de variables dentro del programa Lisp
 * @author diego leiva, pablo orellana
  */

public class VariableHandler {
    private String var_name; // almacena el nombre de la variable
    private int var_value; // almacena el valor de la variable

    /**
     * Crea una nueva variable con los respectivos parametros
     * @param name nombre de la variable
     * @param value valor de la variable
     */
    public VariableHandler(String name, int value){
        this.var_name = name;
        this.var_value = value;
    }

    /**
     * Obtiene el nombre de una variable dada
     * @return el nombre de la variable
     */
    public String getVar_name() {
        return var_name;
    }

    /**
     * Obtiene el valor de una variable dada
     * @return el valor de la variable
     */
    public int getVar_value() {
        return var_value;
    }

    /**
     * Muestra en pantalla la instancia de la variable creada
     * @return la informacion de la variable
     */
    @Override
    public String toString(){
        return this.var_name + ": " +this.var_value;
    }

}
