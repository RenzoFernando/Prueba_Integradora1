package co.icesi.edu.model;

import co.icesi.edu.structures.ListaEnlazada;
import java.util.Date;
import java.util.Calendar;

public class Pedido {
    private String nombreComprador;
    private ListaEnlazada<Producto> listaPedidos;
    private double precioTotal;
    private TarjetaPago metodoPago;
    private Date fechaCompra;

    public Pedido(String nombreComprador) {
        this.nombreComprador = nombreComprador;
        this.listaPedidos = null;
        this.precioTotal = 0.0;
        this.metodoPago = null;
        this.fechaCompra = null;
    }

    public boolean addPedido(Producto producto) {
        boolean result = true;

        if (listaPedidos == null) {
            listaPedidos = new ListaEnlazada<>();
        }
        // Verificar si el producto ya está en la lista de pedidos
        if (!listaPedidos.isEmpty()) {
            for (int i = 0; i < listaPedidos.size(); i++) {
                Producto p = listaPedidos.get(i);

                if (p.getNombre().equals(producto.getNombre())) {
                    // Producto duplicado, no se agrega
                    result = false;
                }
            }
        }
        if (result && producto.getCantidadDisponible() > 0) {
            // Si el producto no está en la lista de pedidos, agregarlo
            Producto productoCopia = new Producto(producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),
                    producto.getCantidadDisponible(), producto.getCategoria().ordinal(),
                    producto.getVecesComprado());
            listaPedidos.agregar(productoCopia);
        }
        return result;
    }


    // Getters y setters
    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public ListaEnlazada<Producto> getListaProductos() {
        return listaPedidos;
    }

    public void setListaProductos(ListaEnlazada<Producto> listaProductos) {
        this.listaPedidos = listaProductos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public TarjetaPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(TarjetaPago metodoPago) {
        this.metodoPago = metodoPago;
    }


    public void actualizarFecha() {
        Calendar cal = Calendar.getInstance();
        this.fechaCompra = cal.getTime();
    }

    public void calcularTotal() {
        double total = 0.0;
        if (listaPedidos != null && !listaPedidos.isEmpty()) {
            for (Producto producto : listaPedidos) {
                total += producto.getPrecio()*producto.getCantidadDisponible();
            }
        }
        precioTotal = total;
    }


    @Override
    public String toString(){
        return  "----------------------------------------------------------------\n" +
                "Información de la compra:\n" +
                "Comprador: " + nombreComprador + "\n" +
                obtenerDetalleProductos() + "\n" +
                "Método de Pago: " + metodoPago.resumen + "\n" +
                "Fecha de Compra: " + fechaCompra + "\n" +
                "----------------------------------------------------------------\n";
    }

    public String obtenerDetalleProductos() {
        StringBuilder detalleProductos = new StringBuilder();
        double total = 0.0;

        int i = 1;
        if (listaPedidos != null && !listaPedidos.isEmpty()) {
            for (Producto producto : listaPedidos) {
                detalleProductos.append("Producto: #" + i + ": ").append(producto.getNombre())
                        .append(", Precio: ").append(producto.getPrecio())
                        .append(", Cantidad: ").append(producto.getCantidadDisponible())
                        .append("\n");
                total += producto.getPrecio()*producto.getCantidadDisponible();
                i++;
            }
        }

        detalleProductos.append("Total: ").append(total);

        return detalleProductos.toString();
    }

    public boolean editarCantidad(int numeroP, int cantidadReal, int newCantidad) {
        boolean result = false;
        if (listaPedidos != null && !listaPedidos.isEmpty()) {
            if (numeroP-1 >= 0 && numeroP-1 <= listaPedidos.size()) {
                Producto producto = listaPedidos.get(numeroP-1);
                if (newCantidad <= cantidadReal) {
                    producto.setCantidadDisponible(newCantidad);
                    result = true;
                }
            }
        }
        return result;
    }

    public String obtenerNombreProducto(int numeroP){
        if (numeroP-1 >= 0 && numeroP-1 <= listaPedidos.size()) {
            Producto producto = listaPedidos.get(numeroP-1);
            return producto.getNombre();
        } else {
            return "";
        }
    }

    public int obtenerCantidadProducto(int numeroP) {
        if (numeroP-1 >= 0 && numeroP-1 <= listaPedidos.size()) {
            Producto producto = listaPedidos.get(numeroP-1);
            return producto.getCantidadDisponible();
        } else {
            return -1;
        }
    }

    public int sizeLista() {
        return listaPedidos.size();
    }
}
