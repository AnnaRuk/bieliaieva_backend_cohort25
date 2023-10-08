package de.ait.mp.service.impl;

import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.models.Event;
import de.ait.mp.repositories.EventsRepository;
import de.ait.mp.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;
    @Override
    public EventDto addEvent(NewEventDto newEvent) {

        Event event = Event.builder()
                .title(newEvent.getTitle())
                .date(LocalDate.parse(newEvent.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .build();

        eventsRepository.save(event);

        return EventDto.from(event);
    }
}
