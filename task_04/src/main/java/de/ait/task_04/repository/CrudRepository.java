package de.ait.task_04.repository;

import java.util.List;

public interface CrudRepository<T> {

    List<T> getAllevents();
    void save(T event); //save
    void deleteEventById(Long id);
    T findById(Long id);
    void update(T newEvent);



}
