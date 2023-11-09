package de.comparus.test.config;

import de.comparus.test.dbms.DBMSStrategyFactory;
import de.comparus.test.util.DataSourceWithSpec;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@EnableConfigurationProperties(DataSourceProperties.class)
@Configuration
public class DataSourceConfig implements DisposableBean {

    @Autowired
    private DBMSStrategyFactory strategyFactory;
    private List<DataSourceWithSpec> dataSources;

    @Bean
    public List<DataSourceWithSpec> dataSources (DataSourceProperties dataSourceProperties) {
        return dataSources = dataSourceProperties.getDataSources().stream()
                .map(sp -> new DataSourceWithSpec(strategyFactory.createStrategy(sp), sp))
                .collect(Collectors.toList());
    }

    @Override
    public void destroy() {
        for (DataSourceWithSpec ds : dataSources) {
            try {
                ds.destroy();
            } catch (Exception e) {}
        }
    }
}
