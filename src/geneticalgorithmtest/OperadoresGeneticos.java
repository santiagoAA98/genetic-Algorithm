package geneticalgorithmtest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class OperadoresGeneticos {

    private Individuo[] pareja;
    private Individuo[] hijos;
    private Individuo[] hijosMutados;
    float[] proCruce1;
    float[] proCruce2;
    float[] proMutacion1;
    float[] proMutacion2;

    public OperadoresGeneticos() {
        pareja = new Individuo[2];
        hijos = new Individuo[2];
        hijosMutados = new Individuo[2];
        proCruce1 = new float[GeneticAlgorithmTest.numeroCromosomas];
        proCruce2 = new float[GeneticAlgorithmTest.numeroCromosomas];
        proMutacion1 = new float[GeneticAlgorithmTest.numeroCromosomas];
        proMutacion2 = new float[GeneticAlgorithmTest.numeroCromosomas];
    }

    public void crearNuevaGeneracion(Individuo[] individuos) {
        emparejar(individuos);
        mostrarIndividuos("Pareja seleccionada", pareja);
        
        cruzar(pareja);
        calcularPromedio(hijos);
        mostrarIndividuos("Hijos cruzados", hijos);
        
        mutar(hijos);
        calcularPromedio(hijosMutados);
        mostrarIndividuos("Hijos Mutados", hijosMutados);
    }

    public void calcularPromedio(Individuo[] futbolistas){
        for (int i = 0; i < 2; i++) {
            futbolistas[i].setSumaHabilidades();
            futbolistas[i].setPromedioHabilidades();
        }
    }
    
    public void mostrarIndividuos(String tipoIndividuos, Individuo[] futbolistas) {
        
        System.out.println("----------------" + tipoIndividuos + "----------------");
        System.out.println();

        for (int i = 0; i < 2; i++) {

            System.out.print("individuo " + (i + 1) + ":\t");

            for (int j = 0; j < GeneticAlgorithmTest.numeroCromosomas; j++) {
                System.out.print(futbolistas[i].getCromosoma(j) + "\t");
            }

            System.out.println();
            System.out.print("Habilidades: \t");

            for (int j = 0; j < GeneticAlgorithmTest.numeroCromosomas; j++) {
                System.out.print(futbolistas[i].getPuntuacionHabilidades()[j] + "\t");
            }

            System.out.println("Valor promedio de habilidades: " + futbolistas[i].getPromedioHabilidades());
            System.out.println("\n");
        }
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
            if (valorPromedioBuscadorAtras < individuos[i].getPromedioHabilidades()
                    && i != buscadorParejaActual) {

                buscadorParejaAtras = individuos[i].getId();
                valorPromedioBuscadorAtras = individuos[i].getPromedioHabilidades();
            }
        }

        pareja[0] = new Individuo(individuos[buscadorParejaActual].getId(),
                individuos[buscadorParejaActual].getCromosomas().clone(),
                individuos[buscadorParejaActual].getPuntuacionHabilidades().clone());

        pareja[1] = new Individuo(individuos[buscadorParejaAtras].getId(),
                individuos[buscadorParejaAtras].getCromosomas().clone(),
                individuos[buscadorParejaAtras].getPuntuacionHabilidades().clone());

    }

    public void cruzar(Individuo[] parejas) {

        hijos[0] = new Individuo(parejas[0].getId(),
                parejas[0].getCromosomas().clone(),
                parejas[0].getPuntuacionHabilidades().clone());

        hijos[1] = new Individuo(parejas[1].getId(),
                parejas[1].getCromosomas().clone(),
                parejas[1].getPuntuacionHabilidades().clone());

        generarProbabiliadesCruce();
        int puntoCruce = buscarPuntoCruce();

        for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {
            if (puntoCruce < i) {
                hijos[0].setCromosoma(i, pareja[1].getCromosoma(i));
                hijos[0].setPuntuaionHabilidad(i, pareja[1].getPuntuacionHabilidad(i));

                hijos[1].setCromosoma(i, pareja[0].getCromosoma(i));
                hijos[1].setPuntuaionHabilidad(i, pareja[0].getPuntuacionHabilidad(i));
            }
        }

    }

    public void generarProbabiliadesCruce() {
        for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {
            proCruce1[i] = valoresAleatoriosReales();
            proCruce2[i] = valoresAleatoriosReales();
        }

        pareja[0].setValorProbabilidadesCruce(proCruce1);
        pareja[1].setValorProbabilidadesCruce(proCruce2);
    }

    public int buscarPuntoCruce() {
        int posicionPuntoCruce = 0;
        float distanciaMasCortaPuntoCruce = Math.abs(GeneticAlgorithmTest.probabilidadCruce
                - pareja[0].getValorProbabilidadesCruce()[0]);

        for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {

            float comparador0 = Math.abs(GeneticAlgorithmTest.probabilidadCruce
                    - pareja[0].getValorProbabilidadesCruce()[i]);

            float comparador1 = Math.abs(GeneticAlgorithmTest.probabilidadCruce
                    - pareja[1].getValorProbabilidadesCruce()[i]);

            if (comparador0 < comparador1) {

                if (comparador0 < distanciaMasCortaPuntoCruce) {
                    posicionPuntoCruce = i;
                    distanciaMasCortaPuntoCruce = comparador0;
                }
            } else {
                if (comparador0 < distanciaMasCortaPuntoCruce) {
                    posicionPuntoCruce = i;
                    distanciaMasCortaPuntoCruce = comparador1;
                }
            }
        }

        return posicionPuntoCruce;
    }

    public void mutar(Individuo[] hijosCruzados) {
        hijosMutados[0] = new Individuo(hijosCruzados[0].getId(),
                hijosCruzados[0].getCromosomas().clone(),
                hijosCruzados[0].getPuntuacionHabilidades().clone());

        hijosMutados[1] = new Individuo(hijosCruzados[1].getId(),
                hijosCruzados[1].getCromosomas().clone(),
                hijosCruzados[1].getPuntuacionHabilidades().clone());

        generarProbabilidadMutacion();
        int[] puntoMutacion = buscarPuntosMutacion();

        boolean posicionEncontrada0 = false;
        boolean posicionEncontrada1 = false;

        for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {

            if (puntoMutacion[0] == i && !posicionEncontrada0) {
                if (hijos[0].getCromosoma(i) == 0) {
                    hijosMutados[0].setCromosoma(i, hijos[1].getCromosoma(i));
                    hijosMutados[0].setPuntuaionHabilidad(i, GeneticAlgorithmTest.valoresAleatoriosEnteros("valorHabillidades"));
                    posicionEncontrada0 = true;
                }
            }

            if (puntoMutacion[1] == i && !posicionEncontrada1) {
                if (hijos[1].getCromosoma(i) == 0) {
                    hijosMutados[1].setCromosoma(i, hijos[1].getCromosoma(i));
                    hijosMutados[1].setPuntuaionHabilidad(i, GeneticAlgorithmTest.valoresAleatoriosEnteros("valorHabillidades"));
                    posicionEncontrada1 = true;
                }
            }

            if (posicionEncontrada0 && posicionEncontrada1) {
                break;
            }

        }
        
    }

    public void generarProbabilidadMutacion() {
        for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {
            proMutacion1[i] = valoresAleatoriosReales();
            proMutacion2[i] = valoresAleatoriosReales();
        }

        hijos[0].setValorProbabilidadesMutacion(proMutacion1);
        hijos[1].setValorProbabilidadesMutacion(proMutacion2);
    }

    public int[] buscarPuntosMutacion() {
        int[] posicionesPuntoMutacion = new int[2];

        float distanciaMasCortaPuntoMutacion0 = Math.abs(GeneticAlgorithmTest.probabilidadMutacion   
                - hijos[0].getValorProbabilidadesMutacion()[0]);

        float distanciaMasCortaPuntoMutacion1 = Math.abs(GeneticAlgorithmTest.probabilidadMutacion
                - hijos[1].getValorProbabilidadesMutacion()[0]);

        for (int i = 0; i < GeneticAlgorithmTest.numeroCromosomas; i++) {

            float comparador0 = Math.abs(GeneticAlgorithmTest.probabilidadMutacion
                    - hijos[0].getValorProbabilidadesMutacion()[i]);

            float comparador1 = Math.abs(GeneticAlgorithmTest.probabilidadMutacion
                    - hijos[1].getValorProbabilidadesMutacion()[i]);

            if (comparador0 < distanciaMasCortaPuntoMutacion0) {
                posicionesPuntoMutacion[0] = i;
                distanciaMasCortaPuntoMutacion0 = comparador0;
            }

            if (comparador1 < distanciaMasCortaPuntoMutacion1) {
                posicionesPuntoMutacion[1] = i;
                distanciaMasCortaPuntoMutacion1 = comparador1;
            }

        }

        return posicionesPuntoMutacion;
    }

    public float valoresAleatoriosReales() {
        BigDecimal bd = new BigDecimal((Math.random() * 1)).setScale(2, RoundingMode.HALF_UP);
        return (float) bd.doubleValue();
    }

}

/*

System.out.println("----------------punto cruce----------------");
        System.out.println(puntoCruce);

for (int i = 0; i < 2; i++) {
            
            System.out.print("individuo " + (i+1) + ":\t");
            
            for (int j = 0; j < GeneticAlgorithmTest.numeroCromosomas; j++) {
               System.out.print(hijos[i].getCromosoma(j) + "\t");   
            }
            
            System.out.println();
            System.out.print("Habilidades: \t");
            
            for (int j = 0; j < GeneticAlgorithmTest.numeroCromosomas; j++) {    
                System.out.print(hijos[i].getPuntuacionHabilidades()[j] + "\t"); 
            }
            
            System.out.println("Valor promedio de habilidades: " + hijos[i].getPromedioHabilidades());
            System.out.println("\n");
        }

 */
