package ru.example.losingweighttogether.user.mapper;

import ru.example.losingweighttogether.user.dto.UserDto;
import ru.example.losingweighttogether.user.dto.UserShortDto;
import ru.example.losingweighttogether.user.model.User;

public class UserMapper {
    public static User mapUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getGender(),
                userDto.getEmail(),
                userDto.getAge(),
                userDto.getWeightInKg(),
                userDto.getHeightInCm(),
                userDto.getGoal()
        );
    }

    public static UserShortDto mapUserToUserShortDto(User user) {
        return new UserShortDto(
                user.getName(),
                user.getGender(),
                user.getEmail(),
                user.getAge(),
                user.getWeightInKg(),
                user.getHeightInCm(),
                user.getGoal()
        );
    }
}
