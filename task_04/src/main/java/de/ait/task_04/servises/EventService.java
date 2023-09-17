package de.ait.task_04.servises;


import de.ait.task_04.dto.EventDtoToFE;
import de.ait.task_04.dto.NewEventDto;

import java.util.List;

public interface EventService {



    List<EventDtoToFE> getAllEvents();


    EventDtoToFE addEvent(NewEventDto newEventdto);

}
