package de.ait.mp.repositories;

import de.ait.mp.models.Venue;

public interface VenueMyRepository {

    public Venue existsByName(String venueName);
}
