package Message;

import java.time.LocalDateTime;

// No setters, we want the class to be immutable
public abstract class MessageAbstract {
        private final String message;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

    protected MessageAbstract(String message) {
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }
}
