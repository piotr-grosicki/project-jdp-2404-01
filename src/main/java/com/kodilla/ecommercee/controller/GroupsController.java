package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/groups")
public class GroupsController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groupService.getAllGroups()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> addGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok().body(groupMapper.mapToGroupDto(groupService.saveGroup(groupDto)));
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int groupId) {
        groupService.deleteGroupById(groupId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable int groupId) throws GroupNotFoundException {
        return ResponseEntity.ok().body(groupMapper.mapToGroupDto(groupService.getGroupById(groupId)));
    }

    @PutMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable int groupId, @RequestBody GroupDto groupDto) throws GroupNotFoundException {
        return ResponseEntity.ok().body(groupMapper.mapToGroupDto(groupService.updateGroup(groupDto, groupId)));
    }
}
