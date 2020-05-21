package geneticalgorithmtest;

public class OperadoresGeneticos {

    private Individuo[] pareja;
    private Individuo[] hijos;
    private Individuo[] hijosMutados;
    int puntoCruce;   

    
    public OperadoresGeneticos() {
        pareja = new Individuo[2];
        hijos = new Individuo[2];
        hijosMutados = new Individuo[2];
    }

    public void crearNuevaGeneracion(Individuo[] individuos) {
        emparejar(individuos);
        mostrarIndividuos("Pareja seleccionada", pareja);
             
        Cruce cruce = new Cruce(pareja);
        hijos = cruce.cruzar();
        calcularPromedio(hijos);
        mostrarIndividuos("Hijos cruzados", hijos, cruce.getPuntoCruce());
        
        Mutacion mutacion = new Mutacion(hijos);
        hijosMutados = mutacion.mutar();
        calcularPromedio(hijosMutados);
        mostrarIndividuos("Hijos Mutados", hijosMutados, mutacion.getPuntoMutacion0(), mutacion.getPuntoMutacion1());
        
        insertarNuevosIndividuos(individuos);
    }

    public void insertarNuevosIndividuos(Individuo[] individuos) {
        int buscadorParejaAtras = 0;
        float valorPromedioBuscadorAtras = individuos[0].getPromedioHabilidades();

        int buscadorParejaActual = 1;
        float valorPromedioBuscadorActual = individuos[1].getPromedioHabilidades();

        for (int i = 2; i < individuos.length; i++) {
            if (valorPromedioBuscadorActual > individuos[i].getPromedioHabilidades()) {
                buscadorParejaActual = individuos[i].getId();
                valorPromedioBuscadorActual = individuos[i].getPromedioHabilidades();
            }

        }

        for (int i = 0; i < individuos.length; i++) {
            if (valorPromedioBuscadorAtras > individuos[i].getPromedioHabilidades()
                    && i != buscadorParejaActual) {

                buscadorParejaAtras = individuos[i].getId();
                valorPromedioBuscadorAtras = individuos[i].getPromedioHabilidades();
            }
        }
        
        
        hijosMutados[0].setId(buscadorParejaActual);
        hijosMutados[1].setId(buscadorParejaAtras);
        
        individuos[buscadorParejaActual] = hijosMutados[0];
        individuos[buscadorParejaAtras] = hijosMutados[1];
    }
    
    public void calcularPromedio(Individuo[] futbolistas){
        for (int i = 0; i < 2; i++) {
            futbolistas[i].setSumaHabilidades();
            futbolistas[i].setPromedioHabilidades();
        }
    }
    
    public void mostrarIndividuos(String tipoIndividuos, Individuo[] futbolistas,int ...puntoCorte) {
        
        System.out.println("-------------------------------- " + tipoIndividuos + " --------------------------------");
        System.out.println();
        
        this.mostrarInformacionOperacion(tipoIndividuos, puntoCorte);

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
    
    public void mostrarInformacionOperacion(String tipoIndividuos, int[] puntoCorte){

       switch(tipoIndividuos){
            case "Pareja seleccionada":
                break;
            case "Hijos cruzados":
                System.out.println("*** Informacion operacion: ***\n");
                System.out.println("Punto cruce: " +  (puntoCorte[0] + 1));
                System.out.println();
                break;
            case "Hijos Mutados":
                System.out.println("*** Informacion operacion: ***\n"); 
                System.out.println("Punto mutacion 1: " +  (puntoCorte[0] + 1));
                System.out.println("Punto mutacion 2: " +  (puntoCorte[1] + 1));
                System.out.println();
                break;
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

}