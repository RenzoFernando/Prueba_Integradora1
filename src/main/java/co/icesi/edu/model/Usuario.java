package co.icesi.edu.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String correo;
    private boolean esAdministrador;
    private TarjetaPago[] tarjetas; // Array para almacenar las tarjetas de pago

    private Pedido pedido;


    public Usuario(String nombreUsuario, String contrasena, String nombres, String apellidos,
                   String fechaNacimiento, String direccion, String ciudad, String telefono,
                   String correo, boolean esAdministrador) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.correo = correo;
        this.esAdministrador = esAdministrador;
        this.tarjetas = new TarjetaPago[3]; // Inicializar array de tarjetas con tamaño 3
        inicializarTarjetas();
        this.pedido = new Pedido(nombreUsuario);
    }

    // Getters y setters

    // Métodos para asignar una tarjeta según el tipo especificado
    public boolean asignarTarjeta(int tipoTarjeta, String cardNumber, String securityCodeCVV, int installments) {
        if (tipoTarjeta >= 1 && tipoTarjeta <= 3) {
            TarjetaPago tarjeta = new CreditPayment(cardNumber, securityCodeCVV, installments);
            tarjetas[tipoTarjeta - 1] = tarjeta;
            return true;
        } else {
            return false;
        }
    }

    public boolean asignarTarjeta(int tipoTarjeta, String cardNumber, String securityCodeCVV) {
        if (tipoTarjeta >= 1 && tipoTarjeta <= 3) {
            TarjetaPago tarjeta = new DebitPayment(cardNumber, securityCodeCVV);
            tarjetas[tipoTarjeta - 1] = tarjeta;
            return true;
        } else {
            return false;
        }
    }

    public boolean asignarTarjeta(int tipoTarjeta, String bankName) {
        if (tipoTarjeta >= 1 && tipoTarjeta <= 3) {
            TarjetaPago tarjeta = new PSEPayment(bankName);
            tarjetas[tipoTarjeta - 1] = tarjeta;
            return true;
        } else {
            return false;
        }
    }

    public void inicializarTarjetas() {
        tarjetas = new TarjetaPago[3];
        for (int i = 0; i < tarjetas.length; i++) {
            tarjetas[i] = null;
        }
    }

    public String mostrarTarjetas() {
        StringBuilder estadoTarjetas = new StringBuilder();
        for (int i = 0; i < tarjetas.length; i++) {
            if (tarjetas[i] != null) {
                estadoTarjetas.append("Tarjeta ").append(i + 1).append(": ").append(tarjetas[i]).append("\n");
            } else {
                estadoTarjetas.append("Tarjeta ").append(i + 1).append(": null\n");
            }
        }
        return estadoTarjetas.toString();
    }

    public boolean tieneTarjeta() {
        for (TarjetaPago tarjeta : tarjetas) {
            if (tarjeta != null) {
                return true;
            }
        }
        return false;
    }


    // Otros métodos de la clase


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public boolean addPedido(Producto obj) {
        if (pedido == null) {
            pedido = new Pedido(nombreUsuario);
        }
        return pedido.addPedido(obj);
    }


    public void hacerPedido(int i) {
        // Asignar el método de pago al pedido
        pedido.setMetodoPago(tarjetas[i - 1]);

        // Actualizar la fecha del pedido
        pedido.actualizarFecha();

        // Calcular el total del pedido
        pedido.calcularTotal();

        // Convertir el pedido a formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonPedido = gson.toJson(pedido);

        // Guardar el JSON en un archivo
        try {
            FileWriter writer = new FileWriter("src/main/resources/pedido.json");
            writer.write(jsonPedido);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Establecer la relación del usuario con el pedido como nula
        pedido = null;
    }

}
