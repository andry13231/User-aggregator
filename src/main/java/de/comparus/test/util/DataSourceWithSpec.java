package de.comparus.test.util;

import de.comparus.test.dbms.DBMSStrategy;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.util.function.Consumer;

@Data
public class DataSourceWithSpec implements DisposableBean {

    private DBMSStrategy strategy;
    private DataSource dataSource;
    private SpecificDataSourceProperties properties;
    @Nullable
    private Consumer<DataSource> disposal;


    @SneakyThrows
    public DataSourceWithSpec(DBMSStrategy strategy, SpecificDataSourceProperties properties) {
        this.strategy = strategy;
        dataSource = strategy.initDataSource(properties);
        disposal = strategy.initDisposal(properties);
        this.properties = properties;
    }

    @Override
    public void destroy() throws Exception {
        if(disposal != null) {
            disposal.accept(dataSource);
        }
    }
}
