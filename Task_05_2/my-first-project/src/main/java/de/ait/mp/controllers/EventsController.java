package de.ait.mp.controllers;

import de.ait.mp.dto.StandardResponseDto;
import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.product.ProductDto;
import de.ait.mp.service.EventsService;
import de.ait.mp.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventsController {

    private final EventsService eventsService;

    //POST documentation
    // ********************************************************************
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
    // POST ******************** ***************************************

    @PostMapping
    public ResponseEntity<EventDto> addEvent(@RequestBody @Valid NewEventDto newEvent){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventsService.addEvent(newEvent));

    }



}
