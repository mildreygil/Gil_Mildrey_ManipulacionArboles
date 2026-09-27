/**
 * Clase Producto (El Nodo)
 * ------------------------
 * Representa cada NODO del árbol binario de búsqueda (ABB) del inventario.
 *
 * Cada nodo guarda:
 *   - id: identificador único del producto. Es la CLAVE que decide en qué
 *     lugar del árbol se ubica el producto.
 *   - nombre: descripción del producto.
 *   - izquierdo: PUNTERO (referencia) al subárbol con IDs MENORES.
 *   - derecho:   PUNTERO (referencia) al subárbol con IDs MAYORES.
 *
 * Cuando un puntero vale null significa que "no hay nada" en ese lado,
 * es decir, que ahí todavía se puede colgar un nuevo producto.
 */
public class Producto {

    int id;
    String nombre;
    Producto izquierdo; // Puntero al hijo izquierdo (IDs menores)
    Producto derecho;   // Puntero al hijo derecho (IDs mayores)

    public Producto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.izquierdo = null; // Al crearse, el nodo es una "hoja": no tiene hijos
        this.derecho = null;
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + nombre;
    }
}
