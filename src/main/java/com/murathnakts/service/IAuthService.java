package com.murathnakts.service;

import com.murathnakts.dto.AuthRequest;
import com.murathnakts.dto.DtoUser;

public interface IAuthService {
    public DtoUser register(AuthRequest request);
}
