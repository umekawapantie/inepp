package com.inepp.domain.dao;

import com.inepp.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDao extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
    @Query(value = "SELECT account_username FROM account_tab",
            nativeQuery = true)
    String[] selectAllUsernames();
}
