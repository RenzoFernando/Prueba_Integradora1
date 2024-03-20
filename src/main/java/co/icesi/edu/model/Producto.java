package co.icesi.edu.model;

public class Producto {
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadDisponible;
    private ProductCategory categoria;
    private int vecesComprado;


    public Producto(String nombre, String descripcion, double precio, int cantidadDisponible,
                    int categoria, int vecesComprado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.vecesComprado = vecesComprado;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public ProductCategory getCategoria() {
        return categoria;
    }

    public void setCategoria(ProductCategory categoria) {
        this.categoria = categoria;
    }

    public int getVecesComprado() {
        return vecesComprado;
    }

    public void setVecesComprado(int vecesComprado) {
        this.vecesComprado = vecesComprado;
    }

    // Otros métodos de la clase
}
