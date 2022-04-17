package com.inepp.domain.dao;

import com.inepp.domain.entity.Regin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReginDao extends JpaRepository<Regin,Integer> {
}
