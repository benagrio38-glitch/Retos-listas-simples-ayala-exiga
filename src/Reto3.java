/**
 * Lista circulares: Catálogo de libros
 * @author Jesús Manuel y Oscar Manuel
 * @date 22 octubre 2026
 */

/**
 * Reto 3 (Lista circular): Turnos de préstamo.
 * Versión A, con encapsulamiento: atributos privados y acceso por métodos
 */

/**
 * Nodo de la lista circular: un estudiante y el siguiente en el turno.
 */
class NodoCircular {
    private String estudiante;
    private NodoCircular siguiente;

    NodoCircular(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getEstudiante() { return estudiante; }

    public NodoCircular getSiguiente() { return siguiente; }

    public void setSiguiente(NodoCircular siguiente) { this.siguiente = siguiente; }
}

/**
 * Cola circular de turnos. La cola siempre apunta a la cabeza,
 * por lo que nunca hay null al final mientras la lista tenga nodos.
 */
class ListaCircular {
    private NodoCircular cabeza;
    private NodoCircular cola;

    /**
     * Agrega un estudiante al final del turno y mantiene el círculo cerrado.
     */
    public void insertar(String estudiante) {
        NodoCircular nuevo = new NodoCircular(estudiante);
        if (cabeza == null) {
            // TODO 1: con un solo nodo, 'siguiente' apunta a sí mismo
            nuevo.setSiguiente(nuevo);
            cabeza = nuevo;
            cola = nuevo;
        } else {
            // TODO 2: enlazar la cola con el nuevo
            cola.setSiguiente(nuevo);
            // TODO 3: cerrar el círculo (nuevo -> cabeza) y actualizar la cola
            nuevo.setSiguiente(cabeza);
            cola = nuevo;
        }
    }

    /**
     * Recorre exactamente una vuelta. Se usa do-while porque la lista
     * nunca llega a null: se detiene al regresar a la cabeza.
     */
    public void mostrarUnaVuelta() {
        if (cabeza == null) return;
        NodoCircular actual = cabeza;
        do {
            System.out.print(actual.getEstudiante() + " -> ");
            // TODO 4: avanzar
            actual = actual.getSiguiente();
        } while (actual != cabeza); // TODO 5: parar tras una vuelta completa
        System.out.println("(vuelve a " + cabeza.getEstudiante() + ")");
    }

    /**
     * Cuenta los nodos dando una sola vuelta, sin caer en ciclo infinito.
     */
    public int contar() {
        // TODO 6
        if (cabeza == null) return 0;
        int total = 0;
        NodoCircular actual = cabeza;
        do {
            total++;
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        return total;
    }

    /**
     * Elimina al estudiante que sigue después del indicado.
     * Cubre: lista vacía, estudiante no encontrado, un solo nodo,
     * eliminado = cabeza y eliminado = cola.
     */
    public void eliminarSiguienteDe(String estudiante) {
        // TODO 7
        if (cabeza == null) return;

        // Ubicar al estudiante dando como máximo una vuelta
        NodoCircular actual = cabeza;
        do {
            if (actual.getEstudiante().equals(estudiante)) break;
            actual = actual.getSiguiente();
        } while (actual != cabeza);

        if (!actual.getEstudiante().equals(estudiante)) {
            System.out.println("No se encontró a " + estudiante);
            return;
        }

        // Un solo nodo: su siguiente es él mismo, así que la lista queda vacía
        if (actual.getSiguiente() == actual) {
            cabeza = null;
            cola = null;
            return;
        }

        NodoCircular eliminado = actual.getSiguiente();
        actual.setSiguiente(eliminado.getSiguiente()); // saltar al eliminado

        if (eliminado == cabeza) {
            cabeza = eliminado.getSiguiente(); // la cabeza avanza
        }
        if (eliminado == cola) {
            cola = actual; // la cola retrocede al nodo previo
        }
    }
}

public class Reto3 {
    public static void main(String[] args) {
        ListaCircular turnos = new ListaCircular();

        // Datos propios 
        turnos.insertar("Oscar");
        turnos.insertar("Mariana");
        turnos.insertar("Diego");
        turnos.insertar("Paola");
        turnos.insertar("Ximena");

        System.out.println("Turnos iniciales (" + turnos.contar() + "):");
        turnos.mostrarUnaVuelta();

        System.out.println("\nEliminar al siguiente de 'Mariana' (intermedio: Diego):");
        turnos.eliminarSiguienteDe("Mariana");
        turnos.mostrarUnaVuelta();

        System.out.println("\nEliminar al siguiente de 'Paola' (la cola: Ximena):");
        turnos.eliminarSiguienteDe("Paola");
        turnos.mostrarUnaVuelta();

        System.out.println("\nEliminar al siguiente de 'Paola' (ahora es la cola; el siguiente es la cabeza: Oscar):");
        turnos.eliminarSiguienteDe("Paola");
        turnos.mostrarUnaVuelta();
        System.out.println("Total: " + turnos.contar());

        System.out.println("\nEliminar al siguiente de 'Mariana' (quedan 2; elimina a Paola):");
        turnos.eliminarSiguienteDe("Mariana");
        turnos.mostrarUnaVuelta();

        System.out.println("\nEliminar al siguiente de 'Mariana' (un solo nodo, queda vacía):");
        turnos.eliminarSiguienteDe("Mariana");
        System.out.println("Total: " + turnos.contar());

        System.out.println("\nEliminar en lista vacía:");
        turnos.eliminarSiguienteDe("Mariana");
        System.out.println("Total: " + turnos.contar());
    }
}