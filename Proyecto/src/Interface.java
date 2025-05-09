import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import org.encog.ml.data.basic.BasicMLData;

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
            
            // Mostrar resultado en un JOptionPane con icono personalizado
            ImageIcon icono = new ImageIcon("dragonball.png"); // Asegúrate de tener esta imagen
            JOptionPane.showMessageDialog(
                this, 
                prediccion,
                "¡Adivinación completada!", 
                JOptionPane.INFORMATION_MESSAGE,
                icono);
            // En el método analizar, después de obtener la predicción:
            String[] opciones = {"¡Correcto!", "Intentar de nuevo"};
            int eleccion = JOptionPane.showOptionDialog(
                this,
                "<html><div style='text-align: center;'><h2>" + prediccion + "</h2>" +
                "<p>¿He acertado?</p></div></html>",
                "Resultado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icono,
                opciones,
                opciones[0]);

            if (eleccion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "¡Genial! Soy un experto en Dragon Ball");
            } else {
                JOptionPane.showMessageDialog(this, "¡Vaya! Seguiré entrenando mi red neuronal");
            }
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, 
                "Ocurrió un error al analizar: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

}