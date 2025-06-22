<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registro</title>
</head>
<body>
<h2>Registrarse</h2>
<form action="${pageContext.request.contextPath}/RegisterController" method="POST">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br><br>

  <label for="confirm_password">Confirmar Password:</label>
  <input type="password" id="confirm_password" name="confirm_password" required><br><br>

  <label for="email">Email:</label>
  <input type="email" id="email" name="email" required><br><br>

  <button type="submit">Registrar</button>
</form>

<a href="${pageContext.request.contextPath}/views/Login.jsp">¿Ya tienes una cuenta? Inicia sesión aquí.</a>

<!-- Aquí agregarías un mensaje de error si el registro falla -->
<p style="color: red;">${errorMessage}</p>
</body>
</html>
