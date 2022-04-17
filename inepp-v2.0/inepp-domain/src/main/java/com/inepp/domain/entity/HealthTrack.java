package com.inepp.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ht_tab")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HealthTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ht_id")
    private  Integer id;
    @Column(name="ht_nat")
    private String nat;
    @Column(name="ht_temp")
    private Double temp;
    @Column(name="ht_regin")
    private String regin;
    @Column(name="ht_from_regin")
    private String fromRegin;
    @Column(name="ht_to_regin")
    private String toRegin;
    @Column(name="ht_date")
    private Date date;
    @Column(name="ht_residents")
    private Integer residentsId;
    @Column(name="ht_createby")
    private String createBy;
    @Column(name="ht_createtime")
    private Date createTime;
}
