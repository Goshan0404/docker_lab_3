package sinara_project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sinara_project.models.user.UserDto;
import sinara_project.models.user.UserRegisterDto;
import sinara_project.service.user.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody UserRegisterDto user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserRegisterDto user) {
        return userService.verify(user);
    }

    @PostMapping("/profile")
    public UserDto getProfile(@RequestBody long id) {
        return userService.getProfile(id);
    }


    public void deleteProfile(@RequestBody long id) {
        userService.deleteProflie(id);
    }
}
