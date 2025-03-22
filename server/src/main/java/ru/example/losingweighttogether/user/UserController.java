package ru.example.losingweighttogether.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.losingweighttogether.user.dto.UserDto;
import ru.example.losingweighttogether.user.dto.UserShortDto;
import ru.example.losingweighttogether.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserShortDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }
}
