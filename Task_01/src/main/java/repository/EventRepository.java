package repository;

import models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends CrudRepository<Event>{

    List<Event> findByDate(LocalDate date);

}
