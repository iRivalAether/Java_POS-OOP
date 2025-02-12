package Proyecto_Integrador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main_Integrador {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Producto> productos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        cargarProductos();
        menuPrincipal();
    }

    public static void menuPrincipal() throws IOException { 
    	// Este es el menu principal, donde se llevara a cabo cada accion
        int opcion;
        do {
            System.out.println("\n--- BIENVENIDO A CAFETERÍA EL 505 ---");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Punto de Venta");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(bf.readLine());
            switch (opcion) {
                case 1:
                    gestionProductos();
                    break;
                case 2:
                    puntoDeVenta();
                    break;
                case 3:
                    System.out.println("¡Gracias por utilizar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 3);
    }

    public static void gestionProductos() throws IOException {
        int opcion;
        do {
        	// Otro submenu donde aqui se gestiona los productos de la tiendoca
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Modificar Producto");
            System.out.println("2. Mostrar Lista de Productos");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(bf.readLine());
            switch (opcion) {
                case 1:
                    modificarProducto();
                    break;
                case 2:
                    mostrarProductos();
                    break;
                case 3:
                    menuPrincipal();
                    break;
                default:
                	//aqui por si ponen una opcion no valida
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    
            }
        } while (opcion != 3);// este es para que de ahuevo haga la primera vuelta del ciclo
    }

    public static void cargarProductos() {
    	//aqui como dice el metodo, cargamos los productos ya establecidos desde un principio
        productos.add(new Producto("001", "Café latte grande    ", 70));
        productos.add(new Producto("002", "Café latte chico     ", 50));
        productos.add(new Producto("003", "Café capuchino grande", 70));
        productos.add(new Producto("004", "Café capuchino chico ", 50));
        productos.add(new Producto("005", "Baguette Clásico     ", 90));
        productos.add(new Producto("006", "Baguette con Avellana", 95));
        productos.add(new Producto("007", "Café moka frapuchino ", 56));
        productos.add(new Producto("008", "Café expreso         ", 40));
        productos.add(new Producto("009", "Baguette Italiano    ", 110));
        productos.add(new Producto("010", "Arranciata Natural   ", 45));
    }

    public static void mostrarProductos() {
    	//mostramos los productos de la clase Producto
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        System.out.println("| Código |      Nombre     \t |  Precio  |");
        System.out.println("|--------|-----------------------|----------|");
        for (Producto producto : productos) {
            System.out.printf("| %-6s | %-16s | $%7.2f |\n",
         //Los numeros bien extraños de arribas son para darle formato a la cadena de caracteres
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio());
        }
        System.out.println("|--------|-----------------------|----------|");
    }
    public static void modificarProducto() throws IOException {
    	//Aqui modificamos el producto de que si marca que es diferente de vacio,
    	//buscara el producto y te dejara modificar el nombre y precio
        System.out.print("Ingrese el código del producto que desea modificar: ");
        String codigo = bf.readLine();
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto.getNombre());
            System.out.print("Ingrese el nuevo nombre: ");
            String nombre = bf.readLine();
            System.out.print("Ingrese el nuevo precio: ");
            double precio = Double.parseDouble(bf.readLine());
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            System.out.println("Producto modificado exitosamente:");
            System.out.println(producto.getNombre());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static Producto buscarProducto(String codigo) {
    	//Aqui no hay nada que explicar profa XD
        for (Producto producto : productos) {
            if (producto.getId().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public static void puntoDeVenta() throws IOException {
    	//alcc tampoco aqui no hay nada que explicar solo es el menu del punto de venta
    	
        System.out.println("\n--- PUNTO DE VENTA ---");
        Carrito carrito = new Carrito();
        boolean salir = false;
        do {
            System.out.println("\n--- OPCIONES ---");
            System.out.println("1. Agregar producto al carrito");
            System.out.println("2. Eliminar producto del carrito");
            System.out.println("3. Ver carrito");
            System.out.println("4. Realizar compra");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(bf.readLine());
            switch (opcion) {
                case 1:
                    agregarAlCarrito(carrito);
                    break;
                case 2:
                    eliminarDelCarrito(carrito);
                    break;
                case 3:
                    verCarrito(carrito);
                    break;
                case 4:
                    realizarCompra(carrito);
                    salir = true;
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (!salir);
    }

    public static void agregarAlCarrito(Carrito carrito) throws IOException {
    	//Aqui agregamos cosas al carrito, mediante la clase ItemCarrito
    	//Si marca que no esta vacio ya hace todo el proceso
        System.out.print("Ingrese el código del producto que desea agregar: ");
        String codigo = bf.readLine();
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            System.out.print("Ingrese la cantidad: ");
            int cantidad = Integer.parseInt(bf.readLine());
            ItemCarrito ic = new ItemCarrito(producto,cantidad);
            carrito.agregarProducto(ic);
            System.out.println("Producto agregado al carrito.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void eliminarDelCarrito(Carrito carrito) throws IOException {
    	//aqui elimanamos del carritos los productos
    	//aqui se empieza desde el 0 pq es un arreglo we
    	// por ejemplo: pediste dos articulos
    	//se contaria como 0 y 1
        // y los numeros
        if (carrito.getProductos().isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        System.out.print("Ingrese el número del producto que desea eliminar: ");
        int indice = Integer.parseInt(bf.readLine());
        if (indice >= 0 && indice < carrito.getProductos().size()) {
            carrito.eliminarProducto(indice);
            System.out.println("Producto eliminado del carrito.");
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    public static void verCarrito(Carrito carrito) {
    	//aqui no hay que explicar nada XD
        System.out.println("\n--- CARRITO ---");
        carrito.mostrarCarrito();
    }

    public static void realizarCompra(Carrito carrito) {
    	//Aqui creamos el archivos del ticket para guardar la compra
        File file = new File("ticket_compra.txt");
        try (FileWriter archivo = new FileWriter(file, true)) {
            archivo.write("=======================================\n");
            archivo.write("|         CAFETERIA EL 505          |\n");
            archivo.write("|           Ticket de Compra          |\n");
            archivo.write("=======================================\n");
            archivo.write("| Código |     Producto     | Precio  |\n");
            archivo.write("|=====================================|\n");

            for (ItemCarrito item : carrito.getProductos()) {
                archivo.write(String.format("| %6s | %-16s | $%6.2f |\n",
                        item.getId(),
                        item.getNombre(),
                        item.getPrecio()));
            }
            archivo.write("|-------------------------------------|\n");
            archivo.write(String.format("| TOTAL                        $%6.2f |\n", carrito.calcularTotal()));
            archivo.write("=======================================\n");
            archivo.write("| ¡Gracias por su compra!             |\n");
            archivo.write("=======================================\n");
            archivo.write("Ay don ramon,Ya se porque le dicen el burro\n");
            System.out.println("Recibo generado correctamente en el archivo 'ticket_compra.txt'");
        } catch (IOException e) {
            System.out.println("Error al generar el recibo: " + e.getMessage());
        }
    }
}