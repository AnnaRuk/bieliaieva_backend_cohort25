package models;

import java.time.LocalDate;
import java.util.Objects;

public class Event {

    private Long id;
    private String title;

    private LocalDate beginDate;
    private LocalDate endDate;

    public Event(String title, LocalDate beginDate, LocalDate endDate) {
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Event(Long id, String title, LocalDate beginDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
    public Event(){}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return Objects.equals(getId(), event.getId()) && Objects.equals(getTitle(), event.getTitle()) && Objects.equals(getBeginDate(), event.getBeginDate()) && Objects.equals(getEndDate(), event.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getBeginDate(), getEndDate());
    }
}
