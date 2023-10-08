package de.ait.mp.dto.event;

import de.ait.mp.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Event", description = "information about event")
public class EventDto {


    @Schema(description = "Event identifier", example = "1")
    private Long id;
    @Schema(description = "Event title", example = "Party")
    private String title;
    @Schema(description = "event date", example = "23.04.2024")
    private String date;
    private Long venueId;

    public static EventDto from(Event event){
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .date(event.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .venueId(event.getVenue().getId())
                .build();
    }

    public static List<EventDto> from(List<Event> eventList){
        return eventList.stream().map(EventDto::from).collect(Collectors.toList());

    }

    public static List<EventDto> from(Set<Event> eventList){
        return eventList.stream().map(EventDto::from).collect(Collectors.toList());

    }
}
