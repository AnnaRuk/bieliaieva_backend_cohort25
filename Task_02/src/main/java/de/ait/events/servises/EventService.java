package de.ait.events.servises;


import de.ait.events.models.Event;

import java.util.List;

public interface EventService {

    Event register(String title, String description);

    List<Event> getAllEvents();

    Event findEventById(Long id);

    void deleteEventById(Long id);
    void updateById(Event newEvent);

}
