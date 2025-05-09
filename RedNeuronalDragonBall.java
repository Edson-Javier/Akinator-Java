import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import java.util.List; // Importación añadida
import java.util.Arrays; // Importación añadida

public class RedNeuronalDragonBall {
    private BasicNetwork red;
    private MLDataSet conjuntoEntrenamiento;

    public void construirYEntrenar() {
        // Cargar datos
        BaseDeDatos.cargarDatos();
        
        // Obtener datos
        List<double[]> entradas = BaseDeDatos.getEntradas();
        List<double[]> salidas = BaseDeDatos.getSalidas();
        
        // Convertir a formato Encog
        double[][] entradaArray = entradas.toArray(new double[0][0]);
        double[][] salidaArray = salidas.toArray(new double[0][0]);
        conjuntoEntrenamiento = new BasicMLDataSet(entradaArray, salidaArray);

        // Crear red neuronal
        red = new BasicNetwork();
        
        // Capa de entrada
        red.addLayer(new BasicLayer(null, true, BaseDeDatos.getNumeroCaracteristicas()));
        
        // Capa oculta
        red.addLayer(new BasicLayer(new ActivationSigmoid(), true, 10));
        
        // Capa de salida
        red.addLayer(new BasicLayer(new ActivationSigmoid(), false, BaseDeDatos.getNumeroPersonajes()));
        
        red.getStructure().finalizeStructure();
        red.reset();

        // Entrenamiento
        ResilientPropagation entrenamiento = new ResilientPropagation(red, conjuntoEntrenamiento);
        
        int epoca = 1;
        do {
            entrenamiento.iteration();
            System.out.println("Época #" + epoca + ", Error:" + entrenamiento.getError());
            epoca++;
        } while (entrenamiento.getError() > 0.01 && epoca < 1000);
        
        entrenamiento.finishTraining();
    }

    // Modificamos el método predecir para que devuelva el resultado como String
    public String predecir(double[] entrada) {
        MLData output = red.compute(new BasicMLData(entrada));
        
        int indicePersonaje = 0;
        double max = output.getData(0);
        for (int i = 1; i < output.size(); i++) {
            if (output.getData(i) > max) {
                max = output.getData(i);
                indicePersonaje = i;
            }
        }
        
        List<String> nombres = BaseDeDatos.getNombresPersonajes();
        double porcentaje = max * 100;
        
        // Formateamos el resultado con 2 decimales
        return String.format("Creo que es... ¡%s! (%.2f%% de certeza)", 
                            nombres.get(indicePersonaje), porcentaje);
    }


}