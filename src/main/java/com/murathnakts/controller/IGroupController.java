package com.murathnakts.controller;

import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.DtoGroup;
import com.murathnakts.dto.DtoGroupIU;

public interface IGroupController {
    public RootEntity<DtoGroup> createGroup(DtoGroupIU dtoGroupIU);
    public RootEntity<DtoGroup> addUserToGroup(Long userId, Long groupId);
    public RootEntity<DtoGroup> getGroup(Long groupId);
}
