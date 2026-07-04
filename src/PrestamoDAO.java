import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PrestamoDAO {
    private Connection Conexion;
    public void insertar(Prestamo p) {

        String sql = "INSERT INTO prestamo (nombre, monto, plazo_meses, tasa_interes, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNombre());
            pstmt.setDouble(2, p.getMonto());
            pstmt.setInt(3, p.getPlazoMeses());
            pstmt.setDouble(4, p.getTasaInteres());
            pstmt.setString(5, p.getEstado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar el préstamo: " + e.getMessage());
        }
    }
    
public List<Prestamo> consultarTodos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo";
        try (Connection conn = ConexionBD.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prestamo p = new Prestamo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("monto"),
                    rs.getInt("plazo_meses"),
                    rs.getDouble("tasa_interes"),
                    rs.getString("estado")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return lista;
    }
public void Actualizar(Prestamo p) {
    String sql = "UPDATE prestamo SET nombre=?, monto=?, plazo_Meses=?, tasa_Interes=?, estado=? WHERE id=?";
    try (Connection conn = ConexionBD.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, p.getNombre());
        ps.setDouble(2, p.getMonto());
        ps.setInt(3, p.getPlazoMeses());
        ps.setDouble(4, p.getTasaInteres());
        ps.setString(5, p.getEstado());
        ps.setInt(6, p.getId());
        ps.executeUpdate();
        System.out.println("Prestamo actualizado: " + p.getId());
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void crear(Prestamo p) {
String sql = "INSERT INTO prestamo (nombre, monto, plazo_meses, tasa_interes, estado) VALUES (?, ?, ?, ?, ?)";   
try (Connection conn = ConexionBD.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, p.getNombre());
        ps.setDouble(2, p.getMonto());
        ps.setInt(3, p.getPlazoMeses());
        ps.setDouble(4, p.getTasaInteres());
        ps.setString(5, p.getEstado());
        ps.executeUpdate();
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) p.setId(rs.getInt(1)); {
    
            }
        }
    } catch (SQLException e) { e.printStackTrace(); }
    
}

public List<Prestamo> listar() {
    
    List<Prestamo> lista = new ArrayList<>();
    String sql = "SELECT * FROM prestamo";
    try (Connection conn = ConexionBD.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Prestamo p = new Prestamo();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setMonto(rs.getDouble("monto"));
            p.setPlazoMeses(rs.getInt("plazo_meses"));
            p.setTasaInteres(rs.getDouble("tasa_interes"));
            p.setEstado(rs.getString("estado"));
            lista.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
    
}

public void eliminar(int id) {
    String sql = "DELETE FROM prestamo WHERE id=?";
    try (Connection conn = ConexionBD.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        int filas = ps.executeUpdate();
        System.out.println("Prestamo eliminado: id=" + id + ", filas afectadas=" + filas);
    } catch (SQLException e) {
        e.printStackTrace();
    }

}
}


