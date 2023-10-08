package de.ait.mp.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Schema(name = "Product", description = "information for adding a new product")
public class NewProductDto {


//    @NotNull(message = "must not be empty")
//    @NotBlank(message = "must not be empty")
//    @NotEmpty(message = "must not be empty")
    @NotNull
    @NotBlank
    @NotEmpty
    @Schema(description = "Product name", example = "Pupa")
    private String productName;

    @Min(value = 1, message = "Category ID must be greater than or equal to 1")
    @Schema(description = "Identifier of a product category", example = "1")
    private int categoryId;

    @Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19[0-9]{2}|20[0-9]{2})$", message = "please check the date")
    @Schema(description = "use by the time", example = "23.07.2025")
    private String expirationDate;


    @Schema(description = "Product description", example = "Toy doll for kids, lifelike, collectible, various styles, sizes, and materials")
    @Size(min = 10, max = 5000,message = "number of letters from 10 to 5000")
    private String description;


    @Min(value = 0, message = "minPrice = 0.00 EUR")
    @Max(value = 10000,  message = "maxPrice = 10_000.00 EUR")
    @Schema(description = "price", example = "99.99")
    private Double price;


/*
{
"productName": "new product",
"categoryID": 2,
"expirationDate": "02.02.2024",
"description": "Description of the new product",
"price": 100.0
}
 */
}
