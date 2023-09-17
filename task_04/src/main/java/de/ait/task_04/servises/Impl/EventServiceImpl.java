package de.ait.task_04.servises.Impl;

import de.ait.task_04.dto.EventDtoToFE;
import de.ait.task_04.dto.NewEventDto;
import de.ait.task_04.models.Event;
import de.ait.task_04.repository.EventRepository;
import de.ait.task_04.servises.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.task_04.dto.EventDtoToFE.from;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventDtoToFE> getAllEvents() {
        return from(eventRepository.getAllevents());
    }

    @Override
    public EventDtoToFE addEvent(NewEventDto newEventdto) {
        Event event = Event.builder()
                .title(newEventdto.getTitle())
                .description(newEventdto.getDescription())
                .beginDate(newEventdto.getBeginDate())
                .endDate(newEventdto.getEndDate())
                .budget(newEventdto.getBudget())
                .build();
        eventRepository.save(event);
        return from(event);
    }

}