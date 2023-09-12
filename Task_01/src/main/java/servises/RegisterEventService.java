package servises;

import models.Event;

import java.time.LocalDate;
import java.util.List;

public interface RegisterEventService {

    Event register(String title, LocalDate beginDate, LocalDate endDate);

    List<Event> getAllEvents();

    Event findEventById(Long id);

    List<Event> getAllCurrentEvents(LocalDate currentDate);

    void deleteEventById(Long id);
    void updateById(Event newEvent);

}
