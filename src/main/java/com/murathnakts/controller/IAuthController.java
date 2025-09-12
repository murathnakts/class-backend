package com.murathnakts.controller;

import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.*;

public interface IAuthController {
    public RootEntity<DtoUser> register(DtoUserIU dtoUserIU);
    public RootEntity<AuthResponse> login(AuthRequest request);
    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
