package com.murathnakts.controller.impl;

import com.murathnakts.controller.IGroupController;
import com.murathnakts.controller.base.BaseController;
import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.DtoGroup;
import com.murathnakts.dto.DtoGroupIU;
import com.murathnakts.service.IGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupControllerImpl extends BaseController implements IGroupController {

    @Autowired
    private IGroupService groupService;

    @PostMapping("/create")
    @Override
    public RootEntity<DtoGroup> createGroup(@Valid @RequestBody DtoGroupIU dtoGroupIU) {
        return success(groupService.createGroup(dtoGroupIU));
    }

    @GetMapping("/add-member")
    @Override
    public RootEntity<DtoGroup> addUserToGroup(@RequestParam(name = "userId", required = true) Long userId,
                                              @RequestParam(name = "groupId", required = true) Long groupId) {
        //Request Param için bir hata maplemesi yok
        return success(groupService.addUserToGroup(userId, groupId));
    }

    @GetMapping("/{id}")
    @Override
    public RootEntity<DtoGroup> getGroup(@PathVariable(value = "id", required = true) Long groupId) {
        return success(groupService.getGroup(groupId));
    }
}
