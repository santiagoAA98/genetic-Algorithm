
package geneticalgorithmtest;

public class Individuo {
    private int [] cromosomas;
    private int [] puntuacionHabilidades;
    private float [] valorProbabilidadesCruce;
    private float [] valorProbabilidadesMutacion;
    private int sumaHabilidades;
    private float promedioHabilidades;

    public Individuo(int[] cromosomas,int[] puntuacionHabilidades) {
        this.cromosomas = cromosomas;
        this.puntuacionHabilidades = puntuacionHabilidades;
        this.setSumaHabilidades();
        this.setPromedioHabilidades();
    }
    
    
    public int[] getCromosomas() {
        return cromosomas;
    }

    public void setCromosomas(int[] cromosomas) {
        this.cromosomas = cromosomas;
    }

    public int[] getPuntuacionHabilidades() {
        return puntuacionHabilidades;
    }

    public void setPuntuacionHabilidades(int[] puntuacionHabilidades) {
        this.puntuacionHabilidades = puntuacionHabilidades;
    }

    public float[] getValorProbabilidadesCruce() {
        return valorProbabilidadesCruce;
    }

    //editar metodo para generar valores de vector aleatoriamente.
    public void setValorProbabilidadesCruce(float[] valorProbabilidadesCruce) {
        this.valorProbabilidadesCruce = valorProbabilidadesCruce;
    }

    public float[] getValorProbabilidadesMutacion() {
        return valorProbabilidadesMutacion;
    }
    
    //editar metodo para generar valores de vector aleatoriamente.
    public void setValorProbabilidadesMutacion(float[] valorProbabilidadesMutacion) {
        this.valorProbabilidadesMutacion = valorProbabilidadesMutacion;
    }

    public int getSumaHabilidades() {
        return sumaHabilidades;
    }

    public void setSumaHabilidades() {   
        for (int i = 0; i < puntuacionHabilidades.length ; i++) {
            this.sumaHabilidades = sumaHabilidades + puntuacionHabilidades[i];
        }
    }

    public float getPromedioHabilidades() {
        return promedioHabilidades;
    }

    public void setPromedioHabilidades() {
        this.promedioHabilidades = (this.getSumaHabilidades() / 7);
    }
    
}
