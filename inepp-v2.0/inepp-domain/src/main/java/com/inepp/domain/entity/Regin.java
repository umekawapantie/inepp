package com.inepp.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 *  中国行政区的实体类,映射regin_tab表
 */
@Entity
@Table(name = "regin_tab")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Regin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regin_id")
    private Integer id;
    @Column(name = "regin_name")
    private String name;
    @Column(name = "regin_pid")
    private Integer pid;
}
