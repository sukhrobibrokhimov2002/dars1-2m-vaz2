package uz.pdp.lesson1vaz2.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "Email must not be empty")
    private String email;
    @NotNull(message = "Password must not be empty")
    private String password;
}
