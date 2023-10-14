package de.ait.mp.repositories;

import de.ait.mp.models.Event;
import de.ait.mp.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface EventsRepository extends JpaRepository<Event,Long> {

    Optional<Event> findByVenueAndId(Venue venue, Long eventId);

}
