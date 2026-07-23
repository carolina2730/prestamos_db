import static spark.Spark.*;
import com.google.gson.Gson;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Main {
   static String URL = "jdbc:mysql://localhost:3307/prestamo_db?allowPublicKeyRetrieval=true&useSSL=false";
    static String USER = "root";
    static String PASS = "carolina";
    static Gson gson = new Gson();

    public static void main(String[] args) {
        port(8081);
        // Permitir CORS para que el HTML pueda conectarse
before((request, response) -> {
    response.header("Access-Control-Allow-Origin", "*");
    response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
    response.header("Access-Control-Allow-Headers", "Content-Type");
});

// Responder a las peticiones OPTIONS
options("/*", (request, response) -> {
    return "OK";
});
// GUARDAR PRESTAMO
post("/prestamos", (req, res) -> {
    res.type("application/json");
    Prestamo p = gson.fromJson(req.body(), Prestamo.class);
    
    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/prestamo_db?allowPublicKeyRetrieval=true&useSSL=false", "root", "carolina")){
        String sql = "INSERT INTO prestamos (nombre, monto, plazo_meses, estado, tasa_interes) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setDouble(2, p.getMonto());
        ps.setInt(3, p.getPlazoMeses());
        ps.setString(4, p.getEstado());
        ps.setDouble(5, p.getTasaInteres());
        ps.executeUpdate();
    }
    return gson.toJson("Guardado");
});

        // LISTAR
        get("/prestamos", (req, res) -> {
            res.type("application/json");
            List<Prestamo> lista = new ArrayList<>();
            try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/prestamo_db?allowPublicKeyRetrieval=true&useSSL=false", "root", "carolina")){
            String sql = "SELECT * FROM prestamos";
            PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    Prestamo p = new Prestamo();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setMonto(rs.getDouble("monto"));
                    p.setPlazoMeses(rs.getInt("plazo_meses"));
                    p.setTasaInteres(rs.getDouble("tasa_interes"));
                    p.setEstado(rs.getString("estado"));
                    lista.add(p);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return gson.toJson(lista);
        });

        // GUARDAR
        post("/prestamos", (req, res) -> {
            try {
                System.out.println(">>> BODY RECIBIDO: " + req.body());
                Prestamo prestamo = gson.fromJson(req.body(), Prestamo.class);
                
                Connection conn = DriverManager.getConnection(URL, USER, PASS);
                String sql = "INSERT INTO prestamos(nombre, monto, plazo_meses, tasa_interes, estado, fecha) VALUES(?,?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                stmt.setString(1, prestamo.getNombre());
                stmt.setDouble(2, prestamo.getMonto());
                stmt.setInt(3, prestamo.getPlazoMeses());
                stmt.setDouble(4, prestamo.getTasaInteres());
                stmt.setString(5, prestamo.getEstado());
                stmt.setDate(6, new Date(System.currentTimeMillis()));
                stmt.executeUpdate();
                conn.close();
                
                res.status(201);
                return gson.toJson(prestamo);
                
            } catch(Exception e) {
                System.out.println(">>> ERROR COMPLETO:");
                e.printStackTrace();
                res.status(500);
                return "{\"error\":\"" + e.getMessage() + "\"}";
            }
        });

    } // fin main
} // fin clase