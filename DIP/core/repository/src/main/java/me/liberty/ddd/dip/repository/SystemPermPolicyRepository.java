package me.liberty.ddd.dip.repository;

import me.liberty.ddd.dip.core.model.SystemPermPolicy;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:36
 */
public interface SystemPermPolicyRepository {

    void save(SystemPermPolicy systemPermPolicy);

    boolean hasPermPolicyOfName(String name);

    SystemPermPolicy permPolicyOfName(String policyId);
}
