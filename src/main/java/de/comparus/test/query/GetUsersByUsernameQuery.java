package de.comparus.test.query;

import de.comparus.test.util.DataSourceWithSpec;
import org.springframework.jdbc.core.SqlParameter;

import java.sql.Types;

public class GetUsersByUsernameQuery extends GetUsersQuery {
    public GetUsersByUsernameQuery(DataSourceWithSpec dataSourceWithSpec) {
        super(dataSourceWithSpec);
    }

    @Override
    protected void declareParameters() {
        setParameters(new SqlParameter(Types.VARCHAR));
    }

    @Override
    protected String createSql(DataSourceWithSpec dataSourceWithSpec) {
        return dataSourceWithSpec.getStrategy().getUsersByUsernameSql(dataSourceWithSpec.getProperties());
    }

}
