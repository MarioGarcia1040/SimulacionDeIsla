import java.util.ArrayList;
/*
 Proyecto - Simulación de una isla, automáta celular
 Clase Isla - Crea la isla en una matriz predefinida y carga las celdas con los habitantes, se imprime la isla con los
 habitantes y su ubicación en cada celda.
 Autor - Mario García. mariogarcia1040@gmail.com
 2-Julio-2024 | 18-Julio-2024
*/
public class Isla {

    private int[][] celdaDelTablero;
    public ArrayList<Planta> plantas = new ArrayList<>();
    public ArrayList<Animal> animales = new ArrayList<>();

    public void generaTablero() {
        celdaDelTablero = new int[Configuracion.NUMERO_DE_FILAS][Configuracion.NUMERO_DE_COLUMNAS];
        // Generar tablero sin seres vivos
        for (int i = 0; i < Configuracion.NUMERO_DE_FILAS; i++) {
            for (int j = 0; j < Configuracion.NUMERO_DE_COLUMNAS; j++) {
                celdaDelTablero[i][j] = 0;
            }
        }
        agregarSeresVivosInicialesATablero(Configuracion.ANIMALES_INICIALES, 2);
        agregarSeresVivosInicialesATablero(Configuracion.PLANTAS_INICIALES, 1);
    }

    // Se agregan los seres vivos, con el número determinado en la configuracion en tipoDeSerVivo 1 es planta y 2 es animal
    public void agregarSeresVivosInicialesATablero(int numeroDeSeresVivosAAgregar, int tipoDeSerVivo){
        int tipoDeSerVivoAleatorio = 0;
        int filaAleatoria = 0;
        int columnaAleatoria = 0;

        while (numeroDeSeresVivosAAgregar > 0) {
            tipoDeSerVivoAleatorio = (int) (Math.random() * 3);
            filaAleatoria = (int) (Math.random() * Configuracion.NUMERO_DE_FILAS);
            columnaAleatoria = (int) (Math.random() * Configuracion.NUMERO_DE_COLUMNAS);

            // Condición para evitar que en el inicio no existan dos o mas seres vivos en una sola celda
            if (tipoDeSerVivoAleatorio == tipoDeSerVivo && celdaDelTablero[filaAleatoria][columnaAleatoria] == 0 ) {
                numeroDeSeresVivosAAgregar--;
                celdaDelTablero[filaAleatoria][columnaAleatoria] = tipoDeSerVivo;
                // Crear ser vivo y agregarlo a la lista
                if (tipoDeSerVivoAleatorio == 1) {
                    plantas.add(new Planta(filaAleatoria, columnaAleatoria,Configuracion.ENERGIA_INICIAL_PLANTAS, true));
                }
                if (tipoDeSerVivoAleatorio == 2) {
                    animales.add(new Animal(filaAleatoria, columnaAleatoria, Configuracion.ENERGIA_INICIAL_ANIMALES, 0, true));
                }
            }
        }
    }

    public void imprimeTablero() {
        for (int i = 0; i < Configuracion.NUMERO_DE_FILAS; i++) {
            for (int j = 0; j < Configuracion.NUMERO_DE_COLUMNAS; j++) {
                if (celdaDelTablero[i][j] == 1) {
                    System.out.print("\uD83C\uDF31");
                } else if (celdaDelTablero[i][j] == 2) {
                    System.out.print("\uD83D\uDC07");
                } else {
                    System.out.print("..");
                }
            }
            System.out.println();
        }
    }

    public int contarPlantas(boolean estaVivo) {
        int totalEncontrados = 0;
        for (Planta planta : plantas) {
            if (planta.isEstaVivo()) {
                totalEncontrados++;
            }
        }
        return totalEncontrados;
    }

    public int contarAnimales(boolean estaVivo) {
        int totalEncontrados = 0;
        for (Animal animal : animales) {
            if (animal.isEstaVivo()) {
                totalEncontrados++;
            }
        }
        return totalEncontrados;
    }

    public void incrementarEnergiaPlanta() {
        for (Planta planta : plantas) {
            if (planta.isEstaVivo() && planta.getEnergia() < Configuracion.ENERGIA_MAXIMA_PLANTAS) {
                planta.setEnergia(true);
            }
        }
    }

