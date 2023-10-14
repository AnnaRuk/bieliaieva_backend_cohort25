package de.ait.mp.dto.venue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;



@Data
@Schema(name = "Venue", description = "information for adding a new venue")
public class NewVenueDto {

    @Schema(description = "New Venue name", example = "Carnegie Hall")
    @NotNull
    private String venueName;
    @Schema(description = " New Venue address", example = "881 7th Ave, New York, NY 10019, USA")
    private String address;
    @Schema(description = "new Venue square", example = "485000.00")
    private double square;




}
