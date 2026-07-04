import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrestamoDAO dao = new PrestamoDAO();

        // 1. LIMPIAR TABLA SOLO LA PRIMERA VEZ PARA PROBAR
        // dao.limpiarTabla(); // Descomenta esta línea 1 sola vez, corre, y vuelve a comentarla

        // 2. CREATE - Solo 3 registros de prueba con datos reales
        System.out.println("--- 1. CREATE ---");
        dao.crear(new Prestamo(0, "Ana Lopez", 1600000.0, 12, 0.15, "Activo")); // 1.6M, 12 meses, 15%
        dao.crear(new Prestamo(0, "Laura Gomez", 500000.0, 6, 0.12, "Activo"));  // 500K, 6 meses, 12%
        dao.crear(new Prestamo(0, "Carlos Ruiz", 2500000.0, 24, 0.18, "Activo"));// 2.5M, 24 meses, 18%

        // 3. READ
        System.out.println("\n--- 2. READ ---");
        List<Prestamo> lista = dao.listar();
        lista.forEach(System.out::println);

        // 4. UPDATE - Cambiar el id=1 a Pagado
        System.out.println("\n--- 3. UPDATE ---");
        Prestamo p1 = lista.get(0); // Toma el primero
        p1.setEstado("Pagado");
        p1.setTasaInteres(0.0); // Ya pagó, tasa a 0
        dao.Actualizar(p1);
        System.out.println("Prestamo actualizado: " + p1);

        // 5. READ despues de UPDATE
        System.out.println("\n--- 4. READ despues de UPDATE ---");
        dao.listar().forEach(System.out::println);

        // 6. DELETE - Borra el último id
        System.out.println("\n--- 5. DELETE ---");
        List<Prestamo> listaFinal = dao.listar();
        int idABorrar = listaFinal.get(listaFinal.size() - 1).getId(); // Borra el último
        dao.eliminar(idABorrar);
        System.out.println("Prestamo eliminado: id=" + idABorrar);

        // 7. READ final
        System.out.println("\n--- 6. READ final ---");
        dao.listar().forEach(System.out::println);
    }
}