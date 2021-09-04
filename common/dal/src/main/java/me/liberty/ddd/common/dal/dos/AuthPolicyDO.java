package me.liberty.ddd.common.dal.dos;

import lombok.Getter;
import lombok.Setter;
import me.liberty.ddd.common.util.enums.PolicyStatus;

import javax.persistence.*;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 10:44
 */
@Entity
@Table(name = "auth_policy")
public class AuthPolicyDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "policy_id")
    private String policyId;

    @Column(name = "account_id")
    @Getter
    private String accountId;

    @Column
    @Getter
    @Setter
    private PolicyStatus status;
}
