package com.inepp.service.admin.controller;

import com.inepp.common.dto.HttpResp;
import com.inepp.common.dto.RespEnum;
import com.inepp.domain.entity.GrantPrivs;
import com.inepp.domain.entity.Privs;
import com.inepp.domain.entity.Role;
import com.inepp.service.admin.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;

@Api(tags = "管理员模块")
@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Autowired
    private IAdminService ias;


    @PostMapping("/loadRolesFromExcel")
    @SneakyThrows
    public HttpResp loadRolesFromExcel(MultipartFile file) {
        List<Role> list = ias.loadRoleFromExcel(file.getInputStream());
        log.debug("roles:::" + list);
        ias.addRolesFromExcel(list);
        return new HttpResp(RespEnum.LOAD_ROLE_FROM_EXCEL.getCode(),
                RespEnum.LOAD_ROLE_FROM_EXCEL.getMsg(),list,
                LocalDate.now());
    }
    @PostMapping("/loadPrivsFromExcel")
    @SneakyThrows
    public HttpResp loadPrivsFromExcel(MultipartFile file) {
        List<Privs> list = ias.loadPrivsFromExcel(file.getInputStream());
        ias.addPrivsFromExcel(list);
        return new HttpResp(RespEnum.LOAD_PRIVS_FROM_EXCEL.getCode(),

                RespEnum.LOAD_PRIVS_FROM_EXCEL.getMsg(),
                list,
                        LocalDate.now());
    }
    @PostMapping("/loadGPFromExcel")
    @SneakyThrows
    public HttpResp loadGPFromExcel(MultipartFile file) {
        List<GrantPrivs> list = ias.loadGPFromExcel(file.getInputStream());
        ias.addGPFromExcel(list);
        return new HttpResp(RespEnum.LOAD_GP_FROM_EXCEL.getCode(),
                RespEnum.LOAD_GP_FROM_EXCEL.getMsg(),
                list,
                        LocalDate.now());
    }
}
