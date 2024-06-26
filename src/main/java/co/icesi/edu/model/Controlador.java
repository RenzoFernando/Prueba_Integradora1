package co.icesi.edu.model;

//----------------------------------------------------------------//

import co.icesi.edu.structures.ListaEnlazada;
import co.icesi.edu.structures.Nodo;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

//----------------------------------------------------------------//

public class Controlador {

    //----------------------------------------------------------------//

    private ListaEnlazada<Usuario> listaUsuarios;
    private ListaEnlazada<Producto> listaProductos;
    private ListaEnlazada<Pedido> listaPedidos;
    private Usuario usuarioActual;

    private Producto productoActual;
    private int indiceProductoActual;


    //----------------------------------------------------------------//


    public Controlador() {
        this.listaUsuarios = new ListaEnlazada<>();
        cargarUsuarios();
        this.listaProductos = new ListaEnlazada<>();
        cargarProductos();
        this.listaPedidos = new ListaEnlazada<>();
        cargarPedidos();

        this.indiceProductoActual = 0;
        usuarioActual = null;
        productoActual = null;
    }


    //----------------------------------------------------------------//

    public String listarUsuarios() {
        String x = "";
        Nodo<Usuario> nodoActual = listaUsuarios.getCabeza();

        while (nodoActual != null) {
            Usuario usuario = nodoActual.getDato();
            x += usuario.getNombreUsuario() + "\n";
            nodoActual = nodoActual.getSiguiente();
        }

        return x;
    }



    //----------------------------------------------------------------//

    public boolean eliminarProducto(String nombreProducto) {
        // Verificar si la lista de productos está vacía
        if (listaProductos.isEmpty()) {
            System.out.println("La lista de productos está vacía.");
            return false;
        }

        Producto p = buscarProducto(nombreProducto);

        // Buscar el producto en la lista
        Nodo<Producto> nodoProducto = listaProductos.buscar(p);
        if (nodoProducto != null) {
            // Eliminar el producto de la lista
            listaProductos.eliminar(nodoProducto.getDato());
            System.out.println("Producto '" + nombreProducto + "' eliminado exitosamente.");
            return true;
        } else {
            System.out.println("El producto '" + nombreProducto + "' no se encontró en la lista.");
            return false;
        }
    }

