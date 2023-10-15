package de.ait.mp.dto.event;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Schema(name = "Event", description = "information for updating an exists event")
public class UpdateEventDto {


    @Schema(description = "Event title", example = "Party")
    private String title;

    @Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19[0-9]{2}|20[0-9]{2})$", message = "please check the date")
    @Schema(description = "event date", example = "21.07.2024")
    private String date;
}
