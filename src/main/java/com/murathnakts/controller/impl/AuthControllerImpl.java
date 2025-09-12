package com.murathnakts.controller.impl;

import com.murathnakts.controller.base.BaseController;
import com.murathnakts.controller.IAuthController;
import com.murathnakts.controller.base.RootEntity;
import com.murathnakts.dto.*;
import com.murathnakts.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl extends BaseController implements IAuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody DtoUserIU dtoUserIU) {
        return success(authService.register(dtoUserIU));
    }

    @PostMapping("/login")
    @Override
    public RootEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return success(authService.login(request));
    }

    @PostMapping("/refresh-token")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return success(authService.refreshToken(request));
    }
}
