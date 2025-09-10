package com.murathnakts.service;

import com.murathnakts.dto.DtoGroup;
import com.murathnakts.dto.DtoGroupIU;

public interface IGroupService {
    public DtoGroup createGroup(DtoGroupIU dtoGroupIU);
    public DtoGroup addUserToGroup(Long userId, Long groupId);
    public DtoGroup getGroup(Long groupId);
}
