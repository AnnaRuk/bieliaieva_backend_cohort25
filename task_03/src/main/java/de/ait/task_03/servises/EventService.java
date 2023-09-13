package de.ait.task_03.servises;


import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;

import java.util.List;

public interface EventService {



    List<EventDto> getAllEvents();


    EventDto addEvent(NewEventDto newEventdto);

}
