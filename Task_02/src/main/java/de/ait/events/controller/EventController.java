package de.ait.events.controller;

import de.ait.events.models.Event;
import de.ait.events.servises.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addEvent")
    public String addEvent(@RequestParam("inputTitle") String inputTitle,
                           @RequestParam("inputDescription") String inputDescription) {
        eventService.register(inputTitle, inputDescription);
        return "redirect:/events";
    }

    @GetMapping("/events")
    public String getEventsPage(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("eventsList", events);
        return "events_Page";
    }

}
