package com.inepp.domain.dao;

import com.inepp.domain.entity.HealthTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHealthTrackDao extends JpaRepository<HealthTrack,Integer> {
}
