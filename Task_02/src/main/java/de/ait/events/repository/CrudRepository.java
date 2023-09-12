package de.ait.events.repository;

import java.util.List;

public interface CrudRepository<T> {

    List<T> getAllevents();
    void addEvent(T event); //save
    void deleteEventById(Long id);
    T findById(Long id);
    void update(T newEvent);



}
