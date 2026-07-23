import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public void insertar(Prestamo p) {
        String sql = "INSERT INTO prestamo(nombre, monto, plazoMeses, tasaInteres, estado) VALUES(?,?,?,?,?)";
        
        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getMonto());
            stmt.setInt(3, p.getPlazoMeses());
            stmt.setDouble(4, p.getTasaInteres());
            stmt.setString(5, p.getEstado());
            
            int filas = stmt.executeUpdate();
            
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    p.setId(rs.getInt(1)); // aquí ya te va a llegar el id real de MySQL
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prestamo> listar() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo";
        
        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setMonto(rs.getDouble("monto"));
                p.setPlazoMeses(rs.getInt("plazoMeses"));
                p.setTasaInteres(rs.getDouble("tasaInteres"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Prestamo p) {

        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    public void eliminar(int id) {
    
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
}