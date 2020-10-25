package com.zero.sys.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.common.jwt.peoperty.JwtProperties;
import com.zero.common.response.domain.ResponseData;
import com.zero.common.response.util.ResponseUtils;
import com.zero.sys.security.jwt.util.JwtUtils;
import com.zero.sys.security.userdetails.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 登录成功的处理器
 *
 * @author herenpeng
 * @since 2020-09-13 15:27
 */
@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ResponseUtils responseUtils;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        // 不应该把密码放入JWT的载荷中
        String tokenId = UUID.randomUUID().toString();
        String subject = objectMapper.writeValueAsString(myUserDetails.getUser());
        // 创建JWT
        String jwt = jwtUtils.createJWT(tokenId, subject);
        // 将jwt存放入redis中
        redisTemplate.opsForHash().put(jwtProperties.getName(), tokenId, jwt);
        ResponseData<Object> responseData = ResponseData.ok(jwt);
        responseUtils.responseJson(response, responseData);
    }
}
