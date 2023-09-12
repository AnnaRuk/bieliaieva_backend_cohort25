package controllers;

import models.Event;
import servises.Impl.RegisterEventServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class RegisterController {

    private final Scanner scanner;
   private final RegisterEventServiceImpl registerEventServiceImpl;

    public RegisterController(Scanner scanner, RegisterEventServiceImpl registerEventServiceImpl) {
        this.scanner = scanner;
        this.registerEventServiceImpl = registerEventServiceImpl;
    }

    public void register(){
        System.out.println("Input TITLE");
        String title = scanner.nextLine();

        System.out.println("Input DATA of beginning like: 2007-12-03");
        LocalDate beginDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Input DATA of end like: 2007-12-03");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        Event event = registerEventServiceImpl.register(title, beginDate, endDate);
        System.out.println(event);
    }

    public void getAllEvents(){

        List<Event> events = registerEventServiceImpl.getAllEvents();
        System.out.println(events);
    }

    public void findEventById(){
        System.out.println("Input ID");
        Long id = Long.parseLong(scanner.nextLine());
        Event event = registerEventServiceImpl.findEventById(id);
        System.out.println(event);
    }

    public void getAllCurrentEvents(){
        System.out.println("Input DATA like: 2007-12-03");
        LocalDate currentDate = LocalDate.parse(scanner.nextLine());
        List<Event> events = registerEventServiceImpl.getAllCurrentEvents(currentDate);
        System.out.println(events);
    }

    public void deleteEventById(){
        System.out.println("Input ID");
        Long id = Long.parseLong(scanner.nextLine());
        registerEventServiceImpl.deleteEventById(id);

    }

    public void updateById(){
        System.out.println("Input ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Input TITLE");
        String title = scanner.nextLine();
        System.out.println("Input DATA of beginning like: 2007-12-03");
        LocalDate beginDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Input DATA of end like: 2007-12-03");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        Event newEvent = new Event(id,title,beginDate,endDate);
        registerEventServiceImpl.updateById(newEvent);

    }
}
