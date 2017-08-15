package me.liberty.ddd.domain.repository;

import me.liberty.ddd.domain.model.Customer;
import me.liberty.ddd.domain.model.CustomerId;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.domain.repository, v 0.1 3/11/16
 *          Exp $
 */
public interface CustomerRepository {

    Customer customerOfId(CustomerId customerId);
}
