package me.bliss.ddd.transaction.script.dao;

import me.bliss.ddd.transaction.script.dos.CustomerDO;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.dao, v 0.1 3/11/16
 *          Exp $
 */
public interface CustomerDAO {

    void update(CustomerDO customerDO);

    CustomerDO customerOfName(String name);
}
