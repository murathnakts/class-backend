package com.murathnakts.service;

import com.murathnakts.dto.*;

public interface IAuthService {
    public DtoUser register(DtoUserIU dtoUserIU);
    public AuthResponse login(AuthRequest request);
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
