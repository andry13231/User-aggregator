package de.comparus.test.dbms;

import org.postgresql.Driver;

public class PostgresStrategy extends DBMSStrategy {

    public PostgresStrategy() {
        super(Driver.class);
    }

    protected PostgresStrategy(Class<? extends java.sql.Driver> driverClass) {
        super(driverClass);
    }

}
