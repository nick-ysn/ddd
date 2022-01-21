package me.liberty.ddd.biz.query.service;

import me.liberty.ddd.biz.query.dto.UserDTO;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-10-04 11:24
 */
public interface UserQueryService {
    UserDTO queryBaseInfo(String accountId);
}
