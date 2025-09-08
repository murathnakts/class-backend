package com.murathnakts.controller;

import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.AuthRequest;
import com.murathnakts.dto.DtoUser;

public interface IAuthController {
    public RootEntity<DtoUser> register(AuthRequest request);
}
