package com.inepp.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gp_tab")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GrantPrivs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gp_id")
    private Integer id;
    @Column(name="gp_role")
    private Integer roleId;
    @Column(name="gp_privs")
    private Integer privsId;
    @Column(name="gp_createby")
    private String createBy;
    @Column(name="gp_createtime")
    private Date createTime;

}
