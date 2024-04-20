import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Producto {
    private String nombre;
    private int cantidadInicial;
    private int cantidadVendida;

    public Producto(String nombre, int cantidadInicial) {
        this.nombre = nombre;
        this.cantidadInicial = cantidadInicial;
        this.cantidadVendida = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }
    public void vender(int cantidad) {
        if (cantidad <= cantidadInicial - cantidadVendida) {
            cantidadVendida += cantidad;
            System.out.println("Venta realizada. Stock actualizado.");
        } else {
            System.out.println("Error: No hay suficiente stock para realizar la venta.");
        }
    }

    public void duplicarInventario() {
        cantidadInicial += cantidadVendida;
        cantidadVendida = 0;
        System.out.println("Inventario duplicado. Stock actualizado.");
    }
}

class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado al inventario.");
    }

    public void realizarVenta(String nombreProducto, int cantidad) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
                producto.vender(cantidad);
                return;
            }
        }
        System.out.println("Error: Producto no encontrado.");
    }

    public void duplicarInventario(String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
                producto.duplicarInventario();
                return;
            }
        }
        System.out.println("Error: Producto no encontrado.");
    }

    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " - Stock inicial: " + producto.getCantidadInicial() +
                    ", Stock vendido: " + producto.getCantidadVendida());
        }
    }
}

public class GestiondeProductos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Realizar venta");
            System.out.println("3. Duplicar inventario");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombreProducto = scanner.nextLine();
                    System.out.print("Ingrese la cantidad inicial en inventario: ");
                    int cantidadInicial = scanner.nextInt();
                    inventario.agregarProducto(new Producto(nombreProducto, cantidadInicial));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    nombreProducto = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a vender: ");
                    int cantidadVenta = scanner.nextInt();
                    inventario.realizarVenta(nombreProducto, cantidadVenta);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del producto a duplicar: ");
                    nombreProducto = scanner.nextLine();
                    inventario.duplicarInventario(nombreProducto);
                    break;
                case 4:
                    inventario.mostrarInventario();
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}