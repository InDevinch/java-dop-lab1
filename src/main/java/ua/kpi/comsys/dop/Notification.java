package ua.kpi.comsys.dop;

/**
 * Сповіщення в месенджері.
 * Ієрархія закрита (sealed), тому компілятор знає всі можливі типи
 * і switch по ній можна писати без default.
 */
public sealed interface Notification permits TextMsg, ImageMsg, AudioMsg {

    String sender();
}
