
package geneticalgorithmtest;

/**
 *
 * @author Santiago Arenas
 */
public class GeneticAlgorithmTest {
    
    public static float probabilidadSelecccion = 0.6f;
    public static float probabilidadCruce = 0.7f;
    public static float probabilidadMutacion = 0.8f;
    public static int numeroGeneraciones = 100;
    public static int numeroIndividuos = 11;
    public static int numeroCromosomas = 7;
    
    
    
    public static void main(String[] args) {
        System.out.println("Poblacion: Futbolistas con aptitudes para ser profesionales y formar parte del equipo");
        System.out.println("Numero de individuos: " + numeroIndividuos);
        System.out.println("Función fitness: Σ habilidad");
        System.out.println("Selección: Emparejamiento + Heurística");
        System.out.println("Cruce: 1 Punto");
        System.out.println("Mutación: heuristica");
        System.out.println("Probabilidad de cruce: " + (probabilidadCruce*100) + "%");
        System.out.println("probabilidad de mutación: " + (probabilidadMutacion*100) + "%");
        System.out.println("Criterio de parada: " + numeroGeneraciones + " generaciones");
        System.out.println("");  
    }
    
    
    
    
    
}
