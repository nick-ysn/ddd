package me.liberty.ddd.transaction.script;

import me.liberty.ddd.domain.model.Customer;
import me.liberty.ddd.domain.model.CustomerId;
import me.liberty.ddd.domain.repository.CustomerRepository;
import me.liberty.ddd.transaction.script.dao.CustomerDAO;
import me.liberty.ddd.transaction.script.dos.CustomerDO;
import me.liberty.ddd.transaction.script.model.Address;
import org.apache.commons.lang.StringUtils;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script, v 0.1 3/11/16
 *          Exp $
 */
public class CustomerService {

    private CustomerDAO customerDAO;

    private CustomerRepository customerRepository;

    public void update(String name, Address address, String phoneNo) {
        final CustomerDO customerDO = customerDAO.customerOfName(name);
        if (StringUtils.isNotBlank(address.getCity())){
            customerDO.setCity(address.getCity());
        }
        if (StringUtils.isNotBlank(address.getCountry())){
            customerDO.setCountry(address.getCountry());
        }
        if (StringUtils.isNotBlank(phoneNo)){
            customerDO.setPhoneNo(phoneNo);
        }
        customerDAO.update(customerDO);
    }

    public void updateAddress(CustomerId customerId,Address address){
        final Customer customer = customerRepository.customerOfId(customerId);
        customer.changeAddress(address);
    }

}
