/**
 * Lista Dobles: Catálogo de libros
 * @author Jesús Manuel y Oscar Manuel
 * @date 22 octubre 2026
 */

/**
 * Reto 2 (Lista doble): Historial de lectura.
 * Versión A, con encapsulamiento: atributos privados y acceso por métodos.
 */

/**
 * Nodo con dos enlaces: siguiente y anterior.
 */
class NodoDoble {
    private String libro;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    NodoDoble(String libro) {
        this.libro = libro;
    }

    public String getLibro() { return libro; }

    public NodoDoble getSiguiente() { return siguiente; }

    public void setSiguiente(NodoDoble siguiente) { this.siguiente = siguiente; }

    public NodoDoble getAnterior() { return anterior; }

    public void setAnterior(NodoDoble anterior) { this.anterior = anterior; }
}

/**
 * Lista doblemente enlazada con referencias a cabeza y cola, ambas privadas.
 */
class ListaDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;

    /**
     * Inserta un libro al inicio. El libro leído más recientemente queda primero.
     */
    public void insertarInicio(String libro) {
        NodoDoble nuevo = new NodoDoble(libro);
        if (cabeza == null) {
            // TODO 1: con un solo nodo, cabeza y cola son el mismo
            cabeza = nuevo;
            cola = nuevo;
        } else {
            // TODO 2: el nuevo apunta hacia adelante a la cabeza actual
            nuevo.setSiguiente(cabeza);
            // TODO 3: la cabeza actual apunta hacia atrás al nuevo
            cabeza.setAnterior(nuevo);
            // TODO 4: el nuevo pasa a ser la cabeza
            cabeza = nuevo;
        }
    }

    /**
     * Elimina la primera aparición del libro, reconectando los vecinos.
     * @return true si se eliminó, false si no existe.
     */
    public boolean eliminar(String libro) {
        NodoDoble actual = cabeza;
        while (actual != null && !actual.getLibro().equals(libro)) {
            actual = actual.getSiguiente();
        }
        if (actual == null) return false;

        // TODO 5: si tiene anterior, anterior.siguiente salta a actual.siguiente;
        //         si no, actual era la cabeza y la cabeza pasa al siguiente.
        if (actual.getAnterior() != null) {
            actual.getAnterior().setSiguiente(actual.getSiguiente());
        } else {
            cabeza = actual.getSiguiente();
        }

        // TODO 6: si tiene siguiente, siguiente.anterior salta a actual.anterior;
        //         si no, actual era la cola y la cola pasa al anterior.
        if (actual.getSiguiente() != null) {
            actual.getSiguiente().setAnterior(actual.getAnterior());
        } else {
            cola = actual.getAnterior();
        }
        return true;
    }

    /** Recorre de cabeza a cola. */
    public void mostrarAdelante() {
        NodoDoble actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getLibro() + " <-> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }

    /** Recorre de cola a cabeza usando los enlaces anteriores. */
    public void mostrarInverso() {
        NodoDoble actual = cola;
        while (actual != null) {
            // TODO 7: imprimir y retroceder
            System.out.print(actual.getLibro() + " <-> ");
            actual = actual.getAnterior();
        }
        System.out.println("null");
    }
}

public class Reto2 {
    public static void main(String[] args) {
        ListaDoble historial = new ListaDoble();

        // Datos propios (cámbielos por los de su bina)
        historial.insertarInicio("Aura");
        historial.insertarInicio("Rayuela");
        historial.insertarInicio("Pedro Páramo");
        historial.insertarInicio("Cien años de soledad");
        historial.insertarInicio("El laberinto de la soledad");

        System.out.println("Hacia adelante:");
        historial.mostrarAdelante();
        System.out.println("Hacia atrás:");
        historial.mostrarInverso();

        System.out.println("\nEliminar la cabeza ('El laberinto de la soledad'): "
                + historial.eliminar("El laberinto de la soledad"));
        historial.mostrarAdelante();

        System.out.println("\nEliminar un intermedio ('Pedro Páramo'): "
                + historial.eliminar("Pedro Páramo"));
        historial.mostrarAdelante();

        System.out.println("\nEliminar la cola ('Aura'): " + historial.eliminar("Aura"));
        historial.mostrarAdelante();
        historial.mostrarInverso();

        System.out.println("\nEliminar uno inexistente ('Ficciones'): " + historial.eliminar("Ficciones"));
    }
}