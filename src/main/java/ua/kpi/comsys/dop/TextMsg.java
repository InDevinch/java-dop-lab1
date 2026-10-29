package ua.kpi.comsys.dop;

public record TextMsg(String sender, String text) implements Notification {
}
