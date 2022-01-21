package me.liberty.ddd.common.dal.dos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 10:36
 */
@Entity
@Table(name = "rule")
public class RuleDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column
    private String policyId;

    @Getter
    @Setter
    @Column
    private String resource;

    @Getter
    @Setter
    @Column
    private String effect;

    @Getter
    @Setter
    @Column
    private String action;
}
