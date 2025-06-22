<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Social Trueque - Inicio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .header {
            background-color: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            padding: 20px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .user-info {
            display: flex;
            align-items: center;
            gap: 15px;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 8px 16px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 40px 20px;
        }
        .welcome-section {
            text-align: center;
            color: white;
            margin-bottom: 50px;
        }
        .welcome-section h1 {
            font-size: 3em;
            margin-bottom: 10px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        .welcome-section p {
            font-size: 1.2em;
            opacity: 0.9;
        }
        .actions-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 30px;
            margin-top: 40px;
        }
        .action-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 30px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .action-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
        }
        .action-card h3 {
            color: #333;
            margin-bottom: 15px;
            font-size: 1.5em;
        }
        .action-card p {
            color: #666;
            line-height: 1.6;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-block;
            padding: 12px 25px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s;
            font-weight: bold;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            background-color: #28a745;
        }
        .btn-secondary:hover {
            background-color: #1e7e34;
        }
        .stats {
            display: flex;
            justify-content: center;
            gap: 40px;
            margin: 40px 0;
            flex-wrap: wrap;
        }
        .stat-item {
            background: rgba(255, 255, 255, 0.2);
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            color: white;
            min-width: 150px;
        }
        .stat-number {
            font-size: 2em;
            font-weight: bold;
            display: block;
        }
    </style>
</head>
<body>

<div class="header">
    <div>
        <h2 style="margin: 0;">🌟 Social Trueque</h2>
    </div>
    <div class="user-info">
        <span>👋 Hola, <strong>${sessionScope.nombreUsuario}</strong></span>
        <button class="logout-btn" onclick="logout()">Cerrar Sesión</button>
    </div>
</div>

<div class="container">
    <div class="welcome-section">
        <h1>¡Bienvenido a tu Espacio Personal!</h1>
        <p>Gestiona tu closet, descubre nuevas prendas y conecta con otros usuarios</p>
    </div>

    <div class="stats">
        <div class="stat-item">
            <span class="stat-number">👕</span>
            <span>Mi Closet</span>
        </div>
        <div class="stat-item">
            <span class="stat-number">🔄</span>
            <span>Intercambios</span>
        </div>
        <div class="stat-item">
            <span class="stat-number">👥</span>
            <span>Comunidad</span>
        </div>
    </div>

    <div class="actions-grid">
        <div class="action-card">
            <h3>🏠 Mi Closet Personal</h3>
            <p>Administra todas tus prendas, agrega nuevas piezas y organiza tu guardarropa digital. Mantén un registro completo de tu ropa.</p>
            <a href="${pageContext.request.contextPath}/MiClosetController" class="btn">Ver Mi Closet</a>
        </div>

        <div class="action-card">
            <h3>🔍 Explorar Prendas</h3>
            <p>Descubre prendas increíbles de otros usuarios. Encuentra exactamente lo que buscas y prepárate para hacer intercambios.</p>
            <a href="${pageContext.request.contextPath}/ExplorarController" class="btn btn-secondary">Explorar</a>
        </div>

        <div class="action-card">
            <h3>💱 Mis Intercambios</h3>
            <p>Revisa tus intercambios pendientes, historial de trueques y propuestas recibidas. Gestiona toda tu actividad de intercambio.</p>
            <a href="${pageContext.request.contextPath}/IntercambiosController" class="btn">Ver Intercambios</a>
        </div>

        <div class="action-card">
            <h3>👤 Mi Perfil</h3>
            <p>Actualiza tu información personal, configura tus preferencias y personaliza tu experiencia en la plataforma.</p>
            <a href="${pageContext.request.contextPath}/PerfilController" class="btn btn-secondary">Editar Perfil</a>
        </div>
    </div>
</div>

<script>
    function logout() {
        if (confirm("¿Estás seguro de que quieres cerrar sesión?")) {
            window.location.href = "${pageContext.request.contextPath}/LoginController?action=logout";
        }
    }

    // Verificar si hay sesión válida
    <c:if test="${empty sessionScope.idUsuario}">
    window.location.href = "${pageContext.request.contextPath}/views/Login.jsp";
    </c:if>
</script>

</body>
</html>