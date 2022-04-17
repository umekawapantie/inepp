package com.inepp.service.residents.service.impl;

import com.inepp.common.dto.RespEnum;
import com.inepp.common.exception.security.AccountAlreadyExistException;
import com.inepp.common.exception.security.EmptyAccountException;
import com.inepp.common.util.Constants;
import com.inepp.common.util.EncryptionUtil;
import com.inepp.domain.dao.IAccountDao;
import com.inepp.domain.dao.IResidentsDao;
import com.inepp.domain.entity.Account;
import com.inepp.domain.entity.Residents;
import com.inepp.service.residents.service.IResidentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Slf4j
public class ResidentsServiceImpl implements IResidentsService {

    private IResidentsDao residentsDao;
    private IAccountDao accountDao;


    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void loadAllUsernames() {
        String[] allUsernames = accountDao.selectAllUsernames();
        stringRedisTemplate.delete("usernames");
        stringRedisTemplate.opsForSet().add("usernames", allUsernames);
        log.debug("缓存预热成功,加载所有用户名到redis缓存中...");
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setResidentsDao(IResidentsDao residentsDao) {
        this.residentsDao = residentsDao;
    }

    @Autowired
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @JmsListener(destination = "active.queue")
    @Override
    public void registry(Residents residents) {

        if (residents == null || residents.getPhone() == null)
            throw new EmptyAccountException(RespEnum.EMPTY_ACCOUNT.getMsg());

        if (Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember("usernames", residents.getPhone())))
            throw new AccountAlreadyExistException(RespEnum.ACCOUNT_ALREADY_EXIST.getMsg());

        residentsDao.save(residents);
        residents.setCreateby(residents.getName());
        residents.setCreatetime(new Date());
        log.debug("居民注册系统成功....");
        Account account = new Account();
        account.setRoleId(Constants.DEFAULT_ROLE);//普通居民
        account.setResidentsId(residents.getId());
        account.setUsername(residents.getPhone());
        account.setPassword(EncryptionUtil.getInstance().encryt(Constants.DEFAULT_PASSWORD));
        account.setInitPassword(Constants.IS_INIT_PASSWORD); //是否为初始密码
        account.setCreateBy(residents.getName());
        account.setCreateTime(new Date());
        accountDao.save(account);
        log.debug("注册账号创建成功....");

        stringRedisTemplate.opsForSet().add("usernames",residents.getPhone());
        log.debug("缓存更新成功....");
    }

    @Override
    public void changePass(String username, String newPassword) {
        Account account = accountDao.findByUsername(username);
        account.setPassword(EncryptionUtil.getInstance().encryt(newPassword));
    }


}
