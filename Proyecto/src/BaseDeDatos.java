// src/BaseDeDatos.java
import java.sql.*;
import java.util.*;

public class BaseDeDatos {
    private static final String[] CARACTERISTICAS = {
        "Es_sayayin", "Es_namekiano", "Es_androide", "Es_humano", "Es_dios",
        "vuela", "usa_ki", "tiene_cola", "Es_verde",
        "es_amigo_de_goku", "es_enemigo_de_goku", "ha_revivido", 
        "tiene_transformaciones", "es_mujer", "pertenece_al_ejercito_de_freezer"
    };
    
    private static final List<double[]> entradas = new ArrayList<>();
    private static final List<String> nombresPersonajes = new ArrayList<>();
    private static final Map<String, double[]> salidasCodificadas = new HashMap<>();

    public static void cargarDatos() {
        String query = "SELECT nombre, " + String.join(", ", CARACTERISTICAS) + 
                      " FROM Personajes_entrenamiento";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Primero cargar todos los nombres
            while (rs.next()) {
                nombresPersonajes.add(rs.getString("nombre"));
            }

            // Codificación one-hot para las salidas
            int numPersonajes = nombresPersonajes.size();
            for (int i = 0; i < numPersonajes; i++) {
                double[] codificacion = new double[numPersonajes];
                codificacion[i] = 1.0;
                salidasCodificadas.put(nombresPersonajes.get(i), codificacion);
            }

            // Volver a ejecutar la consulta para cargar entradas
            try (ResultSet rs2 = stmt.executeQuery(query)) {
                while (rs2.next()) {
                    double[] input = new double[CARACTERISTICAS.length];
                    for (int i = 0; i < CARACTERISTICAS.length; i++) {
                        input[i] = rs2.getBoolean(CARACTERISTICAS[i]) ? 1.0 : 0.0;
                    }
                    entradas.add(input);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<double[]> getEntradas() {
        return new ArrayList<>(entradas);
    }

    public static List<double[]> getSalidas() {
        List<double[]> salidas = new ArrayList<>();
        for (String nombre : nombresPersonajes) {
            salidas.add(salidasCodificadas.get(nombre));
        }
        return salidas;
    }

    public static List<String> getNombresPersonajes() {
        return new ArrayList<>(nombresPersonajes);
    }

    public static int getNumeroCaracteristicas() {
        return CARACTERISTICAS.length;
    }

    public static int getNumeroPersonajes() {
        return nombresPersonajes.size();
    }
}