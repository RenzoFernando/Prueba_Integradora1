package co.icesi.edu.model;

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

    }

    // Getters y setters

    public TarjetaPago[] getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(TarjetaPago[] tarjetas) {
        this.tarjetas = tarjetas;
    }


    // Métodos para agregar una nueva tarjeta de pago
    public void agregarTarjeta(TarjetaPago nuevaTarjeta) {
        for (int i = 0; i < tarjetas.length; i++) {
            if (tarjetas[i] == null) {
                tarjetas[i] = nuevaTarjeta;
                break;
            }
        }
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
}
