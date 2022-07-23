package org.example;
/*
    esta clase implementa los atributos de la tabla artículo: id, Nombre y precio
    el id lo generamos en el constructor, a cada nuevo artículo le asignamos un id sucesivo comenzando por el número 100
 */
public class Articulo {
    // atributos
    private Integer id;
    private String nombre;
    private String precio;


    // constructor
    public Articulo(Integer id, String nombre, String precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    //getters
    public Integer getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPrecio() {
        return precio;
    }

    // setters
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    // to string

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
