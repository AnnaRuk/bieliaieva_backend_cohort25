package de.ait.mp.dto.venue;


import de.ait.mp.models.Venue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Venue", description = "information about venue")
public class VenueDto {

    @Schema(description = "Venue identifier", example = "1")
    private Long id;
    @Schema(description = "Venue name", example = "Carnegie Hall")
    private String venueName;
    @Schema(description = "Venue address", example = "881 7th Ave, New York, NY 10019, USA")
    private String address;
    @Schema(description = "Venue square", example = "485000.00")
    private double square;


    public static VenueDto from(Venue venue){
        return VenueDto.builder()
                .id(venue.getId())
                .venueName(venue.getVenueName())
                .address(venue.getAddress())
                .square(venue.getSquare())
                .build();

    }

    public static List<VenueDto> from(List<Venue> venueList){
        return venueList.stream().map(VenueDto::from).collect(Collectors.toList());
    }




}
