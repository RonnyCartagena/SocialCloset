<%--
  Created by IntelliJ IDEA.
  User: cod3b
  Date: 6/21/2025
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
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
        .panel {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 300px;
            text-align: center;
        }
        .panel h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }
        .panel button {
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
        .panel button:hover {
            background-color: #0056b3;
        }
        .logout {
            background-color: #dc3545;
        }
        .logout:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="panel">
        <h2>Bienvenido a tu Home</h2>
        <button onclick="window.location.href='MiCloset.jsp'">Mi Closet</button>
        <button onclick="window.location.href='#'">Publicaciones</button>
        <button onclick="window.location.href='#'">Mensajería</button>
        <button onclick="window.location.href='#'">Mi Perfil</button>
        <button class="logout" onclick="window.location.href='LogoutController'">Salir</button>
    </div>
</div>

</body>
</html>

