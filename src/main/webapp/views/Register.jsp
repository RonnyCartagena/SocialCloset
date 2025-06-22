<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Social Trueque - Registro</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 20px 0;
    }
    .register-container {
      background: rgba(255, 255, 255, 0.95);
      border-radius: 15px;
      padding: 40px;
      box-shadow: 0 10px 30px rgba(0,0,0,0.3);
      width: 100%;
      max-width: 450px;
      text-align: center;
    }
    .logo-section {
      margin-bottom: 30px;
    }
    .logo-section h1 {
      color: #333;
      font-size: 2.5em;
      margin: 0;
      text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
    }
    .logo-section p {
      color: #666;
      margin: 10px 0 0 0;
      font-size: 1.1em;
    }
    .form-group {
      margin-bottom: 20px;
      text-align: left;
    }
    .form-group label {
      display: block;
      margin-bottom: 8px;
      color: #333;
      font-weight: bold;
    }
    .form-group input {
      width: 100%;
      padding: 12px;
      border: 2px solid #ddd;
      border-radius: 8px;
      font-size: 16px;
      box-sizing: border-box;
      transition: border-color 0.3s;
    }
    .form-group input:focus {
      outline: none;
      border-color: #28a745;
      box-shadow: 0 0 5px rgba(40,167,69,0.3);
    }
    .register-btn {
      width: 100%;
      padding: 15px;
      background-color: #28a745;
      color: white;
      border: none;
      border-radius: 8px;
      font-size: 18px;
      font-weight: bold;
      cursor: pointer;
      transition: background-color 0.3s, transform 0.2s;
      margin-bottom: 20px;
    }
    .register-btn:hover {
      background-color: #1e7e34;
      transform: translateY(-2px);
    }
    .login-link {
      display: block;
      color: #007bff;
      text-decoration: none;
      font-weight: bold;
      padding: 10px;
      border-radius: 5px;
      transition: background-color 0.3s;
    }
    .login-link:hover {
      background-color: rgba(0,123,255,0.1);
    }
    .error-message {
      background-color: #f8d7da;
      color: #721c24;
      border: 1px solid #f5c6cb;
      padding: 10px;
      border-radius: 5px;
      margin-bottom: 20px;
      display: ${empty errorMessage ? 'none' : 'block'};
    }
    .welcome-text {
      color: #666;
      margin-bottom: 30px;
      line-height: 1.5;
    }
    .password-requirements {
      font-size: 12px;
      color: #666;
      text-align: left;
      margin-top: 5px;
      padding: 8px;
      background-color: #f8f9fa;
      border-radius: 4px;
      border-left: 3px solid #007bff;
    }
  </style>
</head>
<body>
<div class="register-container">
  <div class="logo-section">
    <h1>🌟 Social Trueque</h1>
    <p>Únete a nuestra comunidad</p>
  </div>

  <div class="welcome-text">
    <p>Crea tu cuenta y comienza a intercambiar prendas con otros usuarios de manera fácil y segura.</p>
  </div>

  <!-- Mensaje de error -->
  <div class="error-message">
    ${errorMessage}
  </div>

  <form action="${pageContext.request.contextPath}/RegisterController" method="POST">
    <div class="form-group">
      <label for="username">👤 Nombre de Usuario:</label>
      <input type="text" id="username" name="username" placeholder="Tu nombre de usuario" required>
    </div>

    <div class="form-group">
      <label for="email">📧 Correo Electrónico:</label>
      <input type="email" id="email" name="email" placeholder="tu@email.com" required>
    </div>

    <div class="form-group">
      <label for="password">🔒 Contraseña:</label>
      <input type="password" id="password" name="password" placeholder="Crea una contraseña segura" required>
      <div class="password-requirements">
        💡 Tip: Usa al menos 6 caracteres con letras y números
      </div>
    </div>

    <div class="form-group">
      <label for="confirm_password">🔒 Confirmar Contraseña:</label>
      <input type="password" id="confirm_password" name="confirm_password" placeholder="Repite tu contraseña" required>
    </div>

    <button type="submit" class="register-btn">Crear Cuenta</button>
  </form>

  <a href="${pageContext.request.contextPath}/views/Login.jsp" class="login-link">
    ¿Ya tienes una cuenta? Inicia sesión aquí 👈
  </a>
</div>

<script>
  // Validación de contraseñas en tiempo real
  document.getElementById('confirm_password').addEventListener('input', function() {
    var password = document.getElementById('password').value;
    var confirmPassword = this.value;

    if (password !== confirmPassword && confirmPassword.length > 0) {
      this.style.borderColor = '#dc3545';
      this.style.boxShadow = '0 0 5px rgba(220,53,69,0.3)';
    } else {
      this.style.borderColor = '#28a745';
      this.style.boxShadow = '0 0 5px rgba(40,167,69,0.3)';
    }
  });

  // Auto-ocultar mensaje de error después de 5 segundos
  setTimeout(function() {
    var errorMsg = document.querySelector('.error-message');
    if (errorMsg && errorMsg.innerHTML.trim()) {
      errorMsg.style.display = 'none';
    }
  }, 5000);

  // Validación del formulario antes del envío
  document.querySelector('form').addEventListener('submit', function(e) {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirm_password').value;

    if (password !== confirmPassword) {
      e.preventDefault();
      alert('Las contraseñas no coinciden. Por favor, verifica e inténtalo de nuevo.');
      document.getElementById('confirm_password').focus();
    }
  });
</script>
</body>
</html>