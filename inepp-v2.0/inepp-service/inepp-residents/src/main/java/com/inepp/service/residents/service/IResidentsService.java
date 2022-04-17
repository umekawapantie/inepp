package com.inepp.service.residents.service;

import com.inepp.domain.entity.Residents;

public interface IResidentsService {
    /**
     * 居民注册，使用电话号码作为用户名
     * @param residents
     */
    void registry(Residents residents);

    void changePass(String username, String newPassword);
    /**
     * 加载数据库中的用户名到缓存中
     */
    void loadAllUsernames();



}
