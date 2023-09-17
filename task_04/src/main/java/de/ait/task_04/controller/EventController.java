package de.ait.task_04.controller;

import de.ait.task_04.dto.EventDtoToFE;
import de.ait.task_04.dto.NewEventDto;
import de.ait.task_04.servises.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Tags(value = @Tag(name = "Event operations"))
@RequiredArgsConstructor
@Controller
public class EventController {

    private final EventService eventService;

    @Operation(summary = "getting all events", description = "for admin")
    @PostMapping("/events")
    @ResponseBody
    public EventDtoToFE addEvent(@RequestBody NewEventDto newEventdto) {
        eventService.addEvent(newEventdto);

        //TODO id
        return new EventDtoToFE(1L,
                newEventdto.getTitle(),
                newEventdto.getDescription(),
                newEventdto.getBeginDate(),
                newEventdto.getEndDate(),
                newEventdto.getBudget());

    }
    @Operation(summary = "add new event", description = "for admin")
    @ResponseBody
    @GetMapping("/events")
    public List<EventDtoToFE> getEventsPage() {
        List<EventDtoToFE> events = eventService.getAllEvents();
        return events;
    }

    }


