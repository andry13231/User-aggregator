package de.comparus.test.dbms;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import de.comparus.test.util.SpecificDataSourceProperties;
import de.comparus.test.util.UserMapping;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.function.Consumer;

public abstract class DBMSStrategy {

    private Class<? extends Driver> driverClass;

    protected DBMSStrategy(Class<? extends Driver> driverClass) {
        this.driverClass = driverClass;
    }

    @SneakyThrows
    public DataSource initDataSource(SpecificDataSourceProperties properties) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUser(properties.getUser());
        dataSource.setPassword(properties.getPassword());
        dataSource.setInitialPoolSize(1);
        dataSource.setMinPoolSize(1);
        dataSource.setMaxPoolSize(2);
        dataSource.setDriverClass(driverClass.getName());

        return dataSource;
    }

    public Consumer<DataSource> initDisposal (SpecificDataSourceProperties properties) {
        return dataSource -> ((ComboPooledDataSource) dataSource).close();
    }

    public String getUsersSql (SpecificDataSourceProperties properties) {
        UserMapping mapping = properties.getMapping();
        return "SELECT "
                + mapping.getId() + ", "
                + mapping.getUsername() + ", "
                + mapping.getName() + ", "
                + mapping.getSurname()
                + " FROM " + properties.getTable();
    }

    public String getUsersByIdSql (SpecificDataSourceProperties properties) {
        return getUsersSql(properties) + " WHERE "+ properties.getMapping().getId() + " = ?";
    }

    public String getUsersByUsernameSql (SpecificDataSourceProperties properties) {
        return getUsersSql(properties) + " WHERE "+ properties.getMapping().getUsername() + " LIKE ?";
    }

}
