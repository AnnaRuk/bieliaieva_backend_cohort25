package repository.Impl;

import models.Event;
import repository.EventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryListImpl implements EventRepository {
    List<Event> events = new ArrayList<>();
    private Long currentId = 1L;

    @Override
    public List<Event> getAllevents() {
        return new ArrayList<>(events);

    }

    @Override
    public void addEvent(Event event) { ///save
        events.add(event);
        event.setId(currentId);
        currentId++;

    }

    @Override
    public void deleteEventById(Long id) {
        events.removeIf(event -> event.getId().equals(id));
    }

    @Override
    public Event findById(Long id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public void update(Event newEvent) {


    }

    //TODO stream
    @Override
    public List<Event> findByDate(LocalDate date) {
        List<Event> currentEvent = new ArrayList<>();
        for (Event event : events) {
            if ((date.isAfter(event.getBeginDate()) || date.isEqual(event.getBeginDate()))
                            && (date.isBefore(event.getEndDate()) || date.isEqual(event.getEndDate()))) {
                currentEvent.add(event);
            }
            return currentEvent;
        }
        return null;
    }
}
