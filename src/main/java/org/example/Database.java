package org.example;
/*  Se usa el pattern Singleton para que haya una sola instancia de base de datos llamada db
    Entre los atributos está el enlace a la base de datos MySQL bazar
    Se implementan los métodos para conectarse y desconectarse de la base de datos

 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton pattern to create one database instance
public class Database {

    // attributes
    private static Database db = new Database();
    private static final String URL = "jdbc:mysql://localhost:3306/bazartest?serverTimezone=UTC";

    // el atributo para conectarse a la base de datos es privado, solo puede conectarse a la misma usando los métodos de esta clase
    private Connection conn;

    public static Database instance() { // public method called instance that returns a Database object
        return db;
    }

    // private constructor Database
    private Database() {
    }

    // methods
    public Connection getConnection() {
        return conn;
    }
    public void connect() throws SQLException {  // connect to the database using credentials
        conn = DriverManager.getConnection(URL, "alfredo", "Hammil01");
    }
    public void close() throws SQLException {  // Disconnect from the database
        conn.close();
    }
}