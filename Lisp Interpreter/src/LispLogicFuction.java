/**
 * @author diego leiva, pablo orellana
 * 
 * Clase que maneja funciones logicas contenidas en el archivo Lisp
 */
public class LispLogicFuction {

  
    /**
     * Metodo que compara si los parametros ingresados son iguales
     * @param valor Uno de los parametros a comparar
     * @param valor2 Segundo parametro que se compara
     * @return verdadero o falso dependiendo del resultado de la comparacion realizada
     */
    public String equals(String expresion){
        String valor = "";
        String valor2 = "";
        String expresionFinal ="";
        String[] expresionSplit = expresion.split(" ");
        int i =0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals("=")||expresionSplit[i].equals("equals")){
                i++;
                break;
            }
        }
        	valor = expresionSplit[i];
            valor2 = expresionSplit[i+1];
        if(valor.equals(valor2)){
            expresionFinal = "verdadero";
            return expresionFinal;
        }else{
            expresionFinal = "Falso";
            return expresionFinal;
        }
    }


    /**
     * Metodo que compara si un numero es menor que el otro
     * @param expresion expresion a evaluar
     * @return verdadero o falso dependiendo del resultado de la comparacion realizada
     */
    public String menorComparar(String expresion){
        int valor = 0;
        int valor2 = 0;
        String expresionFinal ="";
        String[] expresionSplit = expresion.split(" ");
        int i =0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals("<")){
                i++;
                break;
            }
        }
        	valor = Integer.parseInt(expresionSplit[i]);
            valor2 = Integer.parseInt(expresionSplit[i+1]);
        if(valor < valor2){
            expresionFinal = "verdadero";
            return expresionFinal;
        }else{
            expresionFinal = "Falso";
            return expresionFinal;
        }
    }


    /**
     * Metodo que compara si un numero es mayor que el otro
     * @param expresion expresion a evaluar
     * @return verdadero o falso dependiendo del resultado de la comparacion realizada
     */
    public String mayorComparar(String expresion){
        int valor = 0;
        int valor2 = 0;
        String expresionFinal ="";
        String[] expresionSplit = expresion.split(" ");
        int i = 0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals(">")){
                i++;
                break;
            }
        }
        	valor = Integer.parseInt(expresionSplit[i]);
            valor2 = Integer.parseInt(expresionSplit[i+1]);
        if(valor > valor2){
            expresionFinal = "verdadero";
            return expresionFinal;
        }else{
            expresionFinal = "Falso";
            return expresionFinal;
        }
    }


    /**
     * Metodo que verifica si la expresion es un Atom de lisp
     * @param expresion expresion a evaluar
     * @return verdadero o falso dependiendo si es o no Atom
     */
    public String isAtom(String expresion){
        String atom = "NIL";
        String[] values= expresion.split(" ");

        int i = 0;
        for(i=0; i < values.length ; i++)
        	if(values[i].contains("Atom")){
        		i++;
        		break;
        	}
        if((values.length-i)<=1) {
            atom = "T";
        }
        return atom;
    }


    /**
     * Metodo que verifica si la expresion es list 
     * @param expresion expresion a evaluar
     * @return verdadero o falso dependiendo si es o no List
     */
    public String isList(String expresion){
        String list = "NIL";
        String[] values= expresion.split(" ");

        int i = 0;
        for(i=0; i < values.length ; i++)
        	if(values[i].contains("List")){
        		i++;
        		break;
        	}
        if((values.length-i)>1) {
            list = "T";
        }
        return list;
    }

}
