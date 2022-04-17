package com.inepp.service.residents.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.inepp.common.dto.HttpResp;
import com.inepp.common.dto.RespEnum;
import com.inepp.common.util.EncryptionUtil;
import com.inepp.domain.entity.Account;
import com.inepp.domain.entity.Residents;
import com.inepp.security.service.IAuthorityService;
import com.inepp.service.residents.service.IResidentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Api(tags = "居民信息管理模块")
@RestController
@RequestMapping("/api/residents")
@Slf4j
public class ResidentsController {

    private IResidentsService irs;
    private RedisTemplate<String, Object> redisTemplate;
    private IAuthorityService ias;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    public void setIas(IAuthorityService ias) {
        this.ias = ias;
    }

    @Autowired
    public void setIrs(IResidentsService irs) {
        this.irs = irs;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation( "居民注册平台")
    @PutMapping("/registry")
    public HttpResp registry(Residents residents) {

       // irs.registry(residents);
        jmsMessagingTemplate.convertAndSend(queue,residents);

        return new HttpResp(RespEnum.REGISTRY_SUCCESS.getCode(),
                RespEnum.REGISTRY_SUCCESS.getMsg(), null, LocalDate.now());

    }

    @ApiOperation( "修改密码,需要知道修改哪个账户的密码，用户名信息必须获取")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="password",required = true),
            @ApiImplicitParam(paramType = "header",name="token",required = true)
    }
    )
    @PostMapping("/changePassword")
    public HttpResp changePassword(String password, HttpServletRequest request) {

        String token = request.getHeader("token");
        String username = JWT.decode(token).getAudience().get(0);
        irs.changePass(username, password);
        log.debug("账号:" + username + "修改密码成功....");
        Account account = (Account) redisTemplate.opsForValue().get(token);
        account.setPassword(EncryptionUtil.getInstance().encryt(password));
        redisTemplate.opsForValue().set(token,account);

        log.debug("缓存更新密码完成.....");
        return new HttpResp(RespEnum.CHANGE_PASSWORD_SUCCESS.getCode(), RespEnum.CHANGE_PASSWORD_SUCCESS.getMsg(), null, LocalDate.now());
    }

    @ApiOperation("用户登录，成功登录存储个人信息到session")
    @GetMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value = "用户名(电话号码)",dataTypeClass = String.class,example = "1234657"),
            @ApiImplicitParam(name="password",value = "密码",dataTypeClass = String.class,example = "123")
    })
    public HttpResp login(String username, String password, HttpServletResponse response) {
        Account account = ias.login(username, password); //(1) 验证用户名密码
        log.debug("用户:" + account.getUsername() + "登录成功");

        String token = JWT.create().withAudience(username).sign(Algorithm.HMAC256(password));//(2)唯一token
        log.debug("产生登录成功的token信息:[" + token+"]");
        response.addHeader("token",token);

        redisTemplate.opsForValue().set(token,account);//(3)存储在缓存中
        redisTemplate.expire(token,30, TimeUnit.MINUTES);

        return new HttpResp(RespEnum.LOGIN_SUCCESS.ordinal(), RespEnum.LOGIN_SUCCESS.getMsg(), null, LocalDate.now());
    }

    @ApiOperation("退出登录")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header",name="token",required = true)
    )
    @GetMapping("/logout")
    public HttpResp logout(HttpServletRequest request) {

        String token = request.getHeader("token");
        String username = JWT.decode(token).getAudience().get(0);

        log.debug("用户:" + username + "退出系统");

        redisTemplate.delete(token);
        log.debug("从缓存中清理失效的token......");
        return new HttpResp(RespEnum.LOGIN_OUT_SUCCESS.getCode(), RespEnum.LOGIN_OUT_SUCCESS.getMsg(), null, LocalDate.now());
    }
}
