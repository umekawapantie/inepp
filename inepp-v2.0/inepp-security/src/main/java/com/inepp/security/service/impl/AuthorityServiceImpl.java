package com.inepp.security.service.impl;

import com.inepp.common.dto.RespEnum;
import com.inepp.common.exception.security.EmptyAccountException;
import com.inepp.common.exception.security.IncorrectCredentialsException;
import com.inepp.common.exception.security.UnknownAccountException;
import com.inepp.common.util.EncryptionUtil;
import com.inepp.domain.entity.GrantPrivs;
import com.inepp.domain.dao.IAccountDao;
import com.inepp.domain.dao.IGrantPrivsDao;
import com.inepp.domain.dao.IPrivsDao;
import com.inepp.domain.dao.IRoleDao;
import com.inepp.domain.entity.Account;
import com.inepp.domain.entity.Privs;
import com.inepp.domain.entity.Role;
import com.inepp.security.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AuthorityServiceImpl implements IAuthorityService {

    private IAccountDao accountDao;

    private IPrivsDao privsDao;

    private IRoleDao roleDao;

    private IGrantPrivsDao gpDao;



    @Autowired
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Autowired
    public void setPrivsDao(IPrivsDao privsDao) {
        this.privsDao = privsDao;
    }

    @Autowired
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setGpDao(IGrantPrivsDao gpDao) {
        this.gpDao = gpDao;
    }

    @Override
    public void createRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public void createPrivs(Privs privs) {
        privsDao.save(privs);
    }

    @Override
    public void grantPrivsToRole(int roleId, int privsId) {
        GrantPrivs gp = new GrantPrivs();
        gp.setPrivsId(privsId);
        gp.setRoleId(roleId);
        gp.setCreateTime(new Date());

        gpDao.save(gp);
    }

    @Override
    public void grantRoleToAccount(int accountId, int roleId) {
        Account account = accountDao.findById(accountId).get();
        account.setRoleId(roleId);
    }

    @Override
    public Account login(String username, String password) {
        if (username == null || "".equals(username))
            throw new EmptyAccountException(RespEnum.EMPTY_ACCOUNT.getMsg());
        Account account = accountDao.findByUsername(username);


        if (account == null)
            throw new UnknownAccountException(RespEnum.UNKNOWN_ACCOUNT.getMsg());

        if (!EncryptionUtil.getInstance().encryt(password).equals(account.getPassword()))
            throw new IncorrectCredentialsException(RespEnum.INCORRECT_CREDENTIALS.getMsg());

        return account;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }

}
