package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    @GetMapping
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{userId}")
    public UserDto getUser(@PathVariable int userId) {
        UserDto userDto = new UserDto(1, "Tomasz", "tomasz@tomasz.pl", false
                , LocalDate.of(2000, 1, 1), "2154");
        return userDto;
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
    }

    @DeleteMapping
    public void deleteUser(@RequestBody int userId) {
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        userDto = new UserDto(2, "Tom", "tom@wp.pl", false
                , LocalDate.of(2001,2,3), "352A");
        return userDto;
    }

}
