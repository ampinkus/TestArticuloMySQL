package org.example;
/*
    Data Access Object Pattern or DAO pattern is used to separate low level data accessing API or operations from high level business services.
    Following are the participants in Data Access Object Pattern:
    * Data Access Object Interface - This interface defines the standard operations to be performed on a model object(s).
    * Data Access Object concrete class - This class implements above interface.
      This class is responsible to get data from a data source which can be database / xml or any other storage mechanism.
    * Model Object or Value Object - This object is simple POJO containing get/set methods to store data retrieved using DAO class.
       (POJO: Plain Old Java Objects)
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/*
    Specifying angular brackets after the class name means you are creating a temporary data type which can hold any type of data.
    This is just for convenience:
    <T> is referred to as any type
    <E> as element type
    <N> as number type
    <V> as value
    <K> as key
 */

public interface Dao<T> {
    // save a new article
    void save(T entidad) throws SQLException;

    // find an article by id
    String findById(Integer id);

    // update an article
    void update(T entidad);

    // delete an article
    void delete(Integer id);

    // print all articles
    void printAll();

    //delete the table
    void deleteAll();


}
