package de.ait.mp.validation.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Validation Error", description = "about validation check")
public class ValidationErrorDto {

    @Schema(description = "field that has an error", example = "price")
    private String field;
    @Schema(description = "the value from the user was rejected by the server", example = "1000000.00")
    private String rejectedValue;
    @Schema(description = "message for user", example = "must be less than or equal to 10000.00")
    private String message;



}
