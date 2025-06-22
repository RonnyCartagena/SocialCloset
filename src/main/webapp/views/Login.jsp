<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Iniciar sesión</h2>
<form action="${pageContext.request.contextPath}/LoginController" method="POST">
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>

<a href="${pageContext.request.contextPath}/views/Register.jsp">¿No tienes una cuenta? Regístrate aquí.</a>

<!-- Aquí agregarías un mensaje de error si no se puede iniciar sesión -->
<p style="color: red;">${errorMessage}</p>
</body>
</html>
