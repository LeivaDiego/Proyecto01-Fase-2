 import java.util.Arrays;

/**
 * @author diego leiva, pablo orellana
 * 
 * Clase que realiza operaciones aritmeticas contenidas en el archivo Lisp
 */
 public class AritmethicOperation {
 
     private Stack<Integer> stack;
     private String[] operators = {"+","-","*","/"};
 
 
     /**
      * Evalua la expresion aritmetica y opera
      * @param expresion expresion aritmetica a operar 
      * @return Resultado de la operacion
      */
     public Integer Evaluate(String expresion){
         stack = new Stack<Integer>();
         boolean flag = true;
         int num1, num2, result = 0;
         String[] values = expresion.split(" "); //Separar los valores en la expresion
         
         for(int i = values.length-1; i >= 0; i--){ //Recorrer la expresion 
             if(Arrays.asList(operators).contains(values[i])){ //Si es Operador
                 if (stack.count() >= 2){ //Si hay dos o mas en la pila es porque se pueden hacer operaciones
 
                     //Sacar los dos ultimos numeros
                     num1 = stack.pull();
                     num2 = stack.pull();
                     switch(values[i]){
                         case "+": //Suma
                             stack.push(num1 + num2);
                         break;
 
                         case "-": //Resta
                             stack.push(num2 - num1);
                         break;
 
                         case "*": //Multiplicacion
                             stack.push(num1 * num2);
                         break;
 
                         case "/": //Division
                             if (num1 != 0) //Si el  numero es ≠ 0 se puede hacer la division 
                                 stack.push(num2 / num1);
                             else{ //Division indefinida
                                 System.out.println("Error: Division entre cero");
                                 return null;
                             }
                         break;
 
                     }
                 }
                 else{ //Faltan numeros
                     System.out.println("Error: Le faltaron operandos");
                     flag = false;
                 }
             }
             else{ //No ingreso datos
                 stack.push(Integer.parseInt(values[i]));
             }
         }
         if (stack.count() == 1 && flag) //Si se tiene un elemento en pila
             result = stack.pull();
         else if (!flag) //Si se tinen mas elementos es porque faltaron operadores
             return null;
         else {
             System.out.println("Error: Le faltaron operadores");
             flag = false;
         }
         if (flag) //Si la operacion es valida
             return result;
         else //Existe algun error 
             return null;
     }
 }
