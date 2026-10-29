package ua.kpi.comsys.dop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotificationProcessorTest {

    private NotificationProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new NotificationProcessor();
    }

    @Test
    void processTextMessage() {
        Notification msg = new TextMsg("Alice", "Hello, Bob!");

        String expected = """
                Notification: TEXT
                From: Alice
                Message: Hello, Bob!""";

        assertEquals(expected, processor.process(msg));
    }

    @Test
    void processImageMessage() {
        Notification msg = new ImageMsg("Bob", "https://example.com/cat.png", 512);

        String expected = """
                Notification: IMAGE
                From: Bob
                URL: https://example.com/cat.png (512 KB)""";

        assertEquals(expected, processor.process(msg));
    }

    @Test
    void processAudioMessage() {
        Notification msg = new AudioMsg("Charlie", 45);

        String expected = """
                Notification: AUDIO
                From: Charlie
                Duration: 45 seconds""";

        assertEquals(expected, processor.process(msg));
    }

    @Test
    void processNullThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> processor.process(null));
        assertEquals("Notification cannot be null", ex.getMessage());
    }
}