    public void incrementarEdadAnimal(int numeroDeCiclo) {
        for (Animal animal : animales) {
            if (animal.isEstaVivo()) {
                animal.setEdad();
            }
            // animal muere de viejo
            if (animal.getEdad() >= Configuracion.EDAD_MAXIMA_ANIMALES) {
                animal.setEstaVivo(false);
            }
        }
        System.out.println(numeroDeCiclo);
    }

    public void movimientoAnimal() {
        for (Animal animal : animales) {
            int fila = animal.getFila();
            int columna = animal.getColumna();
            if (animal.isEstaVivo()) {
                animal.setFila(fila);
                animal.setColumna(columna);
                // Restar energía por cada movimiento
                animal.setEnergia(false);
            }
            if (animal.getEnergia() <= 0) {
                animal.setEstaVivo(false);
            }
        }
    }

    public void actualizaRegistrosEnTablero() {
        for (int i = 0; i < Configuracion.NUMERO_DE_FILAS; i++) {
            for (int j = 0; j < Configuracion.NUMERO_DE_COLUMNAS; j++) {
                celdaDelTablero[i][j] = 0;
                for (Animal animal : animales) {
                    if (animal.isEstaVivo() && animal.getFila() == i && animal.getColumna() == j) {
                        celdaDelTablero[i][j] = 2;
                    }
                }
                for (Planta planta : plantas) {
                    if (planta.isEstaVivo() && planta.getFila() == i && planta.getColumna() == j) {
                        celdaDelTablero[i][j] = 1;
                    }
                }
            }
        }
    }

    public void imprimeSeresVivos() {
        for (Planta planta : plantas) {
            System.out.print("Planta: " + planta.getIdentificador());
            System.out.print(" Coordenadas " + planta.getFila() + "-" + planta.getColumna());
            System.out.print(" Energía " + planta.getEnergia());
            System.out.println(" Esta vivo " + planta.isEstaVivo());
        }
        for (Animal animal : animales) {
            System.out.print("Animal: " + animal.getIdentificador());
            System.out.print(" Coordenadas " + animal.getFila() + "-" + animal.getColumna());
            System.out.print(" Energía " + animal.getEnergia());
            System.out.print(" Edad " + animal.getEdad());
            System.out.println(" Esta vivo " + animal.isEstaVivo());
        }
    }

    public void animalCome() {
        for (Animal animal : animales) {
            for (Planta planta : plantas) {
                if (animal.isEstaVivo() && planta.isEstaVivo() && animal.getFila() == planta.getFila()
                        && animal.getColumna() == planta.getColumna()) {
                    planta.setEnergia(false);
                    animal.setEnergia(true);
                }
                if (planta.getEnergia() <= 0) {
                    planta.setEstaVivo(false);
                }
            }
        }
    }

    // Buscamos animales que esten en una misma celda
    public void animalesSeReproducen() {
        for (int i = 0; i < animales.size(); i++) {
            for (int j = i + 1; j < animales.size(); j++) {
                if (animales.get(i).animalesEnMismaCelda(animales.get(j)) && animales.get(i).isEstaVivo() && animales.get(j).isEstaVivo()) {
                    // buscamos una celda vecina libre si existe nace un nuevo animal
                    if (buscarCeldaVecinaVacia(animales.get(i).getFila(), animales.get(i).getColumna())) {
                        animales.get(i).setEnergia(false);
                        animales.get(j).setEstaVivo(false);
                    }
                }
            }

        }
    }

    boolean buscarCeldaVecinaVacia(int fila, int columna) {
        int[][] celdasVecinas = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };

        for (int[] celdaVecina : celdasVecinas) {
            int filaVecina = fila + celdaVecina[0];
            int columnaVecina = columna + celdaVecina[1];

            // Validar que estan dentro de los límites y que este vacía
            if (filaVecina >= 0 && filaVecina < Configuracion.NUMERO_DE_FILAS && columnaVecina >= 0
                    && columnaVecina < Configuracion.NUMERO_DE_COLUMNAS && celdaDelTablero[filaVecina][columnaVecina] == 0) {
                animales.add(new Animal(filaVecina, columnaVecina, Configuracion.ENERGIA_INICIAL_ANIMALES, 0, true));
                return true;
            }
        }
        return false;
    }

}
