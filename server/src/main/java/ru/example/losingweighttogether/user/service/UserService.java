package ru.example.losingweighttogether.user.service;

import ru.example.losingweighttogether.user.dto.UserDto;
import ru.example.losingweighttogether.user.dto.UserShortDto;

public interface UserService {
    UserShortDto create(UserDto user);
}
