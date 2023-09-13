package de.ait.task_03.dto;


import de.ait.task_03.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Event" , description = "info")
public class EventDto {
    @Schema(description = "todo" , example = "QA hw lesson_08")
    private String title;


    public static EventDto from(Event event) {
        return new EventDto(event.getTitle());
    }

    public static List<EventDto> from(List<Event> events) {
        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());

    }


}
