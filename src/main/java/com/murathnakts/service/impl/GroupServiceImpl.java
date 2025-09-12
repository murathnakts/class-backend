package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoGroup;
import com.murathnakts.dto.DtoGroupIU;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.entity.Group;
import com.murathnakts.entity.User;
import com.murathnakts.enums.MessageType;
import com.murathnakts.enums.RoleType;
import com.murathnakts.exception.BaseException;
import com.murathnakts.exception.ErrorMessage;
import com.murathnakts.jwt.JwtService;
import com.murathnakts.repository.GroupRepository;
import com.murathnakts.repository.UserRepository;
import com.murathnakts.service.IGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private User findUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString())));
    }

    private Group findGroupOrThrow(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.GROUP_NOT_FOUND, groupId.toString())));
    }

    private DtoGroup convertDto(Group group) {
        DtoGroup dtoGroup = new DtoGroup();
        List<DtoUser> dtoUsers = new ArrayList<>();
        for (User member : group.getMembers()) {
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(member, dtoUser);
            dtoUsers.add(dtoUser);
        }
        BeanUtils.copyProperties(group, dtoGroup);
        dtoGroup.setMembers(dtoUsers);
        return dtoGroup;
    }

    private Group makeGroup(DtoGroupIU dtoGroupIU, User user) {
        Group group = new Group();
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        group.setCreatorUser(user);
        group.setGroupName(dtoGroupIU.getGroupName());
        group.setDescription(dtoGroupIU.getDescription());
        group.setCreateTime(new Date());
        group.setMembers(users);
        return group;
    }

    @Override
    public DtoGroup createGroup(DtoGroupIU dtoGroupIU) {
        User user = findUserOrThrow(jwtService.getCurrentUserId());
        if (user.getRole().equals(RoleType.PARENT)) {
            throw new BaseException(new ErrorMessage(MessageType.UNAUTHORIZED_USER, null));
        }
        Group newGroup = groupRepository.save(makeGroup(dtoGroupIU, user));
        DtoGroup dtoGroup = new DtoGroup();
        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(user, dtoUser);
        BeanUtils.copyProperties(newGroup, dtoGroup);
        List<DtoUser> dtoUsers = new ArrayList<>();
        dtoUsers.add(dtoUser);
        dtoGroup.setMembers(dtoUsers);
        dtoGroup.setCreatorUser(dtoUser);
        return dtoGroup;
    }

    @Override
    public DtoGroup addUserToGroup(Long userId, Long groupId) {
        User user = findUserOrThrow(userId);
        Group group = findGroupOrThrow(groupId);
        if (group.getMembers().stream().anyMatch(usr ->  usr.getId().equals(user.getId()))) {
            throw new BaseException(new ErrorMessage(MessageType.USER_ALREADY_ADDED, userId.toString()));
        }
        group.getMembers().add(user);
        groupRepository.save(group);
        return convertDto(group);
    }

    @Override
    public DtoGroup getGroup(Long groupId) {
        Group group = findGroupOrThrow(groupId);
        return convertDto(group);
    }
}