    private Producto buscarProducto(String nombreProducto) {
        Nodo<Producto> nodoActual = listaProductos.getCabeza();
        while (nodoActual != null) {
            Producto productoActual = nodoActual.getDato();
            if (productoActual.getNombre().equals(nombreProducto)) {
                return productoActual;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null; // Producto no encontrado
    }




    //----------------------------------------------------------------//
    //PARA LOS PEDIDOS

    public String mostrarTodosLosPedidos() {
        StringBuilder resultado = new StringBuilder();
        boolean algunPedido = false;

        for (Pedido pedido : listaPedidos) {
            resultado.append(pedido.toString()).append("\n");
            algunPedido = true;
        }

        if (!algunPedido) {
            resultado.append("No hay pedidos registrados.");
        }

        return resultado.toString();
    }


    public String mostrarPedidosUsuarioActual() {
        StringBuilder resultado = new StringBuilder();
        boolean usuarioEncontrado = false;

        for (Pedido pedido : listaPedidos) {
            if (pedido.getNombreComprador().equals(usuarioActual.getNombreUsuario())) {
                resultado.append(pedido.toString()).append("\n");
                usuarioEncontrado = true;
            }
        }

        if (!usuarioEncontrado) {
            resultado.append("No hay pedidos para el usuario actual.");
        }

        return resultado.toString();
    }


    private void cargarPedidos() {
        try {
            // Leer el archivo JSON de usuarios
            FileReader reader = new FileReader("src/main/resources/facturaPedidos.json");

            // Configurar Gson para deserializar la lista de usuarios
            Gson gson = new GsonBuilder().create();
            Type tipoListPedidos = new TypeToken<List<Pedido>>() {
            }.getType();

            // Deserializar el JSON a una lista de usuarios
            List<Pedido> pedidos = gson.fromJson(reader, tipoListPedidos);

            // Agregar los usuarios a la lista enlazada
            if (pedidos != null) {
                for (Pedido pedido : pedidos) {
                    listaPedidos.agregar(pedido);
                }
            }

            // Cerrar el lector
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void guardarPedidos(Pedido obj) {
        listaPedidos.agregar(obj);

        try {
            // Configurar Gson para serializar la lista de usuarios
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convertir la lista enlazada de usuarios a JSON
            String jsonPedidos = gson.toJson(listaPedidos.toArray());

            // Escribir el JSON en un archivo
            FileWriter writer = new FileWriter("src/main/resources/facturaPedidos.json");
            writer.write(jsonPedidos);

            // Cerrar el escritor
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ajustarCantidades(Pedido obj) {
        boolean bandera;
        int x = obj.sizeLista();

        for (int i = 0; i < x; i++) {
            String nombrePedido = obj.obtenerNombreProducto(i+1);

            for (int j = 0; j < listaProductos.size(); j++) {
                Producto producto = listaProductos.get(j);
                String nombreProducto = producto.getNombre();

                if (nombrePedido.equals(nombreProducto)) {
                    System.out.println(nombrePedido + nombreProducto);
                    // Obtener la cantidad actual del producto
                    int cantidadProducto = producto.getCantidadDisponible();
                    // Obtener la cantidad del pedido
                    int cantidadPedido = obj.obtenerCantidadProducto(i+1);
                    if (cantidadPedido !=-1) {
                        // Calcular la nueva cantidad del producto
                        int nuevaCantidad = cantidadProducto - cantidadPedido;
                        // Actualizar la cantidad del producto y esa vaina
                        producto.setCantidadDisponible(nuevaCantidad);
                        producto.setVecesComprado(producto.getVecesComprado()+cantidadPedido);
                    }
                }
            }
        }
        guardarProductos();
        cargarProductos();
    }



    //----------------------------------------------------------------//
    //PARA LOS USUARIOS
    private void cargarUsuarios() {
        try {
            // Leer el archivo JSON de usuarios
            FileReader reader = new FileReader("src/main/resources/usuarios.json");

            // Configurar Gson para deserializar la lista de usuarios
            Gson gson = new GsonBuilder().create();
            Type tipoListaUsuarios = new TypeToken<List<Usuario>>() {
            }.getType();

            // Deserializar el JSON a una lista de usuarios
            List<Usuario> usuarios = gson.fromJson(reader, tipoListaUsuarios);

            // Agregar los usuarios a la lista enlazada
            if (usuarios != null) {
                // Si la lista no es null, agregar los usuarios a la lista enlazada
                for (Usuario usuario : usuarios) {
                    listaUsuarios.agregar(usuario);
                }
            }

            // Cerrar el lector
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void guardarUsuarios() {
        try {
            // Configurar Gson para serializar la lista de usuarios
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convertir la lista enlazada de usuarios a JSON
            String jsonUsuarios = gson.toJson(listaUsuarios.toArray());

            // Escribir el JSON en un archivo
            FileWriter writer = new FileWriter("src/main/resources/usuarios.json");
            writer.write(jsonUsuarios);

            // Cerrar el escritor
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------//

    private boolean existeUsuario(String nombreUsuario) {
        // Recorrer la lista de usuarios y verificar si existe un usuario con el mismo nombre de usuario
        Nodo<Usuario> nodoActual = listaUsuarios.getCabeza();
        while (nodoActual != null) {
            Usuario usuarioActual = nodoActual.getDato();
            if (usuarioActual.getNombreUsuario().equals(nombreUsuario)) {
                return true; // El usuario ya existe
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false; // El usuario no existe
    }

    //----------------------------------------------------------------//

    public boolean registrarUsuario(String nombreUsuario, String contrasena, String nombres, String apellidos,
                                    String fechaNacimiento, String direccion, String ciudad, String telefono, String correo,
                                    boolean esAdministrador) {
        // Verificar si el nombre de usuario ya está en uso
        if (existeUsuario(nombreUsuario)) {
            return false; // El usuario ya existe
        }
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, nombres, apellidos, fechaNacimiento,
                direccion, ciudad, telefono, correo, esAdministrador);

        // Agregar el nuevo usuario a la lista
        listaUsuarios.agregar(nuevoUsuario);

        // Guardar la lista actualizada de usuarios
        guardarUsuarios();

        return true; // Registro exitoso
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        // Buscar el usuario por su nombre de usuario
        Usuario usuario = buscarUsuario(nombreUsuario);
        usuarioActual = buscarUsuario(nombreUsuario);

        // Verificar si se encontró un usuario con ese nombre de usuario
        if (usuario != null) {
            // Verificar si la contraseña coincide
            if (usuario.getContrasena().equals(contrasena)) {
                // Inicio de sesión exitoso
                return true;
            }
        }
        // Inicio de sesión fallido
        return false;
    }

    public boolean esAdministrador(String nombreUsuario) {
        // Buscar el usuario por su nombre de usuario
        Usuario usuario = buscarUsuario(nombreUsuario);

        // Verificar si se encontró un usuario con ese nombre de usuario
        if (usuario != null) {
            // Verificar si el usuario es administrador
            return usuario.isEsAdministrador();
        }
        // Si no se encuentra el usuario, no se puede determinar si es administrador o no
        return false;
    }

    private Usuario buscarUsuario(String nombreUsuario) {
        Nodo<Usuario> nodoActual = listaUsuarios.getCabeza();
        while (nodoActual != null) {
            Usuario usuarioActual = nodoActual.getDato();
            if (usuarioActual.getNombreUsuario().equals(nombreUsuario)) {
                return usuarioActual;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null; // Usuario no encontrado
    }

    public String nombreUsuarioActual(){
        return usuarioActual.getNombreUsuario();
    }

    //----------------------------------------------------------------//
    //PARA LOS PRODUCTOS

    public boolean agregarProducto(String nombre, String descripcion, double precio,
                                   int cantidadDisponible, int categoria, int vecesComprado) {
        // Verificar si ya existe un producto con el mismo nombre
        if (existeProducto(nombre)) {
            return false; // El producto ya existe
        }

        // Crear un nuevo producto con los datos proporcionados
        Producto nuevoProducto = new Producto(nombre, descripcion, precio, cantidadDisponible, categoria, vecesComprado);

        // Agregar el nuevo producto al catálogo
        listaProductos.agregar(nuevoProducto);

        guardarProductos();

        // Devolver true si el producto se agregó correctamente
        return true;
    }

    private boolean existeProducto(String nombre) {
        // Recorrer la lista de productos y verificar si existe un producto con el mismo nombre
        Nodo<Producto> nodoActual = listaProductos.getCabeza();
        while (nodoActual != null) {
            Producto productoActual = nodoActual.getDato();
            if (productoActual.getNombre().equals(nombre)) {
                return true; // El producto ya existe
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false; // El producto no existe
    }

    private void cargarProductos() {
        try {
            // Leer el archivo JSON de productos
            FileReader reader = new FileReader("src/main/resources/productos.json");

            // Configurar Gson para deserializar la lista de productos
            Gson gson = new GsonBuilder().create();
            Type tipoListaProductos = new TypeToken<List<Producto>>() {
            }.getType();

            // Deserializar el JSON a una lista de productos
            List<Producto> productos = gson.fromJson(reader, tipoListaProductos);

            // Agregar los productos a la lista enlazada
            if (productos != null) {
                for (Producto producto : productos) {
                    listaProductos.agregar(producto);
                }
            }

            // Cerrar el lector
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarProductos() {
        try {
            // Configurar Gson para serializar la lista de productos
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convertir la lista enlazada de productos a JSON
            String jsonProductos = gson.toJson(listaProductos.toArray());

            // Escribir el JSON en un archivo
            FileWriter writer = new FileWriter("src/main/resources/productos.json");
            writer.write(jsonProductos);

            // Cerrar el escritor
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //----------------------------------------------------------------//
    //PARA MOSTRAR CATALOGO DE PRODUCTOS
    public String obtenerProductoActual() {
        Nodo<Producto> nodoActual = listaProductos.getCabeza();
        for (int i = 0; i < indiceProductoActual; i++) {
            if (nodoActual != null) {
                nodoActual = nodoActual.getSiguiente();
            }
        }
        if (nodoActual != null) {
            return "PRODUCTO #" + (indiceProductoActual + 1) + "\n" + nodoActual.getDato().toString();
        } else {
            return "No hay productos en el catálogo.";
        }
    }

    public String obtenerSiguienteProducto() {
        if (indiceProductoActual < listaProductos.size() - 1) {
            indiceProductoActual++;
        } else {
            return "No hay productos en el catálogo.";
        }
        return obtenerProductoActual();
    }

    public String obtenerProductoAnterior() {
        if (indiceProductoActual > 0) {
            indiceProductoActual--;
        } else {
            return "No hay productos en el catálogo.";
        }
        return obtenerProductoActual();
    }


    public boolean agregarProductoAlCarrito() {
        //Nodo<Producto> nodoActual = listaProductos.getCabeza();
        /*
        for (int i = 0; i < indiceProductoActual; i++) {
            if (nodoActual != null) {
                nodoActual = nodoActual.getSiguiente();
            }
        }
         */
        // Obtener el producto actual del catálogo

        // Verificar si se encontró el producto actual
        Producto producto = listaProductos.get(indiceProductoActual);

        if (listaProductos != null) {
            // Crear una copia del producto actual
            Producto productoActual = null;

            productoActual = producto;
            int cantidad = productoActual.getCantidadDisponible();
            if (cantidad > 0) {
                cantidad = 1;
            }
            Producto productoCopia = new Producto(productoActual.getNombre(), productoActual.getDescripcion(),
                    productoActual.getPrecio(), cantidad, productoActual.getCategoria().ordinal(),
                    productoActual.getVecesComprado());

            // Agregar el producto al carrito utilizando el método addPedido de la clase Pedido
            return usuarioActual.addPedido(productoCopia);

        } else {
            // No se encontró el producto actual en el catálogo
            return false;
        }
    }

    //----------------------------------------------------------------//
    //PARA METODOS DE PAGO


    public boolean addMetodoPago(int i, String cardNumber, String securityCodeCVV, int installments){
        return usuarioActual.asignarTarjeta(i, cardNumber, securityCodeCVV, installments);

    }

    public boolean addMetodoPago(int i, String cardNumber, String securityCodeCVV) {
        return usuarioActual.asignarTarjeta(i, cardNumber, securityCodeCVV);
    }

    public boolean addMetodoPago(int i, String bankName){
        return usuarioActual.asignarTarjeta(i, bankName);
    }

    public  String mostrarTarjetas() {
        return usuarioActual.mostrarTarjetas();
    }

    public boolean tieneTarjeta() {
        return usuarioActual.tieneTarjeta();
    }

    //----------------------------------------------------------------//
    //PARA CANCELAR EL PEDIDO

    public void hacerPedido(int i) {
        Pedido obj = usuarioActual.facturaPedido(i);
        guardarPedidos(obj);
        ajustarCantidades(obj);
        usuarioActual.borrarPedidoAnterior();
    }

    public void borrardatos() {
        usuarioActual.borrarPedidoAnterior();
    }

    public String carritoActual(){
        return usuarioActual.carritoActual();
    }

    public boolean editarCantidad(int numeroP, int newCantidad){

        String nameProductoPedido = usuarioActual.nameProduct(numeroP);
        int actualProducto = 0;
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto producto = listaProductos.get(i);
            if (producto.getNombre().equals(nameProductoPedido)) {
                actualProducto = i;
            }
        }

        Producto producto = listaProductos.get(actualProducto);

        int cantidadReal = producto.getCantidadDisponible();

        return usuarioActual.editarCantidad(numeroP,cantidadReal, newCantidad);

    }

}

