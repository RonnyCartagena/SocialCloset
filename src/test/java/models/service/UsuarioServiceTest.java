package models.service;

import models.dao.ClosetDAO;
import models.dao.UsuarioDAO;
import models.entities.Closet;
import models.entities.Usuario;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    @Mock
    private ClosetDAO closetDAO;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        // Inicializa los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarUsuario_ValidEmail_UsuarioRegistrado() {
        // Datos de prueba
        String nombre = "Juan";
        String password = "password123";
        String email = "juan@example.com";

        // Comportamiento esperado de los mocks
        when(usuarioDAO.findByUsername(nombre)).thenReturn(null); // Nombre no tomado
        when(usuarioDAO.findByEmail(email)).thenReturn(null); // Email no tomado
        when(usuarioDAO.create(any(Usuario.class))).thenReturn(true); // Crear usuario exitoso
        when(closetDAO.create(any(Closet.class))).thenReturn(true); // Crear closet exitoso

        // Llamar al método bajo prueba
        boolean result = usuarioService.registrarUsuario(nombre, password, email);

        // Verificar el resultado
        assertTrue(result, "El usuario debería haberse registrado correctamente");

        // Verificar que las interacciones con los mocks sean las esperadas
        verify(usuarioDAO).create(any(Usuario.class));
        verify(closetDAO).create(any(Closet.class));
    }

    @Test
    public void testRegistrarUsuario_EmailInvalido() {
        // Datos de prueba con email inválido
        String nombre = "Juan";
        String password = "password123";
        String email = "juan@com";

        // Llamar al método bajo prueba
        boolean result = usuarioService.registrarUsuario(nombre, password, email);

        // Verificar que el registro falle debido al email inválido
        assertFalse(result, "El registro debería fallar debido a un email inválido");
    }

    @Test
    public void testIsValidEmail_ValidEmail() {
        // Test para un email válido
        String validEmail = "juan@example.com";
        boolean result = usuarioService.isValidEmail(validEmail);
        assertTrue(result, "El email debería ser válido");
    }

    @Test
    public void testIsValidEmail_InvalidEmail() {
        // Test para un email inválido
        String invalidEmail = "juan@com";
        boolean result = usuarioService.isValidEmail(invalidEmail);
        assertFalse(result, "El email debería ser inválido");
    }

    @Test
    public void testRegistrarUsuario_UsernameExistente() {
        // Datos de prueba con nombre de usuario ya tomado
        String nombre = "Juan";
        String password = "password123";
        String email = "juan@example.com";

        // Comportamiento esperado de los mocks
        when(usuarioDAO.findByUsername(nombre)).thenReturn(new Usuario()); // Simulamos que el usuario ya existe

        // Llamar al método bajo prueba
        boolean result = usuarioService.registrarUsuario(nombre, password, email);

        // Verificar que el registro falle debido al nombre de usuario tomado
        assertFalse(result, "El registro debería fallar debido a un nombre de usuario ya tomado");
    }

    @Test
    public void testRegistrarUsuario_EmailExistente() {
        // Datos de prueba con email ya registrado
        String nombre = "Juan";
        String password = "password123";
        String email = "juan@example.com";

        // Comportamiento esperado de los mocks
        when(usuarioDAO.findByUsername(nombre)).thenReturn(null); // Nombre no tomado
        when(usuarioDAO.findByEmail(email)).thenReturn(new Usuario()); // Simulamos que el email ya existe

        // Llamar al método bajo prueba
        boolean result = usuarioService.registrarUsuario(nombre, password, email);

        // Verificar que el registro falle debido al email ya tomado
        assertFalse(result, "El registro debería fallar debido a un email ya registrado");
    }
}