// DocumentProcessor.java
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentProcessor implements DocumentSubject {

    private final List<DocumentObserver> observers = new ArrayList<>();
    private final ConfigurationManager configManager;
    private final SystemLog log;
    private final LegacyArchiver archiver;

    // Inyección de dependencias (mejor que instanciarlos dentro del método)
    public DocumentProcessor(ConfigurationManager configManager, SystemLog log, LegacyArchiver archiver) {
        this.configManager = configManager;
        this.log = log;
        this.archiver = archiver;
    }

    // Constructor por defecto que crea dependencias simples (útil para ejemplos)
    public DocumentProcessor() {
        this(new ConfigurationManager(), new SystemLog(), new LegacyArchiver());
    }

    @Override
    public void addObserver(DocumentObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(DocumentObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(DocumentJob job, String message) {
        for (DocumentObserver obs : new ArrayList<>(observers)) {
            try {
                obs.update(job, message);
            } catch (Exception ex) {
                log.error("Un observador lanzó excepción: " + ex.getMessage());
            }
        }
    }

    // Flujo de procesamiento (simulado)
    public void processDocument(DocumentJob job) {
        log.info("Iniciando procesamiento del trabajo...");
        if (job == null) {
            log.error("Job es null.");
            return;
        }

        // Validación 1: usuario
        if (job.getRequestingUser() == null) {
            log.error("Error: Usuario no especificado.");
            notifyObservers(job, "Error: Usuario no especificado.");
            return;
        }

        // Validación 2: ruta de archivo
        if (job.getSourceFilePath() == null || job.getSourceFilePath().isEmpty()) {
            log.error("Error: Ruta de archivo no especificada.");
            notifyObservers(job, "Error: Ruta de archivo no especificada.");
            return;
        }

        File file = new File(job.getSourceFilePath());
        if (!file.exists()) {
            log.error("Error: El archivo no existe en la ruta dada.");
            notifyObservers(job, "Error: El archivo no existe en la ruta dada.");
            return;
        }

        // Tamaño máximo
        if (file.length() > configManager.getMaxFileSize()) {
            log.error("Error: El archivo es demasiado grande (" + file.length() + " bytes).");
            notifyObservers(job, "Error: El archivo es demasiado grande.");
            return;
        }

        // Control de acceso: prioridad alta solo para Premium
        if (job.isHighPriority() && !"Premium".equalsIgnoreCase(job.getRequestingUser().getPlan())) {
            log.error("Error: Solo usuarios Premium pueden usar alta prioridad.");
            notifyObservers(job, "Error: Solo usuarios Premium pueden usar alta prioridad.");
            return;
        }

        // Conversión (simulada)
        log.info("Convirtiendo a formato: " + job.getOutputFormat());
        byte[] converted;
        String fmt = job.getOutputFormat() == null ? "TXT" : job.getOutputFormat().toUpperCase();
        switch (fmt) {
            case "PDF":
                converted = new byte[150]; // simulación
                break;
            case "DOCX":
                converted = new byte[200];
                break;
            default:
                converted = new byte[80];
                break;
        }

        // Posible watermark (simulado)
        if (job.getWatermarkText() != null) {
            log.info("Aplicando watermark: " + job.getWatermarkText());
        }

        // Archivado (usa el LegacyArchiver)
        String archivePath = "archive/" + UUID.randomUUID().toString() + "." + fmt.toLowerCase();
        archiver.save(converted, archivePath);

        // Notificar éxito
        String successMsg = "Procesamiento completado con éxito. Archivo guardado en: " + archivePath;
        log.info(successMsg);
        notifyObservers(job, successMsg);

        // Acciones adicionales desacopladas (p. ej. facturación)
        triggerBilling(job.getRequestingUser(), fmt);
    }

    // Método separado para facturación: podría extraerse a un comando/servicio
    private void triggerBilling(User user, String format) {
        // Simulamos facturación
        log.info("Generando evento de facturación para " + user.getUsername() + " por formato " + format);
        // Notificamos a observadores por si algún observador necesita saberlo
        notifyObservers(null, "Billing: facturación simulada para usuario " + user.getUsername());
    }
}
