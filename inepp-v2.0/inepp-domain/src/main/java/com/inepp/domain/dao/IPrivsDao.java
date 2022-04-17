package com.inepp.domain.dao;

import com.inepp.domain.entity.Privs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrivsDao extends JpaRepository<Privs,Integer> {
}
