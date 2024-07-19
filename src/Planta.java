/*
 Proyecto - Simulación de una isla, automáta celular
 Calse Planta - Objeto planta
 Autor - Mario García. mariogarcia1040@gmail.com
12-Julio-2024 | 17-Julio
*/
public class Planta {
    private static int contador = 1;
    private int identificador;
    private int fila;
    private int columna;
    private int energia;
    private boolean estaVivo;

    public Planta(int fila, int columna, int energia, boolean estaVivo) {
        this.fila = fila;
        this.columna = columna;
        this.energia = energia;
        this.estaVivo = estaVivo;
        this.identificador = generadorDeIdentificador();
    }

    private synchronized int generadorDeIdentificador() {
        return contador++;
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(boolean sumaORestaDeEnergia) {
        if (sumaORestaDeEnergia) {
            this.energia++;
        } else {
            this.energia--;
        }
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

}
