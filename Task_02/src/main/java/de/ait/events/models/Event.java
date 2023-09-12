package de.ait.events.models;

import java.util.Objects;

public class Event {
    private Long id;
    private String title;

    private String description;


    public Event(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Event() {
    }

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return Objects.equals(getId(), event.getId()) && Objects.equals(getTitle(), event.getTitle()) && Objects.equals(getDescription(), event.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription());
    }
}
