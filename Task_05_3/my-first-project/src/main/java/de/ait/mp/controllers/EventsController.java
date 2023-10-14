package de.ait.mp.controllers;

import de.ait.mp.dto.StandardResponseDto;
import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.product.ProductDto;
import de.ait.mp.dto.user_account.EventMemberDto;
import de.ait.mp.dto.user_account.NewUserDto;
import de.ait.mp.dto.user_account.UserDto;
import de.ait.mp.service.EventsService;
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
@RequestMapping("/api/events")
@Tags(value = @Tag(name = "Events"))
public class EventsController {

    private final EventsService eventsService;

    @Operation(summary = "Adding new event", description = "Available to everyone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "event has been added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
    })
    @PostMapping
    public ResponseEntity<EventDto> addEvent(@RequestBody @Valid NewEventDto newEvent){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventsService.addEvent(newEvent));
    }




    @Operation(summary = "Adding new user to event", description = "Available to admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "user has been added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "There is already a user with this email",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })
    @PostMapping("{event-id}/users")
    public ResponseEntity<List<UserDto>> addUserToEvent(@RequestBody EventMemberDto newUser, @PathVariable("event-id") Long eventId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventsService.addUserToEvent(eventId, newUser));

    }



    @Operation(summary = "Getting a list of members", description = "Available to admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "members not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })


@GetMapping("{event-id}/users")
    public ResponseEntity<List<UserDto>> getAllMembersOfEvent(@PathVariable("event-id") Long eventId){
        return ResponseEntity.ok(eventsService.getAllMembersOfEvent(eventId));

    }

}
