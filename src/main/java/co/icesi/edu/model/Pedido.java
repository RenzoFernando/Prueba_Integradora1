package co.icesi.edu.model;

import java.util.Date;
import java.util.List;

public class Pedido {
    private String nombreComprador;
    private List<Producto> listaProductos;
    private double precioTotal;
    private TarjetaPago metodoPago;
    private Date fechaCompra;

    public Pedido(String nombreComprador, List<Producto> listaProductos, double precioTotal, TarjetaPago metodoPago, Date fechaCompra) {
        this.nombreComprador = nombreComprador;
        this.listaProductos = listaProductos;
        this.precioTotal = precioTotal;
        this.metodoPago = metodoPago;
        this.fechaCompra = fechaCompra;
    }

    // Getters y setters
    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
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

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
