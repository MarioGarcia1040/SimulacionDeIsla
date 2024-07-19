/*
 Proyecto - Simulación de una isla, automáta celular
 Clase Animal - Objeto animal
 Autor - Mario García. mariogarcia1040@gmail.com
 12-Julio-2024 | 18-Julio-2024
*/
public class Animal {
    private static int contador = 1;
    private int identificador;
    private int fila;
    private int columna;
    private int energia;
    private int edad;
    private boolean estaVivo;

    public Animal(int fila, int columna, int energia, int edad, boolean estaVivo) {
        this.fila = fila;
        this.columna = columna;
        this.energia = energia;
        this.edad = edad;
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
        int movimientoFila = (int) (Math.random() * 3);

        if (movimientoFila == 0) {
            if (fila > 0) {
                this.fila--;
            }
        } else if (movimientoFila == 1) {
            if (fila < Configuracion.NUMERO_DE_FILAS - 1) {
                this.fila++;
            }
        } else {
            this.fila = fila;
        }
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
       int movimientoColumna = (int) (Math.random() * 3);

       if (movimientoColumna == 0) {
           if (columna > 0) {
               this.columna--;
           }
       } else if (movimientoColumna == 1) {
           if (columna < Configuracion.NUMERO_DE_COLUMNAS - 1) {
               this.columna++;
           }
       } else {
           this.columna = columna;
       }
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

    public int getEdad() {
        return edad;
    }

    public void setEdad() {
        this.edad++;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public boolean animalesEnMismaCelda(Animal otroAnimal) {
        return this.fila == otroAnimal.fila && this.columna == otroAnimal.columna;
    }

}
