import java.util.ArrayList;
/*
 Proyecto - Simulación de una isla, automáta celular
 Clase viejo.Simulacion - Se crea la simulación en ciclos, llama a los eventos de la isla, imprime datos generales y
 verifica casos donde se detiene la simulación.
 Autor - Mario García. mariogarcia1040@gmail.com
 5-Julio-2024 | 20-Julio-2024
*/
public class Simulacion {
    Isla isla = new Isla();

    public void ciclosDeLaSimulación(int limiteDeCiclos) {

        for (int i = 0; i < limiteDeCiclos; i++) {
            if (i == 0) {
                isla.generaTablero();
            } else {
                // Ejecutamos los eventos
                isla.incrementarEnergiaPlanta();
                isla.incrementarEdadAnimal();
                isla.movimientoAnimal();
                isla.animalCome();
                isla.animalesSeReproducen();
                //isla.imprimeSeresVivos();
                isla.registrarDatosYEventosDelCiclo(i);
                isla.actualizaRegistrosEnTablero();
            }

            imprimirDatosDeCiclo(i);
            verificarExtincion(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        imprimirDatosFinales(Configuracion.TOTAL_DE_CICLOS);
    }

    void imprimirDatosDeCiclo(int i) {
        System.out.println("\n----------------------------------------------");
        System.out.println("Ciclo:" + (i + 1) + " Plantas:" + isla.contarPlantas()
                + " Animales:" + isla.contarAnimales());
        System.out.println("----------------------------------------------\n");

        isla.imprimeTablero();
    }

    void imprimirDatosFinales(int totalDeCiclos) {
        System.out.println("\n----------------------------------------------");
        System.out.println("Fin de la simulación");
        System.out.println("Ciclos:" + totalDeCiclos + " Plantas:" + isla.contarPlantas()
                + " Animales:" + isla.contarAnimales() );
        System.out.println("----------------------------------------------\n");
    }

    void verificarExtincion(int numeroDeCiclo) {
        if (isla.contarAnimales() <= 0) {
            System.out.println("\n+++ Fin de la simulación ya no hay animales +++");
            imprimirDatosFinales(Configuracion.TOTAL_DE_CICLOS);
            System.exit(0);
        }
        if (isla.contarPlantas() <= 0) {
            System.out.println("\n+++ Fin de la simulación ya no hay plantas +++");
            imprimirDatosFinales(Configuracion.TOTAL_DE_CICLOS);
            System.exit(0);
        }
    }

}
