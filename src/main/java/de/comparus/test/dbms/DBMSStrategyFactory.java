package de.comparus.test.dbms;

import de.comparus.test.util.SpecificDataSourceProperties;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class DBMSStrategyFactory {

    @SneakyThrows
    public DBMSStrategy createStrategy (SpecificDataSourceProperties properties) {
        return switch (properties.getStrategy()) {
            case "postgres" -> new PostgresStrategy();
            case "oracle" -> new OracleStrategy();
            default -> throw new UnsupportedOperationException("Data provider '" + properties.getStrategy() + "' is not supported");
        };
    }

}
