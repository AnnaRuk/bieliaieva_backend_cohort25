package de.ait.ec.dto;

import de.ait.ec.models.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Schema(name = "New course", description = "Information for adding a course")
public class NewCourseDto {

    @Schema(description = "title", example = "Java")
    private String title;
    @Schema(description = "description", example = "java-core course")
    private String description;
    @Schema(description = "begin date of course", example = "2023-10-05")
    private String beginDate;
    @Schema(description = "end date of course", example = "2024-10-05")
    private String endDate;
    @Schema(description = "price", example = "2000.00")
    private double price;

}
