package co.icesi.edu.model;

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

public class Controlador {

    private ListaEnlazada<Usuario> listaUsuarios;


    public Controlador() {
        this.listaUsuarios = new ListaEnlazada<>();
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        try {
            // Leer el archivo JSON de usuarios
            FileReader reader = new FileReader("src/main/resources/usuarios.json");

            // Configurar Gson para deserializar la lista de usuarios
            Gson gson = new GsonBuilder().create();
            Type tipoListaUsuarios = new TypeToken<List<Usuario>>(){}.getType();

            // Deserializar el JSON a una lista de usuarios
            List<Usuario> usuarios = gson.fromJson(reader, tipoListaUsuarios);

            // Agregar los usuarios a la lista enlazada
            for (Usuario usuario : usuarios) {
                listaUsuarios.agregar(usuario);
            }

            // Cerrar el lector
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void guardarUsuarios() {
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

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        return true;
    }
    public boolean esAdministrador() {
        return true;
    }
}

