package com.inepp.domain.dao;

import com.inepp.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IRoleDao extends JpaRepository<Role,Integer> {
}
