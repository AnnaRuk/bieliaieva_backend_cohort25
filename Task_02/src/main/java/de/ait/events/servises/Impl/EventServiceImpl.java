package de.ait.events.servises.Impl;

import de.ait.events.models.Event;
import de.ait.events.repository.EventRepository;
import de.ait.events.servises.EventService;
import de.ait.events.validation.TitleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final TitleValidator titleValidator;


@Autowired
    public EventServiceImpl(@Qualifier("eventRepositoryFileImpl") EventRepository eventRepository,
                            @Qualifier("titleValidatorRegexImpl") TitleValidator titleValidator) {

        this.eventRepository = eventRepository;
        this.titleValidator = titleValidator;

    }

    @Override
    public Event register(String title, String description) {
        titleValidator.validate(title);

        Event event = new Event(title, description);
        eventRepository.addEvent(event);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.getAllevents();
    }

    @Override
    public Event findEventById(Long id) {
        return eventRepository.findById(id);
    }


    @Override
    public void deleteEventById(Long id) {
        eventRepository.deleteEventById(id);
    }


    @Override
    public void updateById(Event newEvent) {
        eventRepository.update(newEvent);
    }
}
