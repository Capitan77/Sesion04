<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cibertec.model.Producto"%>

<!DOCTYPE html>
<html>
<head>
    <title>Listado de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Listado de Productos</h1>
        <a href="registro.jsp" class="btn btn-success mb-3">Agregar nuevo producto</a>
        <!-- Bloque listado de productos -->

        <% List<Producto> items = (List<Producto>) request.getAttribute("listaProductos"); %>

        <% for (Producto item : items) { %>

            <div class="col-md-4">
                <%= item.getNombre() %>
                <%= item.getPrecio() %>
                <%= item.getStock() %>
            </div>

        <% } %>


    </div>
</body>
</html>