package de.ait.mp.service.impl;

import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.event.UpdateEventDto;
import de.ait.mp.dto.user_account.UserDto;
import de.ait.mp.dto.venue.NewVenueDto;
import de.ait.mp.dto.venue.VenueDto;
import de.ait.mp.exceptions.RestException;
import de.ait.mp.models.Event;
import de.ait.mp.models.Venue;
import de.ait.mp.repositories.EventsRepository;
import de.ait.mp.repositories.UsersRepository;
import de.ait.mp.repositories.VenueMyRepository;
import de.ait.mp.repositories.VenueRepository;
import de.ait.mp.service.VenuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VenuesServiceImpl implements VenuesService {

    private final VenueRepository venueRepository;
    private final VenueMyRepository venueMyRepository;
    private final EventsRepository eventsRepository;





    @Override
    public VenueDto addVenue(NewVenueDto newVenue) {
        ///check name

        if(venueMyRepository.existsByName(newVenue.getVenueName())!=null){
            throw new RestException(HttpStatus.CONFLICT,"Venue with name <" + newVenue.getVenueName() + "> already exists");
        }

        Venue venue = Venue.builder()
                .venueName(newVenue.getVenueName())
                .address(newVenue.getAddress())
                .square(newVenue.getSquare())
                .build();

        venueRepository.save(venue);
        return VenueDto.from(venue);

    }

    @Override
    public EventDto addEventToVenue(Long venueId, NewEventDto newEvent) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND,
                "Venue with id: " + venueId + " not found"));

        Event event;

        if(newEvent.getExistsEventId()==null){
            event = Event.builder()
                    .title(newEvent.getTitle())
                    .date(LocalDate.parse(newEvent.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .venue(venue)
                    .build();

        } else {

            event = eventsRepository.findById(newEvent.getExistsEventId()).orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND,
                    "Event with id: " + newEvent.getExistsEventId() + " not found"));
            event.setVenue(venue);
        }
        eventsRepository.save(event);
        return EventDto.from(event);
    }

    @Override
    public List<EventDto> getEventsFromVenue(Long venueId) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND, "Venue not found"));

        Set<Event> eventSet = venue.getEventsThisVenue();
        return EventDto.from(eventSet);
    }

    @Override
    public EventDto updateEventByVenue(Long eventId, Long venueId, UpdateEventDto updateEvent) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND, "Venue not found"));

        Event event = eventsRepository.findByVenueAndId(venue,eventId).orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                "Lesson with id <" + eventId + "> not found in venue with id <" + venueId + ">"));

        if(updateEvent.getTitle() != null) {
        event.setTitle(updateEvent.getTitle());
        }
        if(updateEvent.getDate() != null ){
            event.setDate(LocalDate.parse(updateEvent.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        }

        eventsRepository.save(event);
        return EventDto.from(event);
    }

    @Override
    public EventDto deleteEventFromVenue(Long venueId, Long eventId) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND,
                "Venue with id: " + venueId + " not found"));

        Event eventsForDel = eventsRepository.findByVenueAndId(venue,eventId).orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                "Lesson with id <" + eventId + "> not found in venue with id <" + venueId + ">"));

        eventsForDel.setVenue(null);
        eventsRepository.save(eventsForDel);
        return EventDto.from(eventsForDel);
    }


}
