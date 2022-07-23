package org.example;
/*
Este projecto es la implementacion de una base de datos bazar con una tabla llamada articulos cuyos campos son:
    * id que se genera automáticamente
    * nombre del arículo:  String
    * precio del artículo:  Integer
Se implementa el CRUD a través de Java con JDBC
 */
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {

        Database db = Database.instance(); // creo la instancia db de la clase Database
        try { // me conecto a la base de datos
            db.connect();
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
        }

        // creo una instancia articuloDao para poder usar los métodos de la clase
        ArticuloDao articuloDao = new ArticuloDao();

        // limpio la base de datos antes de comenzar
        articuloDao.deleteAll();

        // creo un arreglo de 3 artículos
        Articulo[] articulo = new Articulo[3];
        // creo algunas instancias de la clase Articulos
        articulo[0] = new Articulo(100,"cucharon","1200");
        articulo[1] = new Articulo(101,"tenedor","400");
        articulo[2] = new Articulo(102,"jarra","3000");

        // cargo la base de datos con los artículos
        for (int i = 0; i < articulo.length; i++ ){
            articuloDao.save(articulo[i]);
        }

        //imprimo todos los artículos
        System.out.println("Impresion inicial de los articulos");
        articuloDao.printAll();
        System.out.println();

        // modifico el artículo con indice 101
        articuloDao.update(new Articulo(101,"fuente","2300"));
        System.out.println("Impresion luego de modificar el articulo con id 101 ");
        articuloDao.printAll();
        System.out.println();

        // busco el artículo con indice 101
        System.out.print("El articulo con indice 102 es: ");
        System.out.println(articuloDao.findById(102));
        System.out.println();

        // borro el artículo con indice 100
        articuloDao.delete(100);
        System.out.println("Impresion luego de borrar el articulo con id 100 ");
        articuloDao.printAll();
        System.out.println();

        try { // al final cierro la conexion con la base de datos
            db.close();
            System.out.println("Disconnected");
        } catch (SQLException e) {
            System.out.println("Cannot close database connection.");
        }
    }
}