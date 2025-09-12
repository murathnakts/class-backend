package com.murathnakts.jwt;

import com.murathnakts.enums.MessageType;
import com.murathnakts.exception.BaseException;
import com.murathnakts.exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private JwtService jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader("Authorization");

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);

                if (jwtTokenProvider.isTokenValid(token)) {
                    String username = jwtTokenProvider.getUsernameByToken(token);
                    accessor.setUser(new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of() // roller eklenebilir
                    ));
                } else {
                    throw new BaseException(new ErrorMessage(MessageType.TOKEN_EXPIRED, token));
                }
            } else {
                throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, null));
            }
        }
        return message;
    }
}

