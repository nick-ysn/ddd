package me.liberty.ddd.repository.impl;

import me.liberty.ddd.core.model.SystemPermPolicy;
import me.liberty.ddd.repository.SystemPermPolicyRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 14:35
 */
@Repository
public class SystemPermPolicyRepositoryImpl implements SystemPermPolicyRepository {

    @Override
    public void save(SystemPermPolicy systemPermPolicy) {

    }

    @Override
    public boolean hasPermPolicyOfName(String name) {
        return false;
    }

    @Override
    public SystemPermPolicy permPolicyOfName(String policyId) {
        return null;
    }
}
