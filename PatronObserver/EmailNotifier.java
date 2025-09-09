// EmailNotifier.java
public class EmailNotifier implements DocumentObserver {
    @Override
    public void update(DocumentJob job, String message) {
        String to = (job == null ? "(sin job)" : job.getUserEmail());
        System.out.println("📧 [EmailNotifier] Enviando email a " + to + " -> " + message);
        // Aquí se llamaría al EmailService real; lo simulamos con un print.
    }
}
