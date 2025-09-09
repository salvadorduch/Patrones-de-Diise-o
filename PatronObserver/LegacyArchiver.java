// LegacyArchiver.java
public class LegacyArchiver {
    // Simula un sistema externo de archivado que no podemos modificar.
    public void save(byte[] fileContent, String destinationPath) {
        System.out.println("[LegacyArchiver] Simulando guardado en: " + destinationPath +
                " (bytes: " + (fileContent == null ? 0 : fileContent.length) + ")");
        // En un caso real aquí estaría la llamada al sistema externo.
    }
}
