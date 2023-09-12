package servises.Impl;

import models.Event;
import repository.EventRepository;
import repository.Impl.EventRepositoryListImpl;
import servises.RegisterEventService;

import java.time.LocalDate;
import java.util.List;

public class RegisterEventServiceImpl implements RegisterEventService {

    EventRepository eventRepository = new EventRepositoryListImpl();

    public RegisterEventServiceImpl(EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    @Override
    public Event register(String title, LocalDate beginDate, LocalDate endDate) {

        if (title == null || title.equals("") || title.equals(" ")){
            throw new IllegalArgumentException("Title mustn't be empty");
        }
        if (endDate.isBefore(beginDate)){
            throw new IllegalArgumentException("Check please dates");
        }

        Event event = new Event(title,beginDate,endDate);
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
    public List<Event> getAllCurrentEvents(LocalDate currentDate) {
        return eventRepository.findByDate(currentDate);
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
