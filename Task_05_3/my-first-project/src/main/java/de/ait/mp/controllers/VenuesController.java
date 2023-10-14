package de.ait.mp.controllers;

import de.ait.mp.dto.StandardResponseDto;
import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.event.UpdateEventDto;
import de.ait.mp.dto.user_account.UserDto;
import de.ait.mp.dto.venue.NewVenueDto;
import de.ait.mp.dto.venue.VenueDto;
import de.ait.mp.service.VenuesService;
import de.ait.mp.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/venues")
@Tags(value = @Tag(name = "Venues"))
public class VenuesController {

    private final VenuesService venuesService;



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

    @PostMapping
    public ResponseEntity<VenueDto> addVenue(@RequestBody @Valid NewVenueDto newVenue){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(venuesService.addVenue(newVenue));


    }


    @Operation(summary = "Adding new event to venue", description = "Available to everyone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "event has been added to venue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),

    })


    @PostMapping("/{venue-id}/events")
    public ResponseEntity<EventDto> addEventToVenue(@PathVariable("venue-id") Long venueId,
                                                    @RequestBody NewEventDto newEvent){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(venuesService.addEventToVenue(venueId, newEvent));

    }

    @Operation(summary = "Getting a list of events of venue", description = "Available to admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(responseCode = "404",
                    description = "events not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })



    @GetMapping("/{venue-id}/events")
    public ResponseEntity<List<EventDto>> getEventsFromVenue(@PathVariable("venue-id") Long venueId){
        return ResponseEntity.ok(venuesService.getEventsFromVenue(venueId));


    }


    @Operation(summary = "Updating event information", description = "Updating event data by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))),
    })


    @PutMapping("/{venue-id}/events/{event-id}")
    public ResponseEntity<EventDto> updateEventByVenue(@PathVariable("event-id") Long eventId,
                                                       @PathVariable("venue-id") Long venueId,
                                                       @RequestBody @Valid UpdateEventDto updateEvent){
        return ResponseEntity.ok(venuesService.updateEventByVenue(eventId,venueId,updateEvent));
    }


    @Operation(summary = "Delete event", description = "Delete an event by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "event from venue delete successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))),
    })



    @DeleteMapping("/{venue-id}/events/{event-id}")
    public ResponseEntity<EventDto> deleteEventFromVenue(@PathVariable("venue-id") Long venueId,
                                                          @PathVariable("event-id") Long eventId){

       return ResponseEntity.ok(venuesService.deleteEventFromVenue(venueId, eventId));
    }


}
