package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getUserId(), userDto.getUsername(), userDto.getMail(),
                userDto.isBlocked(), userDto.getCreationData());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getUserId(), user.getUsername(), user.getMail(),
                user.isBlocked(), user.getCreationData());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
