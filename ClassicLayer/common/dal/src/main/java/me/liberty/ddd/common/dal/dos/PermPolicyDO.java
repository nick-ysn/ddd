package me.liberty.ddd.common.dal.dos;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.PolicyStatus;

import javax.persistence.*;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 10:40
 */
@Entity
@Table(name = "perm_policy")
public class PermPolicyDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "policy_id")
    private String policyId;

    @Column
    @Getter
    private  String name;

    @Column(name = "description")
    @Getter
    private String desc;

    @Column
    @Getter
    private PolicyStatus status;
}
