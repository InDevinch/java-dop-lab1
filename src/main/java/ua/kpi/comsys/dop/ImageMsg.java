package ua.kpi.comsys.dop;

public record ImageMsg(String sender, String url, int sizeKb) implements Notification {
}
