import java.util.ArrayList;

/**
 * Implementacion de una pila, a partir de ArrayList para optimizacion del codigo
 * @author diego leiva, pablo orellana
 * @param <T> Dato generico
 */
public class Stack<T> implements IStack<T>{

    private ArrayList<T> arrayList;

    /**
     * Constructor del Stack
     * Instancia el ArrayList
     */
    public Stack(){
        arrayList = new ArrayList<T>();
    }

    /**
     * Agrega un elemento a la pila
     * @param value el valor a insertar
     */
    @Override
    public void push(T value) {
        arrayList.add(0,value);
    }

    /**
     * Devuelve el ultimo valor ingresado y lo elimina de la pila
     * @return el ultimo valor de la pila
     */
    @Override
    public T pull() {
        return arrayList.remove(0);
    }

    /**
     * Devuelve el ultimo valor contenido en la pila
     * @return el utlimo valor de la pila
     */
    @Override
    public T peek() {
        return arrayList.get(0);
    }


    /**
     * Devuelve la cantidad de elementos en la pila
     * @return el numero de elementos de la pila
     */
    @Override
    public int count() {
        return arrayList.size();
    }

    /**
     * Devuelve el estado de la pila dependiendo
     * si esta vacia o no
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }
}
