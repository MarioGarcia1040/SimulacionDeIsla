/*
 Proyecto - Simulación de una isla, automáta celular
 Clase MenuInicial - Muestra menú inicial con datos de configuración e inicia la simulación
 Autor - Mario García. mariogarcia1040@gmail.com
 2-Julio-2024 | 12-Julio-2024
*/
public class MenuInicial {
    public void menuInicial(){
        System.out.println("\n----------------------------------------------");
        System.out.println("    Isla Simulación - Un autómata celular");
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("Bienvenido a la isla simulación, aquí conviven\n" +
                "plantas y animales, estos, viven, crecen, se  \n" +
                "reproducen y mueren.                          \n" +
                "Se cargarán datos iniciales de los seres que  \n" +
                "habitan la isla y estos comenzarán su vida, se\n" +
                "te mostrarán avances en lapsos, la simulación \n" +
                "termina cuando ciertas condiciones se cumplan \n" +
                "o se finalize el número de ciclos");
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("Tamaño de la isla " + Configuracion.NUMERO_DE_FILAS + "X" + Configuracion.NUMERO_DE_COLUMNAS);
        System.out.println("Duración " + Configuracion.TOTAL_DE_CICLOS + " ciclos");
        System.out.println(Configuracion.PLANTAS_INICIALES + " plantas iniciales");
        System.out.println(Configuracion.ANIMALES_INICIALES + " animales iniciales");
        new Simulacion().ciclosDeLaSimulación(Configuracion.TOTAL_DE_CICLOS );
    }
}
