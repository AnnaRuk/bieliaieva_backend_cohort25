package de.ait.mp.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /venues is works:")
@DisplayNameGeneration(value =
        DisplayNameGenerator.ReplaceUnderscores.class)
class VenuesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("POST/api/venues:")
    public class PostVenue{ }


    @Nested
    @DisplayName("POST/api/venues/{venue-id}/events:")
    public class PostEventToVenue{}


    @Nested
    @DisplayName("POST/api/venues/{venue-id}/events:")
    public class GetAllEventsFromVenue{}


    @Nested
    @DisplayName("PUT/api/venues/{venue-id}/events/{event-id}:")
    public class UpdateEventByVenue{}


    @Nested
    @DisplayName("PUT/api/venues/{venue-id}/events/{event-id}:")
    public class DeleteEventFromVenue{}
}