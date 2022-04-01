package me.liberty.ddd.dip.repository;

import me.liberty.ddd.dip.core.model.AuthPolicy;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:43
 */
public interface AuthPolicyRepository {

    void save(AuthPolicy authPolicy);

    boolean hasAuthPolicy(String accountId);

    AuthPolicy authPolicyOfAccount(String accountId);
}
