package ua.kpi.comsys.dop;

public record TextMsg(String sender, String text) implements Notification {

    public TextMsg {
        if (sender == null || sender.isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be null or empty");
        }
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
    }
}
