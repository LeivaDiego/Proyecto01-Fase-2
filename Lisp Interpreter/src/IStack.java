/**
 * Interfaz para las pila
 * @param <T> dato generico
 * @author diego leiva
 * Referencia de malonso-uvg
 */
public interface IStack<T> {
    int count();

    boolean isEmpty();

    void push(T value);

    T pull();

    T peek();
}
