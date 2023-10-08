package de.ait.mp.service;

import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);

}
