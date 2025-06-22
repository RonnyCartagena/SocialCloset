package models.service;

import models.dao.UsuarioDAO;
import models.dao.ClosetDAO;
import models.entities.Usuario;
import models.entities.Closet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;
    private ClosetDAO closetDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        this.closetDAO = new ClosetDAO();
    }

    public boolean registrarUsuario(String nombre, String password, String email) {
        // Validación del formato del correo electrónico
        if (!isValidEmail(email)) {
            System.out.println("Error: Email inválido - " + email);
            return false;
        }

        // Verificar si el nombre de usuario ya está tomado
        if (isUsernameTaken(nombre)) {
            System.out.println("Error: Nombre de usuario ya existe - " + nombre);
            return false;
        }

        // Verificar si el correo electrónico ya está tomado
        if (isEmailTaken(email)) {
            System.out.println("Error: Email ya existe - " + email);
            return false;
        }

        // Validar que el nombre de usuario no sea nulo ni vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: Nombre de usuario vacío o nulo");
            return false;
        }

        try {
            // ✅ ESTRATEGIA SIMPLIFICADA: Crear usuario primero, luego closet

            // 1. Crear y guardar el Usuario SIN closet
            Usuario newUser = new Usuario(nombre, password, email);

            System.out.println("Creando usuario:");
            System.out.println("- Nombre Usuario: " + newUser.getNombre());
            System.out.println("- Email: " + newUser.getEmail());
            System.out.println("- Password: " + (newUser.getPassword() != null ? "***" : "null"));
            System.out.println("- Fecha Registro: " + newUser.getFechaRegistro());

            // 2. Guardar el Usuario primero
            boolean userCreated = usuarioDAO.create(newUser);

            if (!userCreated) {
                System.out.println("Error: No se pudo crear el usuario");
                return false;
            }

            System.out.println("Usuario creado exitosamente con ID: " + newUser.getIdUsuario());

            // 3. Crear y guardar el Closet con referencia al Usuario ya persistido
            Closet newCloset = new Closet(newUser);
            boolean closetCreated = closetDAO.create(newCloset);

            if (!closetCreated) {
                System.out.println("Warning: Usuario creado pero no se pudo crear el Closet");
                // El usuario ya fue creado exitosamente, así que no es un error crítico
                return true; // Aún consideramos exitoso el registro del usuario
            } else {
                System.out.println("Closet creado exitosamente con ID: " + newCloset.getIdCloset());

                // ✅ OPCIONAL: Actualizar la referencia bidireccional
                // Esto no es necesario para la funcionalidad, pero mantiene la consistencia
                newUser.setCloset(newCloset);
                usuarioDAO.update(newUser);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isUsernameTaken(String nombre) {
        try {
            return usuarioDAO.findByUsername(nombre) != null;
        } catch (Exception e) {
            System.out.println("Error verificando username: " + e.getMessage());
            return true;
        }
    }

    private boolean isEmailTaken(String email) {
        try {
            return usuarioDAO.findByEmail(email) != null;
        } catch (Exception e) {
            System.out.println("Error verificando email: " + e.getMessage());
            return true;
        }
    }

    public boolean validarUsuarioPorEmail(String email, String password) {
        Usuario usuario = usuarioDAO.findByEmail(email);  // Buscar por correo electrónico
        if (usuario != null) {
            // Suponiendo que la contraseña está en texto claro o has hecho la comparación de contraseñas de manera segura
            return usuario.getPassword().equals(password);
        }
        return false;
    }
}