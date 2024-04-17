package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroupById(Integer groupId) {
        groupRepository.deleteById(groupId);
    }

    public List<Group> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        new ArrayList<>(groups);
        return groups;
    }

    public Optional<Group> getGroupById(Integer groupId) {
        return groupRepository.findById(groupId);
    }

    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }
}
