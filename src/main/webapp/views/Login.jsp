<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Social Trueque - Iniciar Sesión</title>
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
        }
        .login-container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            width: 100%;
            max-width: 400px;
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
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0,123,255,0.3);
        }
        .login-btn {
            width: 100%;
            padding: 15px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
            margin-bottom: 20px;
        }
        .login-btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }
        .register-link {
            display: block;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .register-link:hover {
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
    </style>
</head>
<body>
<div class="login-container">
    <div class="logo-section">
        <h1>🌟 Social Trueque</h1>
        <p>Tu plataforma de intercambio de ropa</p>
    </div>

    <div class="welcome-text">
        <p>Ingresa a tu cuenta para continuar explorando y intercambiando prendas increíbles.</p>
    </div>

    <!-- Mensaje de error -->
    <div class="error-message">
        ${errorMessage}
    </div>

    <form action="${pageContext.request.contextPath}/LoginController" method="POST">
        <div class="form-group">
            <label for="email">📧 Correo Electrónico:</label>
            <input type="email" id="email" name="email" placeholder="tu@email.com" required>
        </div>

        <div class="form-group">
            <label for="password">🔒 Contraseña:</label>
            <input type="password" id="password" name="password" placeholder="Tu contraseña" required>
        </div>

        <button type="submit" class="login-btn">Iniciar Sesión</button>
    </form>

    <a href="${pageContext.request.contextPath}/views/Register.jsp" class="register-link">
        ¿No tienes una cuenta? Regístrate aquí 👈
    </a>
</div>

<script>
    // Auto-ocultar mensaje de error después de 5 segundos
    setTimeout(function() {
        var errorMsg = document.querySelector('.error-message');
        if (errorMsg && errorMsg.innerHTML.trim()) {
            errorMsg.style.display = 'none';
        }
    }, 5000);
</script>
</body>
</html>