package de.ait.mp.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "List of Validation Errors", description = "about validation check")
public class ValidationErrorsDto {

    @Schema(description = "list of validation errors")
    private List<ValidationErrorDto> errors;
}
