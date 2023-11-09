package de.comparus.test.query;

import de.comparus.test.model.User;
import de.comparus.test.util.DataSourceWithSpec;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUsersQuery extends MappingSqlQuery<User> {

    public GetUsersQuery (DataSourceWithSpec dataSourceWithSpec) {
        super();

        setDataSource(dataSourceWithSpec.getDataSource());
        setSql(createSql(dataSourceWithSpec));
        declareParameters();
    }

    protected String createSql (DataSourceWithSpec dataSourceWithSpec) {
        return dataSourceWithSpec.getStrategy().getUsersSql(dataSourceWithSpec.getProperties());
    }

    protected void declareParameters () {}

    @Override
    protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4));
    }
}
