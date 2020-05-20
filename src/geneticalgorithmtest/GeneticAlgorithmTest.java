
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
    public static int numeroHijosCruce = 2;
    
    public static Individuo [] individuos;
    public static Individuo [] hijos;
    
    
    public static void main(String[] args) {
        
        individuos = new Individuo[numeroIndividuos];  
        hijos = new Individuo[numeroHijosCruce];
        OperadoresGeneticos operadores = new OperadoresGeneticos();
        
        individuos = generarPoblacionInicial();
        
        imprimirDatosIniciales(); 
        
        for (int i = 0; i < 2; i++) {
            imprimirIndividuos();
            System.out.println("--------------------------------");
            operadores.crearNuevaGeneracion(individuos);
        }
        
         
        
        
    }
    
    
    public static void imprimirDatosIniciales(){
        System.out.println("Poblacion: Futbolistas con aptitudes para ser profesionales y formar parte del equipo");
        System.out.println("Numero de individuos: " + numeroIndividuos);
        System.out.println("Función fitness: Σ habilidad");
        System.out.println("Selección: Emparejamiento + Heurística");
        System.out.println("Cruce: 1 Punto");
        System.out.println("Mutación: heuristica");
        System.out.println("Probabilidad de cruce: " + (probabilidadCruce*100) + "%");
        System.out.println("probabilidad de mutación: " + (probabilidadMutacion*100) + "%");
        System.out.println("Criterio de parada: " + numeroGeneraciones + " generaciones");
        System.out.println("\nHABILIDADES: \n");
        
        System.out.println("vl: Velocidad");
        System.out.println("tr: Tiro");
        System.out.println("rg: Regate");
        System.out.println("df: Defensa");
        System.out.println("ps: Pase");
        System.out.println("fs: Fisico");
        System.out.println("at: Ataque");
        System.out.println("");
        
    }
    
    public static void imprimirIndividuos(){
        System.out.println("\n/*** 1.- Individuos de la generación  ***/\n");
        System.out.println("");
        System.out.println("\t\tvl\t tr\t rg\t df\t ps\t fs\t at\t");
        
        for (int i = 0; i < numeroIndividuos; i++) {
            
            System.out.print("individuo " + (i+1) + ":\t");
            
            for (int j = 0; j < numeroCromosomas; j++) {
               System.out.print(individuos[i].getCromosoma(j) + "\t");   
            }
            
            System.out.println();
            System.out.print("Habilidades: \t");
            
            for (int j = 0; j < numeroCromosomas; j++) {    
                System.out.print(individuos[i].getPuntuacionHabilidades()[j] + "\t"); 
            }
            
            System.out.println("Valor promedio de habilidades: " + individuos[i].getPromedioHabilidades());
            System.out.println("\n");
        }
    }
    
    public static Individuo[] generarPoblacionInicial() {
        Individuo[] individuosGenerados = new Individuo[numeroIndividuos];
        int idIndividuo = 0;
        
        for (int i = 0; i < numeroIndividuos; i++) {
            individuosGenerados[i] = generarIndividuo(idIndividuo);
            idIndividuo++;
        }
        
        return individuosGenerados;
    }
    
    public static Individuo generarIndividuo(int id){ 
        int [] cromosomas = new int [numeroCromosomas];
        int [] habilidades = new int [numeroCromosomas];
        
        for (int i = 0; i < numeroCromosomas; i++) {    
            int valorCromosoma = valoresAleatoriosEnteros("cromosoma"); 
            cromosomas[i] = valorCromosoma;
            
            if (valorCromosoma == 1) {
                habilidades[i] = valoresAleatoriosEnteros("valorHabillidades");
            } else {
                habilidades[i] = 0;
            }  
            
        } 
        
        Individuo futbolista = new Individuo(id,cromosomas,habilidades);
        
        return futbolista;
    }
    
    public static int valoresAleatoriosEnteros(String tipoValor){
        switch(tipoValor){
            case "cromosoma":
                return (int) (Math.random() * 2);
            case "valorHabillidades":
                return (int) (Math.random() * 100) + 1;
        }
        
        return 0;
    } 
}