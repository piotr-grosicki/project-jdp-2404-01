package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public Group mapToGroup(GroupDto groupDto) {
        return new Group(
                groupDto.getGroupId(),
                groupDto.getDescription(),
                null);
    }

    public GroupDto mapToGroupDto(Group group) {
        return new GroupDto(
                group.getGroupId(),
                group.getDescription());
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
