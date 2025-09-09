// SlackNotifier.java
public class SlackNotifier implements DocumentObserver {
    @Override
    public void update(DocumentJob job, String message) {
        System.out.println("💬 [SlackNotifier] Publicando mensaje en Slack -> " + message);
    }
}
