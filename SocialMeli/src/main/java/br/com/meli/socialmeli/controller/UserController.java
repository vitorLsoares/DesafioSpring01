package br.com.meli.socialmeli.controller;

import br.com.meli.socialmeli.dto.UserFollowerDTO;
import br.com.meli.socialmeli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserFollowerDTO> countUserFollowers(@PathVariable long userId) {
        try {
            UserFollowerDTO userFollowersCount = userService.getFollowersCountOfUser(userId);
            return new ResponseEntity<>(userFollowersCount, HttpStatus.OK);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
