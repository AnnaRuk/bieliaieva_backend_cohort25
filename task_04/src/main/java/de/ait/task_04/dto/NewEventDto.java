package de.ait.task_04.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(name = "Event" , description = "info for new Event")
public class NewEventDto {
    @Schema(description = "todo" , example = "QA hw lesson_08")
    private String title;
    @Schema(description = "description" , example = "add Method")
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double budget;


}
