package com.inepp.domain.dao;

import com.inepp.domain.entity.Residents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResidentsDao extends JpaRepository<Residents,Integer> {
}
