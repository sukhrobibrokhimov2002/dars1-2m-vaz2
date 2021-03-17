package uz.pdp.lesson1vaz2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    @NotNull(message = "name must not be  null")
    private String name;
}
