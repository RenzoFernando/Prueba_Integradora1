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
        if (listaPedidos == null) {
            listaPedidos = new ListaEnlazada<>();
        }
        // Verificar si el producto ya está en la lista de pedidos
        if (listaPedidos != null && !listaPedidos.isEmpty()) {
            for (Producto p : listaPedidos) {
                if (p.equals(producto)) {
                    return false; // Producto duplicado, no se agrega
                }
            }
        }

        // Si el producto no está en la lista de pedidos, agregarlo
        Producto productoCopia = new Producto(producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),
                producto.getCantidadDisponible(), producto.getCategoria().ordinal(),
                producto.getVecesComprado());
        listaPedidos.agregar(productoCopia);
        return true; // Producto agregado correctamente
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


    public void actualizarFecha(){
        Calendar cal = Calendar.getInstance();
        this.fechaCompra = cal.getTime();
    }

    public void calcularTotal() {
        double total = 0.0;
        if (listaPedidos != null && !listaPedidos.isEmpty()) {
            for (Producto producto : listaPedidos) {
                total += producto.getPrecio();
            }
        }
        precioTotal = total;
        String x = toString();
        System.out.println(x);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("nombreComprador='").append(nombreComprador).append('\'');
        sb.append(", listaProductos=").append(listaPedidos);
        sb.append(", precioTotal=").append(precioTotal);
        sb.append(", metodoPago=").append(metodoPago);
        sb.append(", fechaCompra=").append(fechaCompra);
        sb.append('}');
        return sb.toString();
    }


}
