package com.murathnakts.controller;

import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.DtoMessage;
import com.murathnakts.dto.DtoMessageIU;

public interface IMessageController {
    public RootEntity<DtoMessage> saveMessage(DtoMessageIU dtoMessageIU);
}
