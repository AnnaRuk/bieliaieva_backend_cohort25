package de.ait.mp.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
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
@DisplayName("Endpoint /events is works:")
@DisplayNameGeneration(value =
        DisplayNameGenerator.ReplaceUnderscores.class)
class EventsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("POST/api/events:")
    public class Post_Event{


        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
       // @Sql(scripts = {"/sql/data.sql"})
        public void return_status_201_event_add_without_venue() throws Exception {
            mockMvc.perform(post(("/api/events")).contentType(MediaType.APPLICATION_JSON).content("{\n" +
                    "  \"title\": \"Party\",\n" +
                    "  \"date\": \"07.10.2023\"\n" +
                    "}")).andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title",is("Party")))
                    .andExpect(jsonPath("$.date",is("07.10.2023")))
                    ;
        }


        @Test
        public void return_status_400_validation_error_data() throws Exception {
            mockMvc.perform(post(("/api/events")).contentType(MediaType.APPLICATION_JSON).content("{\n" +
                            "  \"title\": \"Party\",\n" +
                            "  \"date\": \"07.33.2023\"\n" +
                            "}")).andExpect(status().isBadRequest());

        }

        @Test
        public void return_status_400_validation_error_empty_title() throws Exception {
            mockMvc.perform(post(("/api/events")).contentType(MediaType.APPLICATION_JSON).content("{\n" +
                    "  \"title\": \"\",\n" +
                    "  \"date\": \"07.10.2023\"\n" +
                    "}")).andExpect(status().isBadRequest());
        }


    }

    @Nested
    @DisplayName("POST/api/events/{event-id}/users:")
    public class Post_AddUserToEvent{

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void return_status_201_user_add_to_event() throws Exception {
            mockMvc.perform(post(("/api/events/1/users")).contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"userId\": 1\n" +
                                    "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.size()",is(3)));
        }

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void return_status_409_user_already_has_event() throws Exception {
            mockMvc.perform(post(("/api/events/1/users")).contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"userId\": 2\n" +
                                    "}"))
                    .andExpect(status().isConflict());
        }


    }

    @Nested
    @DisplayName("GET/api/events/{event-id}/users:")
    public class GetAllMembersOfEvent{

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void return_status_200_all_event_members() throws Exception {
            mockMvc.perform(get("/api/events/1/users")).andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is(2)))
                    .andExpect(jsonPath("$.[0].id",is(2)))
                    .andExpect(jsonPath("$.[1].id",is(3)))
                    .andExpect(jsonPath("$.[0].firstName",is("Amina")))
                    .andExpect(jsonPath("$.[1].firstName",is("Olha")))
            ;


        }

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void return_status_404_no_event_members() throws Exception {
            mockMvc.perform(get("/api/events/2/users")).andExpect(status().isNotFound());


        }
    }
}