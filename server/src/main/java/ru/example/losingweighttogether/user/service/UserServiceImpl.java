package ru.example.losingweighttogether.user.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.losingweighttogether.exception.DuplicatedDataException;
import ru.example.losingweighttogether.user.dto.UserDto;
import ru.example.losingweighttogether.user.dto.UserShortDto;
import ru.example.losingweighttogether.user.mapper.UserMapper;
import ru.example.losingweighttogether.user.model.User;
import ru.example.losingweighttogether.user.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Добавляем в базу данных нового пользователя
    @Override
    @Transactional
    public UserShortDto create(UserDto user) {
        log.debug("Добавляем нового пользователя в базу данных.");

        // Проверяем есть ли такой пользователь в бд
        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new DuplicatedDataException("Пользователь с email " + user.getEmail() + " уже существует.");
        }

        User savedUser = userRepository.save(UserMapper.mapUserDtoToUser(user));

        return UserMapper.mapUserToUserShortDto(savedUser);
    }

    @Transactional(readOnly = true)
    private Optional<User> getUserByEmail(String email) {
        log.debug("Получаем данные пользователя по его email.");
        return userRepository.findByEmail(email);
    }
}
