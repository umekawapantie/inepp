package com.inepp.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account_tab")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer id;
    @Column(name = "account_username")
    private String username;
    @Column(name = "account_password")
    private String password;
    @Column(name = "account_role")
    private Integer roleId;
    @Column(name = "account_residents")
    private Integer residentsId;
    @Column(name="account_init_password")
    private String initPassword;
    @Column(name="account_createby")
    private String createBy;
    @Column(name="account_createtime")
    private Date createTime;

}
