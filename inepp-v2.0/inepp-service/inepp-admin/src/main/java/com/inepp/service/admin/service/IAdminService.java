package com.inepp.service.admin.service;

import com.inepp.domain.entity.GrantPrivs;
import com.inepp.domain.entity.Privs;
import com.inepp.domain.entity.Role;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IAdminService {

    List<Role> loadRoleFromExcel(InputStream in);
    List<Privs> loadPrivsFromExcel(InputStream in);
    List<GrantPrivs> loadGPFromExcel(InputStream in);

    void addRolesFromExcel(List<Role> list);
    void addPrivsFromExcel(List<Privs> list);
    void addGPFromExcel(List<GrantPrivs> list);

  void preLoadAuthority();

}
