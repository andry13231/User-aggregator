package de.comparus.test.controller;

import de.comparus.test.api.UsersApi;
import de.comparus.test.model.User;
import de.comparus.test.service.UserAggregatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UsersApi {

    @Autowired
    private UserAggregatingService service;

    @Override
    public ResponseEntity<List<User>> users (String username) {
        return ResponseEntity.ok(service.getAggregatedUsers(username));
    }

    @Override
    public ResponseEntity<List<User>> userById(String id) {
        return ResponseEntity.ok(service.getAggregatedUsersById(id));
    }
}
