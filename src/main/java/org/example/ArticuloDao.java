package org.example;
// En esta clase tengo que implementar todos los metodos definidos en Dao.

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticuloDao implements Dao<Articulo>{ // tengo que especificar para que clase implemento el Dao, Articulo en este caso

    @Override
    public void save(Articulo articulo) throws SQLException {
        var conn = Database.instance().getConnection();

        try {
            /*
            preparo la instruccion de MySQL para insertar datos en la tabla (indice,nombre,precio) usando campos variables (?,?,?)
            stmt.setString(1,articulo.getNombre()); es el primer campo de (?,?) ya que el indice es 1
            stmt.setString(2,articulo.getNombre()); es el primer campo de (?,?) ya que el nombre es 2
            stmt.setString(3,articulo.getPrecio()); es el segundo campo de (?,?) ya que el precio es 3
            */
            var stmt = conn.prepareStatement("insert into articulo (id,nombre,precio) values (?,?,?)");
            stmt.setInt(1,articulo.getId());
            stmt.setString(2,articulo.getNombre());
            stmt.setString(3,articulo.getPrecio());
            stmt.execute(); // ejecuto la instruccion de SQL
            stmt.close();  // cierro la instruccion

        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public String findById(Integer id) {
        String row = "";
        var conn = Database.instance().getConnection();
        ResultSet rs;

        try {
            var stmt = conn.prepareStatement("select * from articulo where id=? ");
            stmt.setInt(1, id );
            // Execute SQL query
            rs = stmt.executeQuery();

            while (rs.next()) {
                row = (rs.getString("nombre") +
                        ", " + rs.getString("precio"));
            }
            System.out.println();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return row;
    }

    @Override
    public void update(Articulo articulo) {
        var conn = Database.instance().getConnection();

        try {
            var stmt = conn.prepareStatement("update articulo set nombre=?, precio=? where id=?");
            stmt.setString(1, articulo.getNombre());
            stmt.setString(2, articulo.getPrecio());
            stmt.setInt(3, articulo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        var conn = Database.instance().getConnection();

        try {
            var stmt = conn.prepareStatement("delete from articulo where id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void printAll() {
        var conn = Database.instance().getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * FROM articulo order by id");
            while (rs.next()) {
                System.out.println(rs.getString("id") + ", " + rs.getString("nombre") +
                        ", " + rs.getString("precio"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteAll() {
        var conn = Database.instance().getConnection();

        try {
            var stmt = conn.prepareStatement("delete from articulo");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}