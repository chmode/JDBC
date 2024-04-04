package services;

import java.sql.SQLException;
import java.util.List;


public interface IService<T> {
    void ajouter(T t) ;
    boolean delete(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> readAll() ;
}
