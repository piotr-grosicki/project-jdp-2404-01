package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
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

    private final GroupMapper groupMapper;
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groups));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Group> addGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        return ResponseEntity.ok().body(groupService.saveGroup(group));
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int groupId) {
        groupService.deleteGroupById(groupId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable int groupId) {
        Group group = groupService.getGroupById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found by id:" + groupId));
        GroupDto groupDto = groupMapper.mapToGroupDto(group);
        return ResponseEntity.ok(groupDto);
    }

    @PutMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable int groupId, @RequestBody GroupDto groupDto) {
        Group existingGroup = groupService.getGroupById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found by id:" + groupId));

        Group updatedGroup = groupMapper.mapToGroup(groupDto);
        updatedGroup.setGroupId(existingGroup.getGroupId());

        Group savedGroup = groupService.updateGroup(updatedGroup);
        GroupDto savedGroupDto = groupMapper.mapToGroupDto(savedGroup);
        return ResponseEntity.ok(savedGroupDto);
    }
}
