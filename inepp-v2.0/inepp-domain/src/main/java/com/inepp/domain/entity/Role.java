package com.inepp.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色的实体类，对应role_tab
 */
@Entity
@Table(name = "role_tab")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer id;
    @Column(name="role_name")
    private String name;
    @Column(name="role_desc")
    private String desc;
    @Column(name="role_createby")
    private String createBy;
    @Column(name="role_createtime")
    private Date createTime;
}
