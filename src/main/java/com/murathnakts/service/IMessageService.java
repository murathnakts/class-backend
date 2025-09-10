package com.murathnakts.service;

import com.murathnakts.dto.DtoMessage;
import com.murathnakts.dto.DtoMessageIU;

public interface IMessageService {
    public DtoMessage saveMessage(Long groupId,DtoMessageIU dtoMessageIU);
}
