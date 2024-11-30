package com.cibertec.model.dao.impl;

import com.cibertec.DBConnection;
import com.cibertec.model.Producto;
import com.cibertec.model.dao.ProductoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao {

    @Override
    public List<Producto> listarProducto() throws SQLException {

        List<Producto> resultado = new ArrayList<>();
        String query = "SELECT id, nombre, precio, stock, descuento FROM Productos";


        try (  Connection connection = DBConnection.getConnector();
               PreparedStatement statement = connection.prepareStatement(query);
               ResultSet result = statement.executeQuery();)
        {

            while(result.next()) {
                Producto item = new Producto();
                item.setId(result.getInt("id"));
                item.setNombre(result.getString("nombre"));
                item.setPrecio(result.getDouble("precio"));
                item.setStock(result.getInt("stock"));
                item.setDescuento(result.getInt("descuento"));
                resultado.add(item);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return  resultado;


    }

    @Override
    public Producto obtenerProducto(int id) throws SQLException {

        Producto producto = null;

        String query = "SELECT id, nombre, precio, stock, descuento" +
                       "FROM Productos"+
                       "WHERE id = ?";

        try(
                Connection connection = DBConnection.getConnector();
                PreparedStatement statement = connection.prepareStatement(query);
                ) {

            statement.setInt(1,id);
            try(ResultSet result = statement.executeQuery()){
                if(result.next()) {

                 producto=new Producto(
                         result.getInt("id"),
                         result.getString("nombre"),
                         result.getDouble("precio"),
                         result.getInt("stock"),
                         result.getInt("descuento")
                         );
                }
            }

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return producto;
    }




    @Override
    public void registrarProducto(Producto nuevoProducto) throws SQLException {
        String query = "INSERT INTO" +
                       "Productos (nombre, precio, stock, descuento)" +
                       "VALUES(?,?,?,?)";

         try(
                 Connection connection = DBConnection.getConnector();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ){

             statement.setString(1, nuevoProducto.getNombre());
             statement.setDouble(2, nuevoProducto.getPrecio());
             statement.setInt(3, nuevoProducto.getStock());
             statement.setInt(4, nuevoProducto.getDescuento());
             statement.executeUpdate();


         }catch (ClassNotFoundException e){
             throw new RuntimeException(e);
         }
    }
}
