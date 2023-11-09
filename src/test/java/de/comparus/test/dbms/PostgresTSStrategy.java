package de.comparus.test.dbms;

import org.testcontainers.jdbc.ContainerDatabaseDriver;

public class PostgresTSStrategy extends PostgresStrategy {

    public PostgresTSStrategy() {
        super(ContainerDatabaseDriver.class);
    }
}
