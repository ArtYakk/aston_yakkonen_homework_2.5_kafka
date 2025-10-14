package com.artemyakkonen.core;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UserCreatedEvent {
    private final String eventId;
    private final LocalDateTime eventTimestamp;
    private final Long id;
    private final String name;
    private final String email;
    private final Integer age;
    private final LocalDateTime createdAt;

    private UserCreatedEvent(Long id, String name, String email, Integer age, LocalDateTime createdAt) {
        this.eventId = UUID.randomUUID().toString();
        this.eventTimestamp = LocalDateTime.now();
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
    }

    public String getEventId() { return eventId; }
    public LocalDateTime getEventTimestamp() { return eventTimestamp; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Integer getAge() { return age; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private Integer age;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserCreatedEvent build() {
            return new UserCreatedEvent(id, name, email, age, createdAt);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatedEvent that = (UserCreatedEvent) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventTimestamp=" + eventTimestamp +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", createdAt=" + createdAt +
                '}';
    }
}