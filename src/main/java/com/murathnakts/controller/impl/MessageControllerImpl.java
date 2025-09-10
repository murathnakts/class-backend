package com.murathnakts.controller.impl;

import com.murathnakts.controller.IMessageController;
import com.murathnakts.controller.base.BaseController;
import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.DtoMessage;
import com.murathnakts.dto.DtoMessageIU;
import com.murathnakts.service.IMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageControllerImpl extends BaseController implements IMessageController {

    @Autowired
    private IMessageService messageService;

    @PostMapping("/send")
    @Override
    public RootEntity<DtoMessage> saveMessage(@Valid @RequestBody DtoMessageIU dtoMessageIU) {
        return success(messageService.saveMessage(dtoMessageIU));
    }
}
