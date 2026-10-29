package ua.kpi.comsys.dop;

public record ImageMsg(String sender, String url, int sizeKb) implements Notification {

    public ImageMsg {
        if (sender == null || sender.isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be null or empty");
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        if (sizeKb <= 0) {
            throw new IllegalArgumentException("Size must be positive, got: " + sizeKb);
        }
    }
}
