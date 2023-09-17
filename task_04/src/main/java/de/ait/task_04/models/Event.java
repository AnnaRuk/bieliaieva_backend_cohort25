package de.ait.task_04.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Schema(name = "Event" , description = "info+id")
public class Event {
    @Schema(description = "id" , example = "1")
    private Long id;
    @Schema(description = "todo" , example = "QA hw lesson_08")
    private String title;
    @Schema(description = "description" , example = "add Method")
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double budget;


    public Event(String title, String description) {
        this.title = title;
        this.description = description;
    }

    }



