package de.ait.mp.service.impl;

import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.venue.NewVenueDto;
import de.ait.mp.dto.venue.VenueDto;
import de.ait.mp.exceptions.RestException;
import de.ait.mp.models.Event;
import de.ait.mp.models.Venue;
import de.ait.mp.repositories.EventsRepository;
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
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND, "Venue not found"));

        Event event = Event.builder()
                .title(newEvent.getTitle())
                .date(LocalDate.parse(newEvent.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .venue(venue)
                .build();

        eventsRepository.save(event);

        return EventDto.from(event);
    }

    @Override
    public List<EventDto> getEventsFromVenue(Long venueId) {
        Venue venue = venueRepository.findById(venueId).orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND, "Venue not found"));

        Set<Event> eventSet = venue.getEventsThisVenue();
        return EventDto.from(eventSet);
    }
}
