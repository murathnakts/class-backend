package com.murathnakts.controller.impl;

import com.murathnakts.controller.IMessageController;
import com.murathnakts.controller.base.BaseController;
import com.murathnakts.dto.DtoMessage;
import com.murathnakts.dto.DtoMessageIU;
import com.murathnakts.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageControllerImpl extends BaseController implements IMessageController {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send/{groupId}")
    @Override
    public void sendMessage(@DestinationVariable Long groupId, DtoMessageIU dtoMessageIU) {
        DtoMessage dtoMessage = messageService.saveMessage(groupId, dtoMessageIU);
        messagingTemplate.convertAndSend("/topic/group/" + groupId,  dtoMessage);
    }
}
