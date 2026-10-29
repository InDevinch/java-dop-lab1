# Лабораторна робота №1 — Data-Oriented Programming у Java

**Варіант 2** (N = 5, V = 5 mod 3 = 2): система обробки сповіщень месенджера.

## Мета

Опанувати підхід Data-Oriented Programming (DOP) у Java: моделювання даних через
`record` та `sealed interface`, валідація на межі системи (компактні конструктори),
обробка через `switch` з pattern matching та форматування результату за допомогою Text Blocks.

## Структура

```
src/main/java/ua/kpi/comsys/dop/
├── Notification.java           # sealed interface permits TextMsg, ImageMsg, AudioMsg
├── TextMsg.java                # record (sender, text)
├── ImageMsg.java               # record (sender, url, sizeKb)
├── AudioMsg.java               # record (sender, durationSeconds)
├── NotificationProcessor.java  # бізнес-логіка: switch + record patterns + text blocks
└── Main.java                   # демонстрація роботи
src/test/java/ua/kpi/comsys/dop/
├── NotificationProcessorTest.java   # тести на кожну гілку switch + null
└── NotificationValidationTest.java  # тести валідації records
```

Модель даних:

```
+ Notification
|--- TextMsg(String sender, String text)
|--- ImageMsg(String sender, String url, int sizeKb)
|--- AudioMsg(String sender, int durationSeconds)
```

Правила валідації (кидається `IllegalArgumentException`):
- `sender`, `text`, `url` — не `null` і не порожні / не з самих пробілів (`isBlank()`);
- `sizeKb`, `durationSeconds` — строго більше 0.

## Вимоги

- JDK 17+ для запуску Gradle (сам проєкт компілюється під **Java 21** — потрібний JDK
  Gradle завантажить автоматично через toolchain / foojay-resolver)
- Встановлювати Gradle не потрібно, використовується Gradle Wrapper

## Запуск

Тести:

```bash
./gradlew test
```

Демо-програма:

```bash
./gradlew run
```

На Windows замість `./gradlew` — `gradlew.bat`.

Приклад виводу:

```
Notification: TEXT
From: Alice
Message: Hi! How is the lab going?
-----
Notification: IMAGE
From: Bob
URL: https://example.com/screenshot.png (348 KB)
-----
Notification: AUDIO
From: Charlie
Duration: 17 seconds
-----
Validation error: Duration must be positive, got: 0
```

## Використані можливості Java

| Можливість | Де |
|---|---|
| Records (Java 16) | `TextMsg`, `ImageMsg`, `AudioMsg` |
| Sealed interfaces (Java 17) | `Notification` |
| Text Blocks (Java 15) | `NotificationProcessor`, тести |
| Switch expressions + pattern matching (Java 21) | `NotificationProcessor.process` |
| Record patterns / деконструкція (Java 21) | `case TextMsg(String sender, String text)` |
