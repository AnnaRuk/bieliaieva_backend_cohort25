package de.ait.task_04.dto;


import de.ait.task_04.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Event" , description = "info")
public class EventDtoToFE {
    @Schema(description = "todo" , example = "QA hw lesson_08")

    private Long id;
    private String title;
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double budget;



    public static EventDtoToFE from(Event event) {
        return new EventDtoToFE(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getBeginDate(),
                event.getEndDate(),
                event.getBudget());

    }

    public static List<EventDtoToFE> from(List<Event> events) {
        return events.stream()
                .map(EventDtoToFE::from)
                .collect(Collectors.toList());

    }


}
