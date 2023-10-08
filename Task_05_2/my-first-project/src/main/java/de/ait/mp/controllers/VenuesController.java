package de.ait.mp.controllers;

import de.ait.mp.dto.StandardResponseDto;
import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.venue.NewVenueDto;
import de.ait.mp.dto.venue.VenueDto;
import de.ait.mp.service.VenuesService;
import de.ait.mp.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/venues")
public class VenuesController {

    private final VenuesService venuesService;


    //POST documentation
    // ********************************************************************
    @Operation(summary = "Adding new venue", description = "Available to everyone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "venue has been added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VenueDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "There is already a venue with this name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })
    // POST ******************** ***************************************

    @PostMapping
    public ResponseEntity<VenueDto> addVenue(@RequestBody @Valid NewVenueDto newVenue){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(venuesService.addVenue(newVenue));


    }

    //POST adding an event to the venue documentation
    // ********************************************************************

    // POST adding an event to the venue ***************************************

    @PostMapping("/{venue-id}/events")
    public ResponseEntity<EventDto> addEventToVenue(@PathVariable("venue-id") Long venueId,
                                                    @RequestBody NewEventDto newEvent){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(venuesService.addEventToVenue(venueId, newEvent));


    }

    // GET all events to the venue ***************************************

    @GetMapping("/{venue-id}/events")
    public ResponseEntity<List<EventDto>> getEventsFromVenue(@PathVariable("venue-id") Long venueId){
        return ResponseEntity.ok(venuesService.getEventsFromVenue(venueId));


    }

}
