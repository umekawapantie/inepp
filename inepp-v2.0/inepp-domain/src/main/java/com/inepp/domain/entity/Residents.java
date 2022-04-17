package com.inepp.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@ApiModel(description = "居民信息对象")
@Entity
@Table(name = "residents_tab")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Residents implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="residents_id")
    private Integer id;
    @Column(name="residents_name")
    private String name;
    @Column(name="residents_gender")
    private String gender;
    @Column(name="residents_age")
    private Integer age;

    @ApiModelProperty(value = "居民电话号码(用来做用户名)",example = "1234567",required = true)
    @Column(name="residents_phone")
    private String phone;
    @Column(name="residents_identity")
    private String identity;
    @Column(name="residents_regin")
    private String regin;//当前所在行政区
    @Column(name="residents_vaccines")
    private String vaccines; //疫苗接种
    @Column(name="residents_overseas_arrival")
    private String overseasArrival;
    @Column(name="residents_health_code")
    private String healthCode; //健康码
    @Column(name="residents_dhsa")
    private String dhsa;//是否去过高风险
    @Column(name="residents_health_status")
    private String heathStatus;//健康状况
    @Column(name="residents_createby")
    private String createby;
    @Column(name="residents_ceratetime")
    private Date createtime;
}
