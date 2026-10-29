package ua.kpi.comsys.dop;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        NotificationProcessor processor = new NotificationProcessor();

        List<Notification> inbox = List.of(
                new TextMsg("Alice", "Привіт! Як справи з лабою?"),
                new ImageMsg("Bob", "https://example.com/screenshot.png", 348),
                new AudioMsg("Charlie", 17)
        );

        for (Notification n : inbox) {
            System.out.println(processor.process(n));
            System.out.println("-----");
        }

        // перевірка валідації
        try {
            new AudioMsg("Dave", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
}
