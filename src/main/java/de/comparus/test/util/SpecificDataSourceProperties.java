package de.comparus.test.util;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Validated
@Getter
@Setter
public class SpecificDataSourceProperties {
    @NotNull
    private String name;
    @Pattern(regexp = "postgres|oracle")
    private String strategy;
    @NotNull
    private String url;
    @NotNull
    private String table;
    @NotNull
    private String user;
    @NotNull
    private String password;
    @Valid
    @NotNull
    private UserMapping mapping;

}
