import java.util.Scanner;

/**
 * Clase Main (La Interfaz)
 * ------------------------
 * Punto de entrada del sistema de inventario Tree-Stock.
 * Presenta el menú interactivo en consola (switch-case) y delega toda la
 * lógica del árbol a la clase ArbolInventario.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolInventario inventario = new ArbolInventario();

        int opcion = -1;

        System.out.println("=============================================");
        System.out.println("   TREE-STOCK - Sistema de Inventario (ABB)");
        System.out.println("=============================================");

        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    registrarProducto(scanner, inventario);
                    break;
                case 2:
                    mostrarInventario(inventario);
                    break;
                case 3:
                    buscarProducto(scanner, inventario);
                    break;
                case 0:
                    System.out.println("\nGracias por usar Tree-Stock. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("\nOpción inválida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n---------------------------------------------");
        System.out.println("1. Registrar Producto");
        System.out.println("2. Mostrar Inventario");
        System.out.println("3. Buscar Producto");
        System.out.println("0. Salir");
        System.out.println("---------------------------------------------");
        System.out.print("Selecciona una opción: ");
    }

    /**
     * Lee un número entero desde consola. Si el usuario escribe algo que
     * no es un número, devuelve -1 para evitar que el programa se detenga.
     */
    private static int leerEntero(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Opción 1: solicita ID y nombre, e inserta el producto en el árbol.
     * Si el ID ya existe, se informa y no se registra.
     */
    private static void registrarProducto(Scanner scanner, ArbolInventario inventario) {
        System.out.println("\n--- Registrar producto ---");
        System.out.print("ID del producto (número entero positivo): ");
        int id = leerEntero(scanner);
        if (id <= 0) {
            System.out.println("ID inválido. Debe ser un número entero positivo.");
            return;
        }

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        if (inventario.insertar(id, nombre)) {
            System.out.println("Producto registrado correctamente: [ID: " + id + "] " + nombre);
            System.out.println("Total de productos en inventario: " + inventario.getCantidad());
        } else {
            System.out.println("Ya existe un producto con el ID " + id + ". No se registró.");
        }
    }

    /**
     * Opción 2: ejecuta el recorrido inorden para listar el inventario
     * ordenado por ID de menor a mayor.
     */
    private static void mostrarInventario(ArbolInventario inventario) {
        System.out.println("\n--- Inventario ordenado por ID (recorrido inorden) ---");
        inventario.recorridoInorden();
        if (!inventario.estaVacio()) {
            System.out.println("Total de productos: " + inventario.getCantidad());
        }
    }

    /**
     * Opción 3: solicita un ID y dice si el producto existe o no.
     */
    private static void buscarProducto(Scanner scanner, ArbolInventario inventario) {
        System.out.println("\n--- Buscar producto ---");
        System.out.print("ID a buscar: ");
        int id = leerEntero(scanner);
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        Producto encontrado = inventario.buscar(id);
        if (encontrado != null) {
            System.out.println("El producto SÍ existe: " + encontrado);
        } else {
            System.out.println("El producto con ID " + id + " NO existe en el inventario.");
        }
    }
}
