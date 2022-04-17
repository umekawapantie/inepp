package com.inepp.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 权限的实体类,映射privs_tab表
 */
@Entity
@Table(name = "privs_tab")
@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Privs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="privs_id")
    private Integer id;
    @Column(name="privs_name")
    private String name;
    @Column(name="privs_desc")
    private String desc;
    @Column(name="privs_createby")
    private String createBy;
    @Column(name="privs_createtime")
    private Date createTime;
}
