package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public Group saveGroup(GroupDto groupDto) {
        return groupRepository.save(groupMapper.mapToGroup(groupDto));
    }

    public void deleteGroupById(Integer groupId) {
        groupRepository.deleteById(groupId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Integer groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(String.format("Group with id %s not found", groupId)));
    }


    public Group updateGroup(GroupDto groupDto, int groupId) throws GroupNotFoundException {
        if (!groupRepository.existsById(groupId)) {
            throw new GroupNotFoundException(String.format("Group with id %s not found", groupId));
        } else {
            groupDto.setGroupId(groupId);
            return saveGroup(groupDto);
        }
    }
}
