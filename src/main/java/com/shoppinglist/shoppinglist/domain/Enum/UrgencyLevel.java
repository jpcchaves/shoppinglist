package com.shoppinglist.shoppinglist.domain.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UrgencyLevel {
    CRITICAL("Muito Urgente"),
    HIGH("Urgente"),
    MEDIUM("Normal"),
    LOW("Pode Esperar"),
    LOWEST("Talvez Comprar");

    private final String message;

    UrgencyLevel(String message) {
        this.message = message;
    }

    @JsonCreator
    public static UrgencyLevel fromValue(String message) {
        for (UrgencyLevel level : UrgencyLevel.values()) {
            if (message.equals(level.name())) {
                return level;
            }
        }
        throw new IllegalArgumentException("Nível de urgência inválido" + message);
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
