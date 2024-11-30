package com.cibertec.model.dao;

import com.cibertec.model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDao {

    List<Producto> listarProducto() throws SQLException;
    Producto obtenerProducto(int id) throws SQLException;
}
