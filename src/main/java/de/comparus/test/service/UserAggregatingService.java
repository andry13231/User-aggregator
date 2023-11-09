package de.comparus.test.service;

import de.comparus.test.model.User;
import de.comparus.test.query.GetUsersByIdQuery;
import de.comparus.test.query.GetUsersByUsernameQuery;
import de.comparus.test.query.GetUsersQuery;
import de.comparus.test.util.DataSourceWithSpec;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAggregatingService {

    private List<GetUsersQuery> getAllQueries;
    private List<GetUsersByIdQuery> getByIdQueries;
    private List<GetUsersByUsernameQuery> getByUsernameQueries;

    public UserAggregatingService (List<DataSourceWithSpec> dataSources) {
        getAllQueries = dataSources.stream()
                .map(GetUsersQuery::new)
                .collect(Collectors.toList());
        getByIdQueries = dataSources.stream()
                .map(GetUsersByIdQuery::new)
                .collect(Collectors.toList());
        getByUsernameQueries = dataSources.stream()
                .map(GetUsersByUsernameQuery::new)
                .collect(Collectors.toList());
    }

    public List<User> getAggregatedUsers (String username) {
        if (username == null || username.isBlank()) {
            return getAllQueries.stream()
                    .flatMap(query -> query.execute().stream())
                    .collect(Collectors.toList());
        } else {
            return getByUsernameQueries.stream()
                    .flatMap(query -> query.execute(username.replace('*', '%')).stream())
                    .collect(Collectors.toList());
        }
    }

    public List<User> getAggregatedUsersById (String id) {
        return getByIdQueries.stream()
                .flatMap(query -> query.execute(id).stream())
                .collect(Collectors.toList());
    }

}
