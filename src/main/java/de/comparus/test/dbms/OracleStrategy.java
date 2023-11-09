package de.comparus.test.dbms;

import oracle.jdbc.OracleDriver;

import java.sql.Driver;

public class OracleStrategy extends DBMSStrategy {

    public OracleStrategy() {
        super(OracleDriver.class);
    }

    protected OracleStrategy(Class<? extends Driver> driverClass) {
        super(driverClass);
    }

}
