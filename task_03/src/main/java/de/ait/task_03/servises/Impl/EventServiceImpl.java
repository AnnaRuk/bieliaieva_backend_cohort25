package de.ait.task_03.servises.Impl;

import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;
import de.ait.task_03.models.Event;
import de.ait.task_03.repository.EventRepository;
import de.ait.task_03.servises.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.task_03.dto.EventDto.from;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventDto> getAllEvents() {
        return from(eventRepository.getAllevents());
    }

    @Override
    public EventDto addEvent(NewEventDto newEventdto) {
        Event event = new Event(newEventdto.getTitle(),
                newEventdto.getDescription());
        eventRepository.save(event);
        return from(event);
    }

}