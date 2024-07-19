import java.util.ArrayList;
/*
 Proyecto - Simulación de una isla, automáta celular
 Clase viejo.Simulacion - Se crea la simulación en ciclos, realizando tareas de crear tablero, registrar bitácora en archivo
 csv, y eventos de los habitantes de la isla.
 Autor - Mario García. mariogarcia1040@gmail.com
 5-Julio-2024 | 18-Julio-2024
*/
public class Simulacion {
    Isla isla = new Isla();
    RegistroEnArchivoCsv archivoCsv = new RegistroEnArchivoCsv();

    public ArrayList<String> eventos = new ArrayList<>();

    public void ciclosDeLaSimulación(int limiteDeCiclos) {

        for (int i = 0; i < limiteDeCiclos; i++) {
            if (i == 0) {
                isla.generaTablero();
                eventos.add(i, "Tablero inicial");
                System.out.println("Crear archivo csv.");
                archivoCsv.crearArchivo();
                // Cargamos encabezados y despues el primer ciclo en el archivo
                archivoCsv.escribirEventosEnArchivoCsv("Tiempo;Animales;Plantas;Eventos\n");
            } else {
                // Ejecutamos los eventos
                isla.incrementarEnergiaPlanta();
                isla.incrementarEdadAnimal(i);
                isla.movimientoAnimal();
                isla.animalCome();
                isla.animalesSeReproducen();
                //isla.imprimeSeresVivos();
                isla.actualizaRegistrosEnTablero();
                eventos.add(i, "XXXXXX");
            }

            imprimirDatosDeCiclo(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            verificarExtincion(i);
        }
        imprimirDatosFinales(Configuracion.TOTAL_DE_CICLOS);
    }

    void imprimirDatosDeCiclo(int i) {
        System.out.println("\n----------------------------------------------");
        System.out.println("Ciclo:" + (i + 1) + " Plantas:" + isla.contarPlantas(true)
                + " Animales:" + isla.contarAnimales(true) + " Eventos:" + eventos.get(i));
        System.out.println("----------------------------------------------\n");

        archivoCsv.escribirEventosEnArchivoCsv((i + 1) + ";" + isla.contarPlantas(true)
                + ";" + isla.contarAnimales(true) + ";" + eventos.get(i) + "\n");
        isla.imprimeTablero();
    }

    void imprimirDatosFinales(int numeroDeCiclos) {
        System.out.println("\n----------------------------------------------");
        System.out.println("Ciclos:" + (numeroDeCiclos + 1) + " Plantas:" + isla.contarPlantas(true)
                + " Animales:" + isla.contarAnimales(true) );
        System.out.println("----------------------------------------------\n");
    }

    void verificarExtincion(int numeroDeCiclos) {
        if (isla.contarAnimales(true) <= 0) {
            System.out.println("\n+++ Fin de la simulación ya no hay animales +++");
            imprimirDatosFinales(numeroDeCiclos);
            System.exit(0);
        }
        if (isla.contarPlantas(true) <= 0) {
            System.out.println("\n+++ Fin de la simulación ya no hay plantas +++");
            imprimirDatosFinales(numeroDeCiclos);
            System.exit(0);
        }
    }

}
