package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;


class ArticuloDaoTest {

    @BeforeEach
    void setUp() {
        Database db = Database.instance(); // creo la instancia db de la clase Database
        try { // me conecto a la base de datos
            db.connect();
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
        }
    }

    @AfterEach
    void tearDown() {
        Database db = Database.instance(); // creo la instancia db de la clase Database
        try { // al final cierro la conexion con la base de datos
            db.close();
            System.out.println("Disconnected");
        } catch (SQLException e) {
            System.out.println("Cannot close database connection.");
        }
    }

    @Test
    void save() throws SQLException {
        // creo una conexión con la base de datos
        var conn = Database.instance().getConnection();
        // creo una instancia articuloDao para poder usar los métodos de la clase
        ArticuloDao articuloDao = new ArticuloDao();
        // limpio la base de datos antes de comenzar
        articuloDao.deleteAll();
        // creo un artículo
        Articulo articulo = new Articulo(100,"cucharon","1200");
        // guardo el artículo en la base de datos
        articuloDao.save(articulo);

        // traigo el artículo de la base de datos
        // preparo una variable que guarde un query
        var stmt = conn.createStatement();
        // ejecuto el query y guardo el resultado en rs
        var rs = stmt.executeQuery("select nombre from bazartest.articulo where id = 100");
        // es el ultimo query
        var result = rs.next();

        // testeo el resultado:

        // si no puedo recuperar el valor de la base de datos genero un error
        // si hay un error result es falso
        Assertions.assertTrue(result, "No puedo recuperar el artículo insertado");
        // recupero el nombre dle rtículo que tenía índice 100 (cucharon)
        var name = rs.getString("nombre");
        // comparo el nombre del artículo que guarde con el que recuperé de la base de datos
        Assertions.assertEquals(articulo.getNombre(), name, "El nombre del articulo no coincide con el guardado");


        stmt.close();

    }
}