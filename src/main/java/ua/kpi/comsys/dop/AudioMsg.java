package ua.kpi.comsys.dop;

public record AudioMsg(String sender, int durationSeconds) implements Notification {
}
