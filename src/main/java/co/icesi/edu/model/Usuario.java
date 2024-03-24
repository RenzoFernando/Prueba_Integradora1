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

    public Pedido getPedido() {
        return pedido;
    }

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

////////////////////////////////////////////////////////////////////////////////

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
                String x = tarjetas[i].resumen;
                estadoTarjetas.append("Metodo de Pago ").append(i + 1).append(": ").append(x).append("\n");
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

    public boolean addPedido(Producto obj) {
        if (pedido == null) {
            pedido = new Pedido(nombreUsuario);
        }
        return pedido.addPedido(obj);
    }


    public Pedido facturaPedido(int i) {
        // Asignar el método de pago al pedido
        pedido.setMetodoPago(tarjetas[i - 1]);

        // Actualizar la fecha del pedido
        pedido.actualizarFecha();

        // Calcular el total del pedido
        pedido.calcularTotal();

        return pedido;
    }

    public String carritoActual() {
        return pedido.obtenerDetalleProductos();
    }

    public void borrarPedidoAnterior() {
        pedido = null;
        pedido = new Pedido(nombreUsuario);
    }

    public boolean editarCantidad(int numeroP,int cantidadReal, int neewCantidad) {
        return pedido.editarCantidad(numeroP, cantidadReal, neewCantidad);
    }

    public String nameProduct(int numeroP) {
        return pedido.obtenerNombreProducto(numeroP);
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



    public int sizeListaPedidos() {
        return pedido.sizeLista();
    }
}
