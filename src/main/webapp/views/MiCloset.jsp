<%--
  Created by IntelliJ IDEA.
  User: cod3b
  Date: 6/22/2025
  Time: 2:12 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mi Closet</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 500px;
            text-align: center;
        }
        .form-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }
        .form-container input,
        .form-container select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .form-container button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            margin: 10px 0;
            width: 100%;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        .table-container {
            margin-top: 30px;
            width: 80%;
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2>Mi Closet</h2>

        <!-- Botón para agregar prenda -->
        <button onclick="document.getElementById('prendaForm').style.display='block'">Añadir Prenda</button>

        <!-- Formulario para añadir prenda (inicialmente oculto) -->
        <div id="prendaForm" style="display:none;">
            <form action="MiClosetController" method="POST">
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo" required><br>

                <label for="descripcion">Descripción:</label>
                <input type="text" id="descripcion" name="descripcion" required><br>

                <label for="tipo_categoria">Tipo de categoría:</label>
                <select id="tipo_categoria" name="tipo_categoria" required>
                    <option value="camisa">Camisa</option>
                    <option value="pantalón">Pantalón</option>
                    <option value="zapatos">Zapatos</option>
                </select><br>

                <label for="talla">Talla:</label>
                <input type="text" id="talla" name="talla" required><br>

                <label for="color">Color:</label>
                <input type="text" id="color" name="color" required><br>

                <button type="submit">Añadir</button>
                <button type="button" onclick="document.getElementById('prendaForm').style.display='none'">Cancelar</button>
            </form>
        </div>
    </div>
</div>

<!-- Tabla para mostrar las prendas -->
<div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Título</th>
            <th>Descripción</th>
            <th>Tipo Categoría</th>
            <th>Talla</th>
            <th>Color</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <!-- Aquí se deben mostrar las prendas desde la base de datos -->
        <c:forEach var="prenda" items="${prendas}">
            <tr>
                <td>${prenda.titulo}</td>
                <td>${prenda.descripcion}</td>
                <td>${prenda.tipo_categoria}</td>
                <td>${prenda.talla}</td>
                <td>${prenda.color}</td>
                <td><button class="delete-btn" onclick="deletePrenda(${prenda.id})">Eliminar</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function deletePrenda(id) {
        if (confirm("¿Estás seguro de que quieres eliminar esta prenda?")) {
            // Aquí puedes hacer una petición AJAX o redirigir a un controlador para eliminar la prenda
            window.location.href = "MiClosetController?accion=eliminar&id=" + id;
        }
    }
</script>

</body>
</html>