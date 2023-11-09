package de.comparus.test.dbms;

import de.comparus.test.util.SpecificDataSourceProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class TestDBMSStrategyFactory extends DBMSStrategyFactory {

    public DBMSStrategy createStrategy (SpecificDataSourceProperties properties) {
        return switch (properties.getStrategy()) {
            case "postgres" -> new PostgresTSStrategy();
            default -> throw new UnsupportedOperationException("Data connection '" + properties.getStrategy() + "' is not supported");
        };
    }

}
