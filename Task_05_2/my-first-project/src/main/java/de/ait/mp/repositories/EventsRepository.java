package de.ait.mp.repositories;

import de.ait.mp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event,Long> {
}
