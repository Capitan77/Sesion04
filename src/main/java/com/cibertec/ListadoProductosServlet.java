package com.cibertec;

import com.cibertec.model.Producto;
import com.cibertec.model.dao.ProductoDao;
import com.cibertec.model.dao.impl.ProductoDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ListadoProductosServlet", urlPatterns = "/ListadoProductosServlet")
public class ListadoProductosServlet extends HttpServlet {

    ProductoDao productoDao = new ProductoDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {


        try {
            List<Producto> productos = productoDao.listarProducto();


            request.setAttribute("listaProductos", productos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, resp);
        }catch (SQLException error){
            throw new ServletException("Error al obtener la lista", error);
        }

    }
}
