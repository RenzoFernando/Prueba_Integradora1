package co.icesi.edu.model;

public class Producto {
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadDisponible;
    private ProductCategory categoria;
    private int vecesComprado;
    private Producto siguiente; // Referencia al siguiente producto en la lista
    private Producto anterior; // Referencia al producto anterior en la lista

    public Producto(String nombre, String descripcion, double precio, int cantidadDisponible,
                    int categoria, int vecesComprado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.vecesComprado = vecesComprado;
        this.siguiente = null;
        this.anterior = null;
        switch (categoria) {
            case 1:
                this.categoria = ProductCategory.BOOKS;
                break;
            case 2:
                this.categoria = ProductCategory.ELECTRONICS;
                break;
            case 3:
                this.categoria = ProductCategory.CLOTHES;
                break;
            case 4:
                this.categoria = ProductCategory.FOOD;
                break;
            case 5:
                this.categoria = ProductCategory.STATIONERY;
                break;
            case 6:
                this.categoria = ProductCategory.SPORTS;
                break;
            case 7:
                this.categoria = ProductCategory.BEAUTY;
                break;
            case 8:
                this.categoria = ProductCategory.TOYS;
                break;
            case 9:
                this.categoria = ProductCategory.VIDEOGAMES;
                break;
        }
    }

    // Getters y setters

    public Producto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Producto siguiente) {
        this.siguiente = siguiente;
    }

    public Producto getAnterior() {
        return anterior;
    }

    public void setAnterior(Producto anterior) {
        this.anterior = anterior;
    }

    // Otros métodos de la clase
}
