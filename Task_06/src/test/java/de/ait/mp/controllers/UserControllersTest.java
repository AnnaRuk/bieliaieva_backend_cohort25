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
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /users is works:")
@DisplayNameGeneration(value =
        DisplayNameGenerator.ReplaceUnderscores.class)
class UserControllersTest {

    @Autowired
    private MockMvc mockMvc;


    @Nested
    @DisplayName("POST/api/users/register:")
    public class PostUser{

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void return_201_status_add_user_successful() throws Exception {
            mockMvc.perform(post("/api/users/register")
                    .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                            "\"firstName\": \"Alla\",\n" +
                            "\"lastName\": \"Bieliaieva\",\n" +
                            "\"email\": \"natalia@gmail.com\",\n" +
                            "\"password\": \"Qwerty555!\"\n" +
                            "\n" +
                            "}")).andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id",is(4)))
                    .andExpect(jsonPath("$.firstName",is("Alla")))
                    .andExpect(jsonPath("$.lastName",is("Bieliaieva")))
                    .andExpect(jsonPath("$.role",is("USER")));

        }

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void return_409_status_add_user_exists() throws Exception {
            mockMvc.perform(post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                                    "\"firstName\": \"Anna\",\n" +
                                    "\"lastName\": \"Bieliaieva\",\n" +
                                    "\"email\": \"anna@gmail.com\",\n" +
                                    "\"password\": \"Qwerty555!\"\n" +
                                    "\n" +
                                    "}")).andExpect(status().isConflict());

        }
        @Test
        public void return_400_status_if_validation_password_error() throws Exception {
            mockMvc.perform(post("/api/users/register")
                    .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                            "\"firstName\": \"Anna\",\n" +
                            "\"lastName\": \"Bieliaieva\",\n" +
                            "\"email\": \"anna33@gmail.com\",\n" +
                            "\"password\": \"Qw\"\n" +
                            "\n" +
                            "}")).andExpect(status().isBadRequest());

        }

        @Test
        public void return_400_status_if_validation_email_error() throws Exception {
            mockMvc.perform(post("/api/users/register")
                    .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                            "\"firstName\": \"Anna\",\n" +
                            "\"lastName\": \"Bieliaieva\",\n" +
                            "\"email\": \"anna33gmail.com\",\n" +
                            "\"password\": \"Qwerty004!\"\n" +
                            "\n" +
                            "}")).andExpect(status().isBadRequest());

        }

    }

    @Nested
    @DisplayName("GET/users:")
    public class GetUsers{

    }

}