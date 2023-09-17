package de.ait.task_04.repository.Impl;

import de.ait.task_04.models.Event;
import de.ait.task_04.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcImpl implements EventRepository {

    private final DataSource dataSource;

    //TODO
    @Override
    public List<Event> getAllevents() {
        List<Event> events = new ArrayList<>();

        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:h2:file:~/databases/events_db", "admin", "qwerty007");
            Statement statement = connection.createStatement(); //obj for request to BD
            ResultSet resultSet = statement.executeQuery("select * from account;");

            while (resultSet.next()){

                Event event = new Event(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        LocalDate.parse(resultSet.getString("begin_date")),
                        LocalDate.parse(resultSet.getString("end_date")),
                        resultSet.getDouble("budget")
                        );
                events.add(event);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        List <Event> mockList = List.of(
//                new Event(1L,"Event1","party",
//                        LocalDate.of(2023,8,8),
//                        LocalDate.of(2023,8,8),
//                        1000.00),
//                new Event(2L,"Event2","birthday",
//                        LocalDate.of(2023,9,9),
//                        LocalDate.of(2023,9,10),
//                        1000.00),
//                new Event(3L,"Event3","wedding",
//                        LocalDate.of(2023,7,5),
//                        LocalDate.of(2023,7,20),
//                        1000.00),
//                new Event(4L,"Event4","work",
//                        LocalDate.of(2023,10,1),
//                        LocalDate.of(2023,10,3),
//                        1000.00)
//
//        );

        return events;
    }

    //TODO Object???
    @Override
    public void save(Event event) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id");
        jdbcInsert.withTableName("account");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", event.getTitle());
        parameters.put("description", event.getDescription());
        parameters.put("begin_date", event.getBeginDate());
        parameters.put("end_date", event.getEndDate());
        parameters.put("budget", event.getBudget());

        jdbcInsert.execute(parameters);

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
