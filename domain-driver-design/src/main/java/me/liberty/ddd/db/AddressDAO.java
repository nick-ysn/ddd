package me.liberty.ddd.db;

import me.liberty.ddd.model.Address;

/**
 *
 *
 * @author shouna.ysn
 * @email shouna.ysn@alipay.com
 * @version $Id: me.liberty.ddd.db, v 0.1 16/08/2017
 *          Exp $
 */
public interface AddressDAO {

    void insert(Address address);

    Address queryById(String id);
}
