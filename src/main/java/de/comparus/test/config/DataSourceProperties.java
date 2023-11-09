package de.comparus.test.config;


import de.comparus.test.util.SpecificDataSourceProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Validated
@ConfigurationProperties
public class DataSourceProperties {

    @NotEmpty
    @Valid
    @Getter
    private List<SpecificDataSourceProperties> dataSources = new ArrayList<>();

}
