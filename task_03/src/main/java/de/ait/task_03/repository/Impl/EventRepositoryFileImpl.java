package de.ait.task_03.repository.Impl;



import de.ait.task_03.models.Event;
import de.ait.task_03.repository.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository("eventRepositoryFileImpl")
public class EventRepositoryFileImpl implements EventRepository {
    private Long currentId = 1L;

    private final String fileName;

    public EventRepositoryFileImpl(@Value("${events.file-name}") String fileName) {
        this.fileName = fileName;
    }

    //DO
    @Override
    public List<Event> getAllevents() {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .map(parsed -> new Event(Long.parseLong(parsed[0]),parsed[1],parsed[2]))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IllegalStateException("File doesn't work" + e.getMessage());
        }

    }

  //DO
    @Override
    public void save(Event event) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))){
            event.setId(currentId);
            writer.write(event.getId() + "|" + event.getTitle()
                    + "|" + event.getDescription());
            writer.newLine();
        } catch (IOException e){
            throw new IllegalStateException ("File doesn't work" + e.getMessage());
        }
        currentId++;
    }




    @Override
    public void deleteEventById(Long id) {

    }


    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public void update(Event newEvent) {

    }


}
