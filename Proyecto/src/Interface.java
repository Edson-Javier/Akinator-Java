import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import org.encog.ml.data.basic.BasicMLData;
import java.io.File;                     // Importación añadida
import java.io.FileNotFoundException;   

public class Interface extends JFrame {
    private Map<String, JComboBox<String>> respuestas;
    private JButton analizarButton;
    private RedNeuronalDragonBall redNeuronal;

    public Interface() {
        // Inicializar la red neuronal
        redNeuronal = new RedNeuronalDragonBall();
        redNeuronal.construirYEntrenar();
        
        respuestas = new HashMap<>();
        setTitle("Akinator de Dragon Ball");
        setSize(500, 600); // Aumentado para más preguntas
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10)); // Añadido espaciado

        // Lista de características coincidente con la base de datos
        String[] caracteristicas = {
            "Es_sayayin", "Es_namekiano", "Es_androide", "Es_humano", "Es_dios",
            "vuela", "usa_ki", "tiene_cola", "Es_verde",
            "es_amigo_de_goku", "es_enemigo_de_goku", "ha_revivido", 
            "tiene_transformaciones", "es_mujer", "pertenece_al_ejercito_de_freezer"
        };

        for (String caracteristica : caracteristicas) {
            agregarPregunta(caracteristica, "¿El personaje " + caracteristica.toLowerCase().replace("_", " ") + "?");
        }

        analizarButton = new JButton("¿Quién es?");
        analizarButton.addActionListener(this::analizar);
        analizarButton.setBackground(new Color(255, 165, 0)); // Color naranja Dragon Ball
        analizarButton.setFont(new Font("Arial", Font.BOLD, 16));

        add(new JLabel("")); // Espaciado
        add(analizarButton);

        setVisible(true);
    }

    private void agregarPregunta(String clave, String pregunta) {
        JLabel label = new JLabel(pregunta);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        add(label);
        
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"No", "Sí"});
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        respuestas.put(clave, comboBox);
        add(comboBox);
    }

    private void analizar(ActionEvent e) {
        try {
            // Crear vector de entrada basado en las respuestas
            double[] entrada = new double[respuestas.size()];
            int i = 0;
            
            String[] caracteristicasOrdenadas = {
                "Es_sayayin", "Es_namekiano", "Es_androide", "Es_humano", "Es_dios",
                "vuela", "usa_ki", "tiene_cola", "Es_verde",
                "es_amigo_de_goku", "es_enemigo_de_goku", "ha_revivido", 
                "tiene_transformaciones", "es_mujer", "pertenece_al_ejercito_de_freezer"
            };
            
            for (String caracteristica : caracteristicasOrdenadas) {
                entrada[i++] = respuestas.get(caracteristica).getSelectedIndex(); // 0=No, 1=Sí
            }

            // Obtener predicción
            String prediccion = redNeuronal.predecir(entrada);
            String nombrePersonaje = prediccion.split("¡")[1].split("!")[0].trim(); // Extraer solo el nombre
            
            ImageIcon iconoDialogo = null;
            File iconoFile = new File("bin/resources/dragon.png");
            if (iconoFile.exists()) {
                iconoDialogo = new ImageIcon(iconoFile.getAbsolutePath());
            } else {
                // Intenta cargar como recurso embebido
                java.net.URL iconoUrl = getClass().getResource("/resources/dragon.png");
                if (iconoUrl != null) {
                    iconoDialogo = new ImageIcon(iconoUrl);
                } else {
                    throw new FileNotFoundException("No se encontró el icono dragon.png");
                }
            }

            // 2. Cargar imagen del personaje
            String nombreArchivo = nombrePersonaje + ".jpg";
            ImageIcon imagenPersonaje = null;
            
            // Intento 1: Desde filesystem
            File imagenFile = new File("bin/resources/predicciones/" + nombreArchivo);
            if (imagenFile.exists()) {
                imagenPersonaje = new ImageIcon(imagenFile.getAbsolutePath());
            } else {
                // Intento 2: Como recurso embebido
                java.net.URL imagenUrl = getClass().getResource("/resources/predicciones/" + nombreArchivo);
                if (imagenUrl != null) {
                    imagenPersonaje = new ImageIcon(imagenUrl);
                } else {
                    throw new FileNotFoundException("No se encontró la imagen del personaje: " + nombreArchivo);
                }
            }

            // 3. Redimensionar imagen del personaje
            Image imagenRedimensionada = imagenPersonaje.getImage()
                    .getScaledInstance(200, 400, Image.SCALE_SMOOTH);
            ImageIcon iconoPersonaje = new ImageIcon(imagenRedimensionada);

            // 4. Crear panel de diálogo
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setPreferredSize(new Dimension(450, 400));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Texto con HTML para mejor formato
            JLabel texto = new JLabel("<html><div style='text-align: center;'>" +
                    "<h2 style='color: #FF6600;'>" + prediccion + "</h2>" +
                    "<p style='font-size: 14px;'>¿He acertado?</p>" +
                    "</div></html>");
            texto.setHorizontalAlignment(SwingConstants.CENTER);

            // Imagen del personaje
            JLabel imagenLabel = new JLabel(iconoPersonaje);
            imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Organizar componentes
            panel.add(texto, BorderLayout.NORTH);
            panel.add(imagenLabel, BorderLayout.CENTER);

            // 5. Mostrar diálogo
            String[] opciones = {"¡Correcto!", "Intentar de nuevo"};
            int eleccion = JOptionPane.showOptionDialog(
                    this,
                    panel,
                    "¡Resultado!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    iconoDialogo,
                    opciones,
                    opciones[0]);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al mostrar resultados:\n" + ex.getMessage() +
                    "\nVerifica que los archivos estén en bin/resources/",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}