/**
 * Clase ArbolInventario (La Lógica)
 * ---------------------------------
 * Implementación MANUAL de un Árbol Binario de Búsqueda (ABB) construido
 * con nodos de tipo Producto.
 *
 * Regla de oro del ABB:
 * ---------------------
 * Para CUALQUIER nodo del árbol:
 *   - Todos los productos de su subárbol IZQUIERDO tienen un ID MENOR.
 *   - Todos los productos de su subárbol DERECHO tienen un ID MAYOR.
 *
 * Gracias a esta regla, para insertar o buscar no hace falta revisar todos
 * los productos: en cada nodo se compara el ID y se descarta una de las dos
 * ramas, moviéndose por el puntero "izquierdo" o "derecho".
 *
 * El puntero "raiz" es la puerta de entrada al árbol: desde él se llega
 * a cualquier otro nodo.
 */
public class ArbolInventario {

    private Producto raiz;     // Puntero al primer nodo (la cima del árbol)
    private int cantidad;      // Cantidad de productos registrados

    public ArbolInventario() {
        this.raiz = null;      // Árbol vacío: la raíz no apunta a ningún nodo
        this.cantidad = 0;
    }

    // =====================================================================
    //  INSERTAR (recursivo)
    // =====================================================================

    /**
     * Método público para registrar un producto.
     * Primero valida que el ID no exista (en un ABB las claves no se repiten)
     * y luego delega el trabajo al método recursivo.
     *
     * @return true si se insertó, false si el ID ya existía.
     */
    public boolean insertar(int id, String nombre) {
        if (buscar(id) != null) {
            return false; // ID duplicado: no se inserta
        }
        raiz = insertarRecursivo(raiz, id, nombre);
        cantidad++;
        return true;
    }

    /**
     * insertarRecursivo(): baja por el árbol hasta encontrar un puntero
     * vacío (null) en la posición correcta y ahí cuelga el nuevo nodo.
     *
     * Lógica de punteros:
     * 1. CASO BASE: si "actual" es null, encontramos el hueco donde va el
     *    producto. Se crea el nodo y se devuelve para que el nodo padre
     *    lo enganche en su puntero izquierdo o derecho.
     * 2. Si el ID es MENOR que el del nodo actual, se baja por la izquierda
     *    y el resultado se reasigna a actual.izquierdo.
     * 3. Si el ID es MAYOR, se baja por la derecha y el resultado se
     *    reasigna a actual.derecho.
     * 4. Se devuelve "actual" para que los enlaces de los niveles superiores
     *    se mantengan intactos (no se pierde ninguna rama).
     */
    private Producto insertarRecursivo(Producto actual, int id, String nombre) {
        if (actual == null) {
            return new Producto(id, nombre); // Nuevo nodo hoja
        }

        if (id < actual.id) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, id, nombre);
        } else if (id > actual.id) {
            actual.derecho = insertarRecursivo(actual.derecho, id, nombre);
        }

        return actual; // El nodo actual sigue siendo la raíz de su subárbol
    }

    // =====================================================================
    //  RECORRIDO INORDEN (Izquierda -> Raíz -> Derecha)
    // =====================================================================

    /**
     * Muestra el inventario ORDENADO de menor a mayor ID.
     */
    public void recorridoInorden() {
        if (estaVacio()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        inordenRecursivo(raiz);
    }

    /**
     * inordenRecursivo(): visita los nodos en el orden
     *   1. Todo el subárbol IZQUIERDO (IDs menores)
     *   2. El nodo ACTUAL
     *   3. Todo el subárbol DERECHO (IDs mayores)
     *
     * Como a la izquierda siempre están los menores y a la derecha los
     * mayores, este orden de visita imprime los productos ordenados por ID
     * sin necesidad de aplicar ningún algoritmo de ordenamiento.
     * El caso base es un puntero null: no hay nada que visitar.
     */
    private void inordenRecursivo(Producto actual) {
        if (actual == null) {
            return;
        }
        inordenRecursivo(actual.izquierdo);   // 1. Izquierda
        System.out.println("  " + actual);    // 2. Raíz
        inordenRecursivo(actual.derecho);     // 3. Derecha
    }

    // =====================================================================
    //  BUSCAR (por ID)
    // =====================================================================

    /**
     * Busca un producto por su ID.
     *
     * @return el Producto encontrado o null si no existe.
     */
    public Producto buscar(int id) {
        return buscarRecursivo(raiz, id);
    }

    /**
     * buscarRecursivo(): en cada nodo compara el ID buscado y decide
     * por cuál puntero seguir, descartando la otra mitad del árbol.
     *
     * 1. Si "actual" es null, se llegó al final de una rama: NO existe.
     * 2. Si el ID coincide, se encontró el producto.
     * 3. Si es menor, se sigue por el puntero izquierdo.
     * 4. Si es mayor, se sigue por el puntero derecho.
     */
    private Producto buscarRecursivo(Producto actual, int id) {
        if (actual == null) {
            return null;
        }
        if (id == actual.id) {
            return actual;
        }
        if (id < actual.id) {
            return buscarRecursivo(actual.izquierdo, id);
        }
        return buscarRecursivo(actual.derecho, id);
    }

    // =====================================================================
    //  Métodos de apoyo
    // =====================================================================

    /** El árbol está vacío cuando la raíz no apunta a ningún nodo. */
    public boolean estaVacio() {
        return raiz == null;
    }

    /** Devuelve la cantidad de productos registrados. */
    public int getCantidad() {
        return cantidad;
    }
}
