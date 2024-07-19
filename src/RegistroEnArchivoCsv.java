import java.io.File;
import java.io.IOException;
import java.nio.file.*;
/*
 Proyecto - Simulación de una isla, automáta celular
 Calse RegistroEnArchivoCsv - Crea y registra los eventos del autómata celular en un archivo CSV de texto
 Autor - Mario García. mariogarcia1040@gmail.com
 8-Julio-2024 | 9-Julio-2024
*/
public class RegistroEnArchivoCsv {

    boolean crearArchivo() {
        Path rutaYNombreDelArchivo = FileSystems.getDefault().getPath("bitacora.csv");
        if (!verificarSiExisteArchivo(String.valueOf(rutaYNombreDelArchivo))) {
            try {
                Files.createFile(rutaYNombreDelArchivo);
                System.out.println("Archivo creado con éxito " + rutaYNombreDelArchivo);
            } catch (IOException e) {
                System.out.println("Error al crear el archivo " + e.getMessage());
                System.out.println("Saliendo del programa...");
                System.exit(1);
            }
        } else {
            System.out.println("Ya existe un archivo \"bitacora.csv\", será borrado y creado nuevamente");
            borrarArchivo(String.valueOf(rutaYNombreDelArchivo));
        }
        return true;
    }

    private boolean verificarSiExisteArchivo(String rutaDelArchivo) {
        File existeArchivo = new File(rutaDelArchivo);
        return existeArchivo.exists();
    }

    void borrarArchivo(String rutaDelArchivo) {
        File archivoAborrar = new File(rutaDelArchivo);
        if (archivoAborrar.delete()) {
            System.out.println("Archivo \"bitacora.csv\" eliminado");
            crearArchivo();
        } else {
            System.out.println("No se pudo eliminar el archivo \"bitacora.csv\"");
            System.out.println("Saliendo del programa...");
            System.exit(0);
        }
    }

    void escribirEventosEnArchivoCsv(String cadenaDeEventosAEscribir) {
        Path rutaYNombreDelArchivo = FileSystems.getDefault().getPath("bitacora.csv");
        try {
            Files.writeString(rutaYNombreDelArchivo, cadenaDeEventosAEscribir, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo \"bitacora.csv\" " + e.getMessage());
        }
    }

}
