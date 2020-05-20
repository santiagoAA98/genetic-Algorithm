package geneticalgorithmtest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class OperadoresGeneticos {

    private Individuo[] pareja;
    private Individuo[] hijos;
    float[] proCruce1;
    float[] proCruce2;
    float[] proMutacion1;
    float[] proMutacion2;

    public OperadoresGeneticos() {
        pareja = new Individuo[2];
        hijos = new Individuo[2];
        proCruce1 = new float[GeneticAlgorithmTest.numeroCromosomas];
        proCruce2 = new float[GeneticAlgorithmTest.numeroCromosomas];
    }

    public void crearNuevaGeneracion(Individuo[] individuos) {
        emparejar(individuos);
        cruzar();
    }

    public void emparejar(Individuo[] individuos) {
        int buscadorParejaAtras = 0;
        float valorPromedioBuscadorAtras = individuos[0].getPromedioHabilidades();

        int buscadorParejaActual = 1;
        float valorPromedioBuscadorActual = individuos[1].getPromedioHabilidades();

        for (int i = 2; i < individuos.length; i++) {
            if (valorPromedioBuscadorActual < individuos[i].getPromedioHabilidades()) {
                buscadorParejaActual = individuos[i].getId();
                valorPromedioBuscadorActual = individuos[i].getPromedioHabilidades();
            }
                     
        }
               
        for (int i = 0; i < individuos.length; i++) {
            if ( valorPromedioBuscadorAtras < individuos[i].getPromedioHabilidades() && 
                  i !=  buscadorParejaActual ) {
                
                buscadorParejaAtras = individuos[i].getId();
                valorPromedioBuscadorAtras = individuos[i].getPromedioHabilidades();
            } 
        }

        
        pareja[0] =  new Individuo(individuos[buscadorParejaActual].getId(), 
                                    individuos[buscadorParejaActual].getCromosomas().clone(),
                                    individuos[buscadorParejaActual].getPuntuacionHabilidades().clone());
        
        
        pareja[1] =  new Individuo(individuos[buscadorParejaAtras].getId(), 
                                    individuos[buscadorParejaAtras].getCromosomas().clone(),
                                    individuos[buscadorParejaAtras].getPuntuacionHabilidades().clone());
          

        /*se cambiaran los cromosomas cuando se crucen y muten*/
        hijos[0] =  new Individuo(individuos[buscadorParejaActual].getId(), 
                                    individuos[buscadorParejaActual].getCromosomas().clone(),
                                    individuos[buscadorParejaActual].getPuntuacionHabilidades().clone());
        
        
        hijos[1] =  new Individuo(individuos[buscadorParejaAtras].getId(), 
                                    individuos[buscadorParejaAtras].getCromosomas().clone(),
                                    individuos[buscadorParejaAtras].getPuntuacionHabilidades().clone());
        

    }

    public void cruzar() {
        
        generarProbabiliadesCruce();
        int puntoCruce = buscarPuntoCruce();
        
        System.out.println("----------------punto cruce----------------");
        System.out.println(puntoCruce);
        
        for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {
            if ( puntoCruce < i ) {
                hijos[0].setCromosoma(i, pareja[1].getCromosomas(i));
                hijos[1].setCromosoma(i, pareja[0].getCromosomas(i));
                System.out.println("entro " + pareja[1].getCromosomas(i) +  "--" + pareja[0].getCromosomas(i));
            }
        }
        
        for (int i = 0; i < 2; i++) {
            
            System.out.print("individuo " + (i+1) + ":\t");
            
            for (int j = 0; j < GeneticAlgorithmTest.numeroCromosomas; j++) {
               System.out.print(hijos[i].getCromosomas(j) + "\t");   
            }
            
            System.out.println();
            System.out.print("Habilidades: \t");
            
            for (int j = 0; j < GeneticAlgorithmTest.numeroCromosomas; j++) {    
                System.out.print(hijos[i].getPuntuacionHabilidades()[j] + "\t"); 
            }
            
            System.out.println("Valor promedio de habilidades: " + hijos[i].getPromedioHabilidades());
            System.out.println("\n");
        }
        
        
    }
    
    public void generarProbabiliadesCruce(){
      for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {
            proCruce1[i] = valoresAleatoriosReales();
            proCruce2[i] = valoresAleatoriosReales();
        }
        
        pareja[0].setValorProbabilidadesCruce(proCruce1);
        pareja[1].setValorProbabilidadesCruce(proCruce2); 
   }
    
    public int buscarPuntoCruce() {
       int posicionPuntoCruce = 0;
       int numeroHijo = 0;
       float distanciaMasCortaPuntoCruce = Math.abs(GeneticAlgorithmTest.probabilidadCruce - 
                                                     pareja[0].getValorProbabilidadesCruce()[0]);
       
       
       for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {
           
           float comparador0 = Math.abs(GeneticAlgorithmTest.probabilidadCruce - 
                                           pareja[0].getValorProbabilidadesCruce()[i]);
           
           float comparador1 = Math.abs(GeneticAlgorithmTest.probabilidadCruce - 
                                            pareja[1].getValorProbabilidadesCruce()[i]);
                 
           if ( comparador0 < comparador1){
               
               if (comparador0 < distanciaMasCortaPuntoCruce) {
                    posicionPuntoCruce = i;
                    numeroHijo = 0;
                    distanciaMasCortaPuntoCruce = comparador0;
               }
           } else {
               if (comparador0 < distanciaMasCortaPuntoCruce) {
                    posicionPuntoCruce = i;
                    numeroHijo = 1;
                    distanciaMasCortaPuntoCruce = comparador1;
               }
           }
       }
       
       return posicionPuntoCruce;
   }

    public void mutar() {

    }
    
   
    public float valoresAleatoriosReales() {
        BigDecimal bd = new BigDecimal((Math.random() * 1)).setScale(2, RoundingMode.HALF_UP);
        return(float)bd.doubleValue();      
    }

}
