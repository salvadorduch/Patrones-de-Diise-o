// Main.java
import java.io.File;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        try {
            // Creamos un usuario
            User user = new User("juan", "Premium");

            // Creamos un archivo temporal para que el ejemplo lo encuentre (simulación)
            File temp = File.createTempFile("archivo-ejemplo", ".txt");
            try (FileWriter fw = new FileWriter(temp)) {
                fw.write("Contenido de prueba para el job.");
            }

            // Creamos el job
            DocumentJob job = new DocumentJob(temp.getAbsolutePath(), "PDF", null, false, user);
            job.setUserEmail("juan@test.com");

            // Creamos el procesador y registramos observadores
            DocumentProcessor processor = new DocumentProcessor();
            processor.addObserver(new EmailNotifier());
            processor.addObserver(new SlackNotifier());

            // Ejecutamos el proceso
            processor.processDocument(job);

            // Nota: el archivo temporal se dejará en el tmp del sistema; es solo para demo.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
