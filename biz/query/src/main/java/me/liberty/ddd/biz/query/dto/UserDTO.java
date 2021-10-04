package me.liberty.ddd.biz.query.dto;

import lombok.Getter;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-16 11:12
 */
public class UserDTO {

    @Getter
    private String accountId;
    @Getter
    private String nickName;
    @Getter
    private String phoneNo;
    @Getter
    private String password;
    @Getter
    private String[] systemPermPolicies;
    @Getter
    private String gmtCreated;
    @Getter
    private String gmtModified;


}
