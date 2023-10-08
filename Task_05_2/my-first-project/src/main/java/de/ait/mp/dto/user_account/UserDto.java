package de.ait.mp.dto.user_account;


import de.ait.mp.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "User", description = "information about User")
public class UserDto {

    @Schema(description = "User identifier", example = "1")
    private Long id;

    @Schema(description = "User name", example = "Anna")
    private String firstName;

    @Schema(description = "User lastname", example = "Bieliaieva")
    private String lastName;

    @Schema(description = "User role", example = "USER")
    private String role;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().toString())
                .build();
    }

    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }

}
