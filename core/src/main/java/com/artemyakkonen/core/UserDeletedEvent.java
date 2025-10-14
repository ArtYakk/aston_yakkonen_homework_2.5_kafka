package com.artemyakkonen.core;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UserDeletedEvent {
    private final String eventId;
    private final LocalDateTime eventTimestamp;
    private final Long id;
    private final String email;

    private UserDeletedEvent(Long id, String email, String reason) {
        this.eventId = UUID.randomUUID().toString();
        this.eventTimestamp = LocalDateTime.now();
        this.id = id;
        this.email = email;
    }

    public String getEventId() { return eventId; }
    public LocalDateTime getEventTimestamp() { return eventTimestamp; }
    public Long getId() { return id; }
    public String getEmail() { return email; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String email;
        private String reason;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public UserDeletedEvent build() {
            return new UserDeletedEvent(id, email, reason);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDeletedEvent that = (UserDeletedEvent) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    @Override
    public String toString() {
        return "UserDeletedEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventTimestamp=" + eventTimestamp +
                ", id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}