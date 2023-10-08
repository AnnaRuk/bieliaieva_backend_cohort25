package de.ait.mp.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@DisplayName("Endpoint /products is works:")
@DisplayNameGeneration(value =
        DisplayNameGenerator.ReplaceUnderscores.class)
class ProductsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET/products:")
    public class GetProducts{

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})  //"/sql/schema.sql",
        public void return_all_products() throws Exception {
            mockMvc.perform(get("/api/products")).andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is(3)))
                    .andExpect(jsonPath("$.[0].productName",is("New Product 1")))
                    .andExpect(jsonPath("$.[0].categoryId",is(1)))
                    .andExpect(jsonPath("$.[0].expirationDate",is("01.10.2025")))
                    .andExpect(jsonPath("$.[0].description",is("description 1")))
                    .andExpect(jsonPath("$.[0].price",is(1000.0)))
                    .andExpect(jsonPath("$.[0].state",is("DRAFT")));

        }

        @Test
        public void return_empty_list_of_products_for_empty_database() throws Exception {
            mockMvc.perform(get("/api/products"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is(0)));
        }

    }

    @Nested
    @DisplayName("POST/products:")
    public class PostProduct{


        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void add_product_successful_and_return_201_status() throws Exception {
            mockMvc.perform(post("/api/products")
                    .contentType("application/json")
                    .content("{\n" +
                            "  \"productName\": \"new product5\",\n" +
                            "  \"categoryID\": 2,\n" +
                            "  \"expirationDate\": \"02.02.2024\",\n" +
                            "  \"description\": \"Description of the new product\",\n" +
                            "  \"price\": 100\n" +
                            "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id",is(4)))
                    .andExpect(jsonPath("$.productName",is("new product5")))
                    .andExpect(jsonPath("$.expirationDate",is("02.02.2024")))
                    .andExpect(jsonPath("$.price",is(100.0)));

        }

        ///TODO after validation
        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})
        public void return_000_status_when_null_price() throws Exception {
            mockMvc.perform(post("/api/products")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"productName\": \"new product5\",\n" +
                                    "  \"categoryID\": 2,\n" +
                                    "  \"expirationDate\": \"02.02.2024\",\n" +
                                    "  \"description\": \"Description of the new product\",\n" +
                                    "  \"price\": 0\n" +
                                    "}"))
                    .andExpect(status().isBadRequest()); ///400


        }


    }


    @Nested
    @DisplayName("GET/products/{product-id}:")
    public class GetProduct{

        @Test
       // @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})  //"/sql/schema.sql",
        public void return_product_by_id() throws Exception {
            mockMvc.perform(get("/api/products/2")).andExpect(status().isOk())
                    .andExpect(jsonPath("$.productName",is("New Product 2")))
                    .andExpect(jsonPath("$.categoryId",is(2)))
                    .andExpect(jsonPath("$.expirationDate",is("02.10.2025")))
                    .andExpect(jsonPath("$.description",is("description 2")))
                    .andExpect(jsonPath("$.price",is(2000.0)))
                    .andExpect(jsonPath("$.state",is("DRAFT")));

        }

        @Test
        //@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Sql(scripts = {"/sql/data.sql"})  //"/sql/schema.sql",
        public void return_404_product_by_id_not_found() throws Exception {
            mockMvc.perform(get("/api/products/6")).andExpect(status().isNotFound());

        }


    }

}