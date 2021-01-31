package com.zero.auth.security.handler;

import com.zero.common.constant.StringConst;
import com.zero.common.response.domain.ResponseData;
import com.zero.auth.util.RequestUtils;
import com.zero.auth.util.ResponseUtils;
import com.zero.auth.security.jwt.properties.JwtProperties;
import com.zero.auth.security.jwt.util.JwtUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录退出处理器类
 *
 * @author herenpeng
 * @since 2020-09-13 15:25
 */
@Component
public class MyLogoutHandler implements LogoutHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ResponseUtils responseUtils;

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = requestUtils.getToken(request);
        String tokenId = jwtUtils.getId(token);
        String tokenRedisKey = jwtProperties.getKey() + StringConst.COLON + tokenId;
        redisTemplate.delete(tokenRedisKey);
        ResponseData<Object> responseData = ResponseData.ok().message("退出成功");
        responseUtils.responseJson(response, responseData);
    }
}