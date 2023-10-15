package de.ait.mp.dto.user_account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

@Data
@Schema(name = "User", description = "information for adding a new user")
public class NewUserDto {



    @Schema(description = "User name", example = "Anna")
    private String firstName;

    @Schema(description = "User lastname", example = "Bieliaieva")
    private String lastName;

    @Email
    @NotNull
    @Schema(description = "User email", example = "anna@gmail.com")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Schema(description = "User password", example = "Qwerty555!")
    private String password;


}
