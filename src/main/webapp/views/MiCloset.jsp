<%--
  Created by IntelliJ IDEA.
  User: cod3b
  Date: 6/22/2025
  Time: 2:12 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Mi Closet</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .header {
            background-color: #007bff;
            color: white;
            padding: 15px;
            text-align: center;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .user-info {
            float: right;
            font-size: 14px;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 3px;
            margin-left: 10px;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .form-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 500px;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }
        .form-container input,
        .form-container select,
        .form-container textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        .form-container textarea {
            height: 80px;
            resize: vertical;
        }
        .form-container button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            margin: 10px 5px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        .cancel-btn {
            background-color: #6c757d !important;
        }
        .cancel-btn:hover {
            background-color: #545b62 !important;
        }
        .table-container {
            width: 90%;
            max-width: 1200px;
            overflow-x: auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
        tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 3px;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        .message {
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            text-align: center;
        }
        .success-message {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        .no-prendas {
            text-align: center;
            padding: 40px;
            color: #6c757d;
        }
    </style>
</head>
<body>

<!-- Header con información del usuario -->
<div class="header">
    <div class="user-info">
        Bienvenido, <strong>${sessionScope.nombreUsuario}</strong>
        <button class="logout-btn" onclick="logout()">Cerrar Sesión</button>
    </div>
    <h1>Mi Closet Digital</h1>
</div>

<div class="container">
    <div class="form-container">
        <h2>Gestionar mi Closet</h2>

        <!-- Mostrar mensajes de éxito o error -->
        <c:if test="${not empty successMessage}">
            <div class="message success-message">
                    ${successMessage}
            </div>
        </c:if>

        <c:if test="${not empty errorMessage}">
            <div class="message error-message">
                    ${errorMessage}
            </div>
        </c:if>

        <!-- Botones de navegación -->
        <div style="margin-bottom: 15px;">
            <button onclick="goHome()" style="background-color: #28a745; margin-right: 10px;">🏠 Regresar al Inicio</button>
            <button onclick="toggleForm()">Añadir Nueva Prenda</button>
        </div>

        <!-- Formulario para añadir prenda (inicialmente oculto) -->
        <div id="prendaForm" style="display:none; margin-top: 20px;">
            <h3>Nueva Prenda</h3>
            <form action="${pageContext.request.contextPath}/MiClosetController" method="POST">
                <label for="titulo">Título *:</label>
                <input type="text" id="titulo" name="titulo" placeholder="Ej: Camisa azul de verano" required>

                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" placeholder="Describe tu prenda..."></textarea>

                <label for="tipo_categoria">Tipo de categoría *:</label>
                <select id="tipo_categoria" name="tipo_categoria" required>
                    <option value="">Selecciona una categoría</option>
                    <option value="camisa">Camisa</option>
                    <option value="pantalon">Pantalón</option>
                    <option value="zapatos">Zapatos</option>
                    <option value="vestido">Vestido</option>
                    <option value="falda">Falda</option>
                    <option value="chaqueta">Chaqueta</option>
                    <option value="sueter">Suéter</option>
                    <option value="shorts">Shorts</option>
                    <option value="accesorios">Accesorios</option>
                </select>

                <label for="talla">Talla *:</label>
                <select id="talla" name="talla" required>
                    <option value="">Selecciona talla</option>
                    <option value="XS">XS</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                    <option value="XXL">XXL</option>
                    <option value="Talla única">Talla única</option>
                </select>

                <label for="color">Color *:</label>
                <input type="text" id="color" name="color" placeholder="Ej: Azul marino" required>

                <div>
                    <button type="submit">Agregar Prenda</button>
                    <button type="button" class="cancel-btn" onclick="toggleForm()">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Tabla para mostrar las prendas -->
<div class="table-container">
    <c:choose>
        <c:when test="${empty prendas}">
            <div class="no-prendas">
                <h3>Tu closet está vacío</h3>
                <p>¡Comienza agregando tu primera prenda!</p>
            </div>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                <tr>
                    <th>Título</th>
                    <th>Descripción</th>
                    <th>Categoría</th>
                    <th>Talla</th>
                    <th>Color</th>
                    <th>Fecha</th>
                    <th>Acción</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="prenda" items="${prendas}">
                    <tr>
                        <td><strong>${prenda.titulo}</strong></td>
                        <td>${prenda.descripcion}</td>
                        <td>${prenda.tipo_categoria}</td>
                        <td>${prenda.talla}</td>
                        <td>${prenda.color}</td>
                        <td>
                            <fmt:formatDate value="${prenda.fechapublicacion}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td>
                            <button class="delete-btn" onclick="deletePrenda(${prenda.idprenda}, '${prenda.titulo}')">
                                Eliminar
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div style="text-align: center; padding: 15px; background-color: #f8f9fa;">
                <strong>Total de prendas: ${prendas.size()}</strong>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script>
    function toggleForm() {
        var form = document.getElementById('prendaForm');
        if (form.style.display === 'none' || form.style.display === '') {
            form.style.display = 'block';
        } else {
            form.style.display = 'none';
        }
    }

    function goHome() {
        window.location.href = "${pageContext.request.contextPath}/views/Home.jsp";
    }

    function deletePrenda(id, titulo) {
        if (confirm("¿Estás seguro de que quieres eliminar la prenda '" + titulo + "'?")) {
            // Redirigir al controlador para eliminar la prenda
            window.location.href = "${pageContext.request.contextPath}/MiClosetController?accion=eliminar&id=" + id;
        }
    }

    function logout() {
        if (confirm("¿Estás seguro de que quieres cerrar sesión?")) {
            window.location.href = "${pageContext.request.contextPath}/LoginController?action=logout";
        }
    }

    // Auto-ocultar mensajes después de 5 segundos
    setTimeout(function() {
        var messages = document.querySelectorAll('.message');
        messages.forEach(function(message) {
            message.style.display = 'none';
        });
    }, 5000);
</script>

</body>
</html>