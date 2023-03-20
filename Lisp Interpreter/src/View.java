/**
 * @author diego leiva, pablo orellana
 *
 * Clase encargada de mostrar mensajes en pantalla
 */
public class View {

    /**
     * Muestra el mensaje de bienvenida en pantalla
     */
    public void Welcome(){
        System.out.println("Bienvendio al Interprete de Lisp\n");
    }

    /**
     * Muestra un mensaje en pantalla
     * @param str el mensaje a mostrar
     */
    public void ShowOnScreen(String str){
        System.out.println(str);
    }


}
