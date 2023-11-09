package de.comparus.test.query;

import de.comparus.test.util.DataSourceWithSpec;
import org.springframework.jdbc.core.SqlParameter;

import java.sql.Types;

public class GetUsersByIdQuery extends GetUsersQuery {

    public GetUsersByIdQuery(DataSourceWithSpec dataSourceWithSpec) {
        super(dataSourceWithSpec);
    }

    @Override
    protected void declareParameters() {
        setParameters(new SqlParameter(Types.VARCHAR));
    }

    @Override
    protected String createSql(DataSourceWithSpec dataSourceWithSpec) {
        return dataSourceWithSpec.getStrategy().getUsersByIdSql(dataSourceWithSpec.getProperties());
    }
}
