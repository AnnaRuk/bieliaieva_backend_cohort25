package de.ait.mp.service;

import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.venue.NewVenueDto;
import de.ait.mp.dto.venue.VenueDto;

import java.util.List;

public interface VenuesService {
    VenueDto addVenue(NewVenueDto newVenue);

    EventDto addEventToVenue(Long venueId, NewEventDto newEvent);

    List<EventDto> getEventsFromVenue(Long venueId);
}
