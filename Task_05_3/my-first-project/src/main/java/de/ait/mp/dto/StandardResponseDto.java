package de.ait.mp.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "message ", description = "information from server")
public class StandardResponseDto {


    @Schema(description = "information about errors and exceptions", example = "product not found")
    private String message;
}
