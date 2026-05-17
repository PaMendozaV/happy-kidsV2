package com.eventstock.happykids.controller;

import com.eventstock.happykids.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * ============================================================
 *  API REST — Happy Kids (versión sin base de datos)
 *
 *  Todos los datos son mock hardcodeados.
 *  Cuando conectes la BD, solo reemplazas los métodos de datos
 *  por llamadas a un Repository — el resto no cambia.
 * ============================================================
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    // ── DATOS MOCK ─────────────────────────────────────────────
    // Tomados directamente de los INSERT de tu BD happykids

    private static final List<Producto> PRODUCTOS = List.of(
        new Producto(21, "Globo R-12 Fashion Azul Rey",
            "Sempertex x50 unidades (28-30cms)",
            new BigDecimal("10000"), 10, "7703340231563",
            "css/images/AzulReyR-12.jpg", "INFLABLES"),

        new Producto(22, "Globo R-5 Pastel Mate Azul",
            "Sempertex x50 unidades (13-15cms)",
            new BigDecimal("8000"), 10, "7703340155722",
            "css/images/R5-Pastel-Mate-Azul.jpg", "INFLABLES"),

        new Producto(23, "Globo Tubito 260 Pastel Mate Azul",
            "Sempertex x20 unidades",
            new BigDecimal("10000"), 10, "7703340402079",
            "css/images/azul.jpg", "INFLABLES"),

        new Producto(24, "Globo R-12 Reflex Dorado",
            "Sempertex x50 unidades (28-30cms)",
            new BigDecimal("10000"), 10, "7703340169644",
            "css/images/DORADO.jpg", "INFLABLES")
    );

    private static final List<Map<String, Object>> METODOS_PAGO = List.of(
        Map.of("codigo", 0, "nombre", "EFECTIVO"),
        Map.of("codigo", 1, "nombre", "TARJETA"),
        Map.of("codigo", 2, "nombre", "TRANSFERENCIA"),
        Map.of("codigo", 3, "nombre", "NEQUI")
    );

    // Usuarios mock (correo → contraseña)
    private static final Map<String, String> USUARIOS = Map.of(
        "san.cvelandia@gmail.com", "3118913Niko*",   // ADMIN
        "proyectm7@gmail.com",     "12345Prueba*"    // CLIENTE
    );

    // ── ENDPOINTS ──────────────────────────────────────────────

    /** GET /api/test */
    @GetMapping("/test")
    public Map<String, Object> test() {
        return Map.of(
            "message", "Happy Kids corriendo sin base de datos",
            "time", new java.util.Date().toString()
        );
    }

    /** GET /api/productos — devuelve lista de productos */
    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return PRODUCTOS;
    }

    /** GET /api/productos/:id */
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Integer id) {
        return PRODUCTOS.stream()
            .filter(p -> p.getCodproducto().equals(id))
            .findFirst()
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(404).body(Map.of("error", "Producto no encontrado")));
    }

    /** GET /api/metodos-pago */
    @GetMapping("/metodos-pago")
    public List<Map<String, Object>> getMetodosPago() {
        return METODOS_PAGO;
    }

    /** POST /api/login — autenticación mock */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String email    = body.getOrDefault("email", "");
        String password = body.getOrDefault("password", "");

        String contrasenaCorrrecta = USUARIOS.get(email);

        if (contrasenaCorrrecta == null || !contrasenaCorrrecta.equals(password)) {
            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "error", "Correo o contraseña incorrectos"
            ));
        }

        boolean esAdmin = email.equals("san.cvelandia@gmail.com");

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("usuario_id", esAdmin ? 0 : 1);
        usuario.put("correo_usuario", email);
        usuario.put("rol_nombre", esAdmin ? "ADMIN" : "CLIENTE");
        usuario.put("nombre_cliente", esAdmin ? "Administrador" : "Cliente Demo");
        usuario.put("cliente_id", esAdmin ? null : 0);

        return ResponseEntity.ok(Map.of(
            "success", true,
            "usuario", usuario,
            "message", "Inicio de sesión exitoso"
        ));
    }

    /** POST /api/registro — registro mock (solo confirma, no guarda nada) */
    @PostMapping("/registro")
    public ResponseEntity<Map<String, Object>> registro(@RequestBody Map<String, String> body) {
        String email = body.getOrDefault("email", "");
        if (email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El correo es obligatorio"));
        }
        // En la versión sin BD simplemente devolvemos éxito
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Usuario registrado exitosamente (modo demo)"
        ));
    }

    /** POST /api/ventas — simula una venta sin guardar en BD */
    @PostMapping("/ventas")
    public ResponseEntity<Map<String, Object>> crearVenta(@RequestBody Map<String, Object> body) {
        // En modo demo simplemente devolvemos éxito
        return ResponseEntity.ok(Map.of(
            "success", true,
            "codventa", new Random().nextInt(9000) + 1000,
            "message", "Venta registrada (modo demo)"
        ));
    }
}
