package com.inepp.service.admin.service.impl;

import com.inepp.domain.dao.IGrantPrivsDao;
import com.inepp.domain.dao.IPrivsDao;
import com.inepp.domain.dao.IRoleDao;
import com.inepp.domain.entity.GrantPrivs;
import com.inepp.domain.entity.Privs;
import com.inepp.domain.entity.Role;
import com.inepp.service.admin.service.IAdminService;
import com.inepp.service.admin.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

    private  IRoleDao roleDao;
    private IPrivsDao privsDao;
    private IGrantPrivsDao gpDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

      @Autowired
    public AdminServiceImpl(IRoleDao roleDao, IPrivsDao privsDao, IGrantPrivsDao gpDao) {
        this.roleDao = roleDao;
        this.privsDao = privsDao;
        this.gpDao = gpDao;
    }

    @Override
    public List<Role> loadRoleFromExcel(InputStream in) {
        return new ExcelHelper<Role>(in).parse("role",Role.class);
    }

    @Override
    public List<Privs> loadPrivsFromExcel(InputStream in) {
        return new ExcelHelper<Privs>(in).parse("privs",Privs.class);
    }

    @Override
    public List<GrantPrivs> loadGPFromExcel(InputStream in) {
        return new ExcelHelper<GrantPrivs>(in).parse("gp",GrantPrivs.class);
    }

    @Override
    public void addRolesFromExcel(List<Role> list) {
        for (Role role : list) {
            roleDao.save(role);
        }
    }

    @Override
    public void addPrivsFromExcel(List<Privs> list) {
        for (Privs privs : list) {
            privsDao.save(privs);
        }
    }

    @Override
    public void addGPFromExcel(List<GrantPrivs> list) {
        for (GrantPrivs grantPrivs : list) {
            gpDao.save(grantPrivs);
        }
    }

    @Override
    public void preLoadAuthority() {
        List<GrantPrivs> all = gpDao.findAll();
        for (GrantPrivs gp : all) {
         int roleId = gp.getRoleId();
         int privsId = gp.getPrivsId();

            Privs privs = privsDao.getById(privsId);

            stringRedisTemplate.opsForSet().add("role_"+roleId,
                    privs.getName());

        }

       
    }
}
