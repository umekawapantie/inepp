package com.inepp.security.service.interceptor;

import com.auth0.jwt.JWT;
import com.inepp.common.dto.RespEnum;
import com.inepp.common.exception.security.HasNotLoginException;
import com.inepp.common.exception.security.HostUnauthorizedException;
import com.inepp.domain.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if(token==null|| "".equals(token)) { //用户还没有登录
            throw  new HasNotLoginException(RespEnum.HAS_NOT_LOGIN_ERROR.getMsg());
        }

        String username = JWT.decode(token).getAudience().get(0);

        Account account = (Account) redisTemplate.opsForValue().get(token);

        if(account==null)
            throw new HasNotLoginException("登录信息已经过期，请重新登录");

        if(!username.equals(account.getUsername())){
            throw new HasNotLoginException(RespEnum.HAS_NOT_LOGIN_ERROR.getMsg());
        }

        int roleId = account.getRoleId();
        String key = "role_"+roleId;


        HandlerMethod hm = (HandlerMethod) handler;
        log.debug("方法名称-->:   " + hm.getMethod().getName());


        if(!stringRedisTemplate.opsForSet().isMember(key,hm.getMethod().getName())){
            throw new HostUnauthorizedException(RespEnum.HOST_UNAUTHORIZED.getMsg());
        }


        //如果用户登录成功，检查其权限，是否能够进行此操作
        return HandlerInterceptor.super.preHandle(request, response, handler);


    }
}
