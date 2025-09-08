package com.murathnakts.controller;

import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.AuthRequest;
import com.murathnakts.dto.AuthResponse;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.RefreshTokenRequest;

public interface IAuthController {

    public RootEntity<DtoUser> register(AuthRequest request);

    public RootEntity<AuthResponse> login(AuthRequest request);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
