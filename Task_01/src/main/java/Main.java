import controllers.RegisterController;
import repository.EventRepository;
import repository.Impl.EventRepositoryFileImpl;
import repository.Impl.EventRepositoryListImpl;
import servises.Impl.RegisterEventServiceImpl;
import servises.RegisterEventService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //LIST
//         EventRepository eventRepositoryList = new EventRepositoryListImpl();
//        RegisterEventServiceImpl registerEventService = new RegisterEventServiceImpl(eventRepositoryList);

        //FILE
       EventRepository eventRepositoryFile = new EventRepositoryFileImpl("events.txt");
       RegisterEventServiceImpl registerEventService = new RegisterEventServiceImpl(eventRepositoryFile);

       RegisterController registerController = new RegisterController(scanner, registerEventService);

       boolean isRun = true;
        while (isRun) {
            String command = scanner.nextLine();
            switch (command) {
                case "/addEvent" -> registerController.register();
                case "/events" -> registerController.getAllEvents();
                case "/find by ID" -> registerController.findEventById();
                case "/events by data" -> registerController.getAllCurrentEvents(); //2007-12-03
                case "/delete by ID" -> registerController.deleteEventById();
                case "/update event" -> registerController.updateById();
                case "/exit" -> isRun = false;
            }
        }
    }
    }

