package repository.Impl;

import models.Event;
import repository.EventRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryFileImpl implements EventRepository {
    private Long currentId = 1L;

    private final String fileName;

    public EventRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Event> getAllevents() {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .map(parsed -> new Event(Long.parseLong(parsed[0]),parsed[1],LocalDate.parse(parsed[2]),LocalDate.parse(parsed[3])))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IllegalStateException("File doesn't work" + e.getMessage());
        }

    }


    @Override
    public void addEvent(Event event) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))){
            event.setId(currentId);
            writer.write(event.getId() + "|" + event.getTitle()+ "|"
                    + event.getBeginDate() + "|" + event.getEndDate());
            writer.newLine();
        } catch (IOException e){
            throw new IllegalStateException ("File doesn't work" + e.getMessage());
        }
        currentId++;
    }

    @Override
    public void deleteEventById(Long id) {
        List<Event> events = getAllevents();
        List<Event> newEvents = new ArrayList<>();

        for (Event event: events
             ) {
            if(!event.getId().equals(id)){
                newEvents.add(event);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){

            for (Event event: newEvents
                 ) { writer.write(event.getId() + "|" + event.getTitle()+ "|"
                    + event.getBeginDate() + "|" + event.getEndDate());;
                writer.newLine();
            }

        } catch (IOException e){
            throw new IllegalStateException ("File doesn't work" + e.getMessage());
        }

        System.out.println("event with id " + id + " deleted successfully");

    }



    //TODO ID => String the best var
    @Override
    public Event findById(Long id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
           String idString = String.valueOf(id);
            return reader.lines().map(line->line.split("\\|"))
                    .filter(parsed->parsed[0].equals(idString))
                    .findFirst()
                    .map(parsed -> new Event(Long.parseLong(parsed[0]),parsed[1],LocalDate.parse(parsed[2]),LocalDate.parse(parsed[3])))
                    .orElse(null);


        } catch (IOException e){
            throw new IllegalStateException ("File doesn't work" + e.getMessage());
        }
    }

    @Override
    public void update(Event newEvent) {



    }

    //TODO stream
    @Override
    public List<Event> findByDate(LocalDate date) {
        List<Event> currentEvent = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
        while ((line = reader.readLine())!=null){
        String[] parsed =  line.split("\\|");

            if ((date.isAfter(LocalDate.parse(parsed[2])) || date.isEqual(LocalDate.parse(parsed[2])))
                    && (date.isBefore(LocalDate.parse(parsed[3])) || date.isEqual(LocalDate.parse(parsed[3])))) {
                Event event = new Event(Long.parseLong(parsed[0]),parsed[1],LocalDate.parse(parsed[2]),LocalDate.parse(parsed[3]));
                currentEvent.add(event);
            }
        }
        } catch (IOException e){
            throw new IllegalStateException ("File doesn't work" + e.getMessage());
        }
        return currentEvent;
    }
}
