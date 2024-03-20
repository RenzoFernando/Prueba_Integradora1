package co.icesi.edu.ui;

import co.icesi.edu.model.Controlador;
import java.util.Scanner;

//----------------------------------------------------------------//

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Controlador controlador = new Controlador();

    //----------------------------------------------------------------//

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("Bienvenido a Tienda Libre");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
        scanner.close();
    }

    //----------------------------------------------------------------//

    private static void iniciarSesion() {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();

        // Verificar si las credenciales son válidas
        boolean sesionIniciada = controlador.iniciarSesion(nombreUsuario, contraseña);
        if (sesionIniciada) {
            System.out.println("¡Inicio de sesión exitoso!");
            if (!controlador.esAdministrador(nombreUsuario)) {
                menuUsuario();
            } else {
                menuAdministrador();
            }
        } else {
            System.out.println("Inicio de sesión fallido. Verifique sus credenciales.");
        }
    }

    //----------------------------------------------------------------//


    private static void registrarUsuario() {
        System.out.println("Registro de nuevo usuario:");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese sus nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese sus apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese su fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Ingrese su dirección de residencia: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese su ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Ingrese su número de teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
        boolean esAdministrador = false;
        int opcion = 0;
        do {
            System.out.print("¿Es administrador de la tienda? (1: Sí / 2: No): ");
            opcion = scanner.nextInt();
            if (opcion == 1) {
                esAdministrador = true;
            } else if (opcion == 2) {
                esAdministrador = false;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione 1 o 2.");
            }
            scanner.nextLine();
        } while (opcion != 1 && opcion != 2);

        // Llamar al método del controlador para registrar el usuario
        boolean registroExitoso = controlador.registrarUsuario(nombreUsuario, contrasena, nombres, apellidos,
                fechaNacimiento, direccion, ciudad, telefono, correo, esAdministrador);

        // Mostrar mensaje de registro exitoso o fallido
        if (registroExitoso) {
            System.out.println("¡Registro exitoso!");
        } else {
            System.out.println("Error al registrar el usuario. Nombre de usuario ya existente.");
        }
    }

    //----------------------------------------------------------------//

    private static void menuUsuario() {
        System.out.println("Bienvenido a Tienda Libre");
        System.out.println("1. Ver catálogo de productos");
        System.out.println("2. Buscar producto");
        System.out.println("3. Ver carrito de compras"); //print
        System.out.println("4. Realizar pedido");
        System.out.println("5. Ver historial de pedidos"); //json que guarde y filtre por nombre?
        System.out.println("6. Configuración de cuenta");  //ñao
        System.out.println("7. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                // Implementar método para ver catálogo de productos
                break;
            case 2:
                // Implementar método para buscar producto
                break;
            case 3:
                // Implementar método para ver carrito de compras
                break;
            case 4:
                // Implementar método para realizar pedido
                break;
            case 5:
                // Implementar método para ver historial de pedidos
                break;
            case 6:
                // Implementar método para configuración de cuenta
                break;
            case 7:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    }

    //----------------------------------------------------------------//

    private static void menuAdministrador() {
        System.out.println("Bienvenido a Tienda Libre (Modo Administrador)");
        System.out.println("1. Ver catálogo de productos");
        System.out.println("2. Buscar producto");
        System.out.println("3. Agregar producto al catálogo"); //YAP
        System.out.println("4. Eliminar producto del catálogo"); //NO REQUERIMIENTO
        System.out.println("5. Ver lista de usuarios");
        System.out.println("6. Ver historial de pedidos"); //si archovo json
        System.out.println("7. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                // Implementar método para ver catálogo de productos
                break;
            case 2:
                // Implementar método para buscar producto
                break;
            case 3:
                agregarProductoCatalogo();
                menuAdministrador();
                break;
            case 4:
                // Implementar método para eliminar producto del catálogo
                break;
            case 5:
                // Implementar método para ver lista de usuarios
                break;
            case 6:
                // Implementar método para ver historial de pedidos
                break;
            case 7:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    }

    //----------------------------------------------------------------//
    //AGREGAR PRODUCTO AL CATALOGO
    private static void agregarProductoCatalogo() {
        System.out.println("¡Bienvenido al sistema de gestión de ventas en línea!");
        System.out.println("Por favor, ingrese los datos del nuevo producto:");

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Descripción del producto: ");
        String descripcion = scanner.nextLine();

        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();

        System.out.print("Cantidad disponible del producto: ");
        int cantidadDisponible = scanner.nextInt();

        scanner.nextLine(); // Limpiar el buffer del scanner

        // Solicitar y validar la categoría del producto
        int categoria = 0;
        do {
            System.out.println("Categorías disponibles:");
            System.out.println("1. Libros");
            System.out.println("2. Electrónica");
            System.out.println("3. Ropa y accesorios");
            System.out.println("4. Alimentos y bebidas");
            System.out.println("5. Papelería");
            System.out.println("6. Deportes");
            System.out.println("7. Productos de belleza y cuidado personal");
            System.out.println("8. Juguetes");
            System.out.println("9. Videojuegos");
            System.out.print("Seleccione la categoría del producto (1-9): ");
            categoria = scanner.nextInt();
            scanner.nextLine();
            if (categoria >= 1 && categoria <= 9) {
                // Todo bien, salir del bucle
                break;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una categoría del 1 al 9.");
            }
        } while (true); // El bucle se repetirá hasta que se seleccione una categoría válida


        // Inicializar el contador de veces comprado en 0
        int vecesComprado = 0;

        // Agregar el producto al catálogo
        boolean productoAgregado = controlador.agregarProducto(nombre, descripcion, precio,
                cantidadDisponible, categoria, vecesComprado);

        if (productoAgregado) {
            System.out.println("El producto se ha agregado al catálogo exitosamente.");
        } else {
            System.out.println("Ha ocurrido un error al agregar el producto al catálogo. Por favor, inténtelo de nuevo.");
        }
    }
}
