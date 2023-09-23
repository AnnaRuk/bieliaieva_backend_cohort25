package de.ait.ec.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@DisplayName("Endpoint /courses is works:")
@DisplayNameGeneration(value =
        DisplayNameGenerator.ReplaceUnderscores.class)
class CoursesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /courses:")
    public class GetCourses {

        @Test
        @Sql(scripts = {"sql/schema.sql","sql/data.sql"})
        public void return_empty_list_of_courses_test() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(jsonPath("$.[0].id",is(1)))
                    .andExpect(jsonPath("$.[1].title", is("testTitle2")))
                    .andExpect(status().isOk());

        }
    }

    @Nested
    @DisplayName("POST /courses:")
    public class PostCourses {
        @Test
        @Sql(scripts = {"sql/schema.sql","sql/data.sql"})
        public void created_new_course_test() throws Exception {
         mockMvc.perform(post("/api/courses")
        .contentType("application/json")
        .content("{\n" +
                "  \"title\": \"Новый курс\",\n" +
                "  \"beginDate\": \"2022-02-02\",\n" +
                "  \"endDate\": \"2023-02-02\",\n" +
                "  \"description\": \"Описание нового курса\",\n" +
                "  \"price\": 100.0\n" +
                "}"))
                         .andExpect(jsonPath("$.id",is(5)))
                 .andExpect(status().isCreated());

        }
    }

}