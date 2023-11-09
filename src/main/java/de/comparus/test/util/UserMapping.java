package de.comparus.test.util;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Validated
@Getter
@Setter
public class UserMapping {

    @NotNull
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String surname;

}
