package com.murathnakts.service.impl;

import com.murathnakts.dto.AuthRequest;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.entity.User;
import com.murathnakts.repository.UserRepository;
import com.murathnakts.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    private User createUser(AuthRequest authRequest) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword());
        return user;
    }

    @Override
    public DtoUser register(AuthRequest request) {
        DtoUser user = new DtoUser();
        User savedUser = userRepository.save(createUser(request));
        BeanUtils.copyProperties(savedUser, user);
        return user;
    }
}
