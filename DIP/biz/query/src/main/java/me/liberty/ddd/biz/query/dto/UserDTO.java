package me.liberty.ddd.biz.query.dto;

import lombok.Getter;

import java.util.Date;
import java.util.List;

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
    private List<String> systemPermPolicies;
    @Getter
    private Date gmtCreated;
    @Getter
    private Date gmtModified;

    public UserDTO(String accountId,
                   String nickName,
                   String phoneNo,
                   String password,
                   List<String> systemPermPolicies,
                   Date gmtCreated,
                   Date gmtModified) {
        this.accountId = accountId;
        this.nickName = nickName;
        this.phoneNo = phoneNo;
        this.password = password;
        this.systemPermPolicies = systemPermPolicies;
        this.gmtCreated = gmtCreated;
        this.gmtModified = gmtModified;
    }
}
