package com.murathnakts.service;

import com.murathnakts.dto.AuthRequest;
import com.murathnakts.dto.AuthResponse;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.RefreshTokenRequest;

public interface IAuthService {
    public DtoUser register(AuthRequest request);
    public AuthResponse login(AuthRequest request);
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
