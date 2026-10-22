/**
 * Lista simples: Catálogo de libros
 * @author Jesús Manuel y Oscar Manuel
 * @date 22 octubre 2026
 */
/**
 * Reto 1 (Lista simple): Catálogo de libros.
 * Versión A. Cada clase protege su estado interno con atributos privados
 * y expone solo los métodos necesarios (encapsulamiento).
 */

/**
 * Unidad básica de la lista: guarda un libro y la referencia al nodo siguiente.
 * Los atributos son privados; el acceso se hace con getters y setters.
 */
class Nodo {
    private String libro;
    private Nodo siguiente;

    Nodo(String libro) {
        this.libro = libro;
    }

    public String getLibro() { return libro; }

    public Nodo getSiguiente() { return siguiente; }

    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}

/**
 * Lista enlazada simple. La cabeza es privada: ningún código externo
 * puede alterar los enlaces sin pasar por los métodos de la lista.
 */
class ListaSimple {
    private Nodo cabeza;

    /**
     * Inserta un libro al final de la lista.
     * Si está vacía, el nuevo nodo se vuelve la cabeza; si no, se recorre
     * hasta el último nodo (el que tiene siguiente == null) y se enlaza ahí.
     */
    public void insertarFinal(String libro) {
        Nodo nuevo = new Nodo(libro);
        if (cabeza == null) {
            // TODO 1: lista vacía, el nuevo nodo es la cabeza
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            // TODO 2: detenerse en el ÚLTIMO nodo, no cuando actual sea null
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            // TODO 3: enlazar el nuevo nodo al final
            actual.setSiguiente(nuevo);
        }
    }

    /**
     * Devuelve true si el libro existe en la lista.
     */
    public boolean buscar(String libro) {
        Nodo actual = cabeza;
        while (actual != null) {
            // TODO 4: comparar con equals (no con ==, que compara referencias)
            if (actual.getLibro().equals(libro)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Elimina la primera aparición del libro.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(String libro) {
        if (cabeza == null) return false;

        if (cabeza.getLibro().equals(libro)) {
            // TODO 5: eliminar la cabeza = mover la cabeza al siguiente nodo
            cabeza = cabeza.getSiguiente();
            return true;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null) {
            // TODO 6: ¿el siguiente nodo es el que busco?
            if (actual.getSiguiente().getLibro().equals(libro)) {
                // TODO 7: saltar el nodo a eliminar (también cubre eliminar el último)
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /** Imprime la lista de cabeza a null. */
    public void mostrar() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getLibro() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }
}

public class Reto1 {
    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();

        // Datos propios
        lista.insertarFinal("Cien años de soledad");
        lista.insertarFinal("Pedro Páramo");
        lista.insertarFinal("El laberinto de la soledad");
        lista.insertarFinal("Rayuela");
        lista.insertarFinal("Aura");
        System.out.println("Lista inicial:");
        lista.mostrar();

        System.out.println("\nBuscar 'Rayuela' (existe): " + lista.buscar("Rayuela"));
        System.out.println("Buscar 'Ficciones' (no existe): " + lista.buscar("Ficciones"));

        System.out.println("\nEliminar la cabeza ('Cien años de soledad'): "
                + lista.eliminar("Cien años de soledad"));
        lista.mostrar();

        System.out.println("\nEliminar un nodo intermedio ('El laberinto de la soledad'): "
                + lista.eliminar("El laberinto de la soledad"));
        lista.mostrar();

        System.out.println("\nEliminar el último ('Aura'): " + lista.eliminar("Aura"));
        lista.mostrar();

        System.out.println("\nEliminar uno inexistente ('Ficciones'): " + lista.eliminar("Ficciones"));
        lista.mostrar();
    }
}