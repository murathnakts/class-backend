package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoMessage;
import com.murathnakts.dto.DtoMessageIU;
import com.murathnakts.entity.Group;
import com.murathnakts.entity.Message;
import com.murathnakts.enums.MessageType;
import com.murathnakts.exception.BaseException;
import com.murathnakts.exception.ErrorMessage;
import com.murathnakts.repository.GroupRepository;
import com.murathnakts.repository.MessageRepository;
import com.murathnakts.service.IMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MessageRepository messageRepository;

    private Message createMessage(Long groupId,DtoMessageIU dtoMessageIU) {
        Optional<Group> optGroup = groupRepository.findById(groupId);
        if (optGroup.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.GROUP_NOT_FOUND, groupId.toString()));
        }
        Message message = new Message();
        message.setCreateTime(new Date());
        message.setSenderId(dtoMessageIU.getSenderId());
        message.setContent(dtoMessageIU.getContent());
        message.setGroup(optGroup.get());
        return message;
    }

    @Override
    public DtoMessage saveMessage(Long groupId,DtoMessageIU dtoMessageIU) {
        DtoMessage dtoMessage = new DtoMessage(); //Burada bir de user grupta mı diye kontrol et
        Message newMessage = messageRepository.save(createMessage(groupId,dtoMessageIU));
        BeanUtils.copyProperties(newMessage, dtoMessage);
        return dtoMessage;
    }
}
