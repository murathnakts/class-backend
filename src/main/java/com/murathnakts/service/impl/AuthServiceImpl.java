package com.murathnakts.service.impl;

import com.murathnakts.dto.*;
import com.murathnakts.entity.RefreshToken;
import com.murathnakts.entity.User;
import com.murathnakts.exception.BaseException;
import com.murathnakts.exception.ErrorMessage;
import com.murathnakts.enums.MessageType;
import com.murathnakts.jwt.JwtService;
import com.murathnakts.repository.RefreshTokenRepository;
import com.murathnakts.repository.UserRepository;
import com.murathnakts.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private User findUserOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, username)));
    }

    private Boolean isUsernameAvailable(String username) {
        return userRepository.existsByUsername(username);
    }


    private User createUser(DtoUserIU dtoUserIU) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(dtoUserIU.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dtoUserIU.getPassword()));
        user.setRole(dtoUserIU.getRole());
        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public DtoUser register(DtoUserIU dtoUserIU) {
        if (isUsernameAvailable(dtoUserIU.getUsername())) {
            throw new BaseException(new ErrorMessage(MessageType.USER_ALREADY_REGISTERED, dtoUserIU.getUsername()));
        } else {
            DtoUser user = new DtoUser();
            User savedUser = userRepository.save(createUser(dtoUserIU));
            BeanUtils.copyProperties(savedUser, user);
            return user;
        }
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(authenticationToken);
            User user = findUserOrThrow(request.getUsername());
            String accessToken = jwtService.generateToken(user, user.getId());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, null));
        }
    }

    public boolean isValidRefreshToken(Date expiredDate) {
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
        }
        if (!isValidRefreshToken(optional.get().getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_EXPIRED, request.getRefreshToken()));
        }
        User user = optional.get().getUser();
        String accessToken = jwtService.generateToken(user, user.getId());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }
}
