package com.cibertec;

import com.cibertec.model.Producto;
import com.cibertec.model.dao.ProductoDao;
import com.cibertec.model.dao.impl.ProductoDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "RegistroProductoServlet", urlPatterns = "/RegistroProductoServlet")
public class RegistroProductoServlet extends HttpServlet {

    ProductoDao productoDao = new ProductoDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

          String nombre = req.getParameter("nombre");
          String precioStr = req.getParameter("precio");
          String stockStr  = req.getParameter("stock");


          try {
                  if(nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
                      throw  new Exception("los campos no deben ser vacias");
                  }
                  double precio = Double.parseDouble(precioStr);
                  int stock = Integer.parseInt(stockStr);
                  int descuento = (precio > 500) ? 10 : 0;

                  // operador ternario
                  // (condicon) ? si es verdadero : si es falso

                 Producto nuevoProducto = new Producto(
                         0,nombre,precio,stock,descuento

                 );

                 productoDao.registrarProducto(nuevoProducto);

                 resp.sendRedirect("ListadoProductosServlet");


          }catch (Exception error){
                         throw  new ServletException("No se pudo registrar",error);
          }

    }
}
