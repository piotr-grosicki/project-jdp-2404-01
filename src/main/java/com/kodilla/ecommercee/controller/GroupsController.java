package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupsController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @PostMapping
    public void addGroup(@RequestBody GroupDto groupDto) {
    }

    @GetMapping(value = "{groupId}")
    public GroupDto getGroupById(@PathVariable int groupId) {
        return new GroupDto(5, "testGroup");
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(3, "updated testGroup");
    }
}
