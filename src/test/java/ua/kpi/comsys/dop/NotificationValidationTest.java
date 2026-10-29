package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Перевірка валідації в компактних конструкторах records.
 */
class NotificationValidationTest {

    // ---- TextMsg ----

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t"})
    void textMsgInvalidSender(String sender) {
        assertThrows(IllegalArgumentException.class, () -> new TextMsg(sender, "hi"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n"})
    void textMsgInvalidText(String text) {
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("Alice", text));
    }

    @Test
    void textMsgValid() {
        assertDoesNotThrow(() -> new TextMsg("Alice", "hi"));
    }

    // ---- ImageMsg ----

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void imageMsgInvalidSender(String sender) {
        assertThrows(IllegalArgumentException.class,
                () -> new ImageMsg(sender, "https://example.com/a.jpg", 10));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void imageMsgInvalidUrl(String url) {
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("Bob", url, 10));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void imageMsgInvalidSize(int sizeKb) {
        assertThrows(IllegalArgumentException.class,
                () -> new ImageMsg("Bob", "https://example.com/a.jpg", sizeKb));
    }

    @Test
    void imageMsgValid() {
        assertDoesNotThrow(() -> new ImageMsg("Bob", "https://example.com/a.jpg", 1));
    }

    // ---- AudioMsg ----

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void audioMsgInvalidSender(String sender) {
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg(sender, 30));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -5})
    void audioMsgInvalidDuration(int duration) {
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg("Charlie", duration));
    }

    @Test
    void audioMsgValid() {
        assertDoesNotThrow(() -> new AudioMsg("Charlie", 1));
    }
}
