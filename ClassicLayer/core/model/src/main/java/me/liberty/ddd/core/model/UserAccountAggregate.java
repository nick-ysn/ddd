package me.liberty.ddd.core.model;

import lombok.Getter;
import org.springframework.util.DigestUtils;

/**
 * @author yuanshouna@gmail.com
 * @created 2022-01-05 18:57
 */
public class UserAccountAggregate extends Account{

    @Getter
    private String nickName;
    @Getter
    private String phoneNo;
    @Getter
    private String password;
    @Getter
    private AuthPolicy[] authPolicies;

    public UserAccountAggregate(String nickName, String phoneNo, String password) {
        super();
        this.nickName = nickName;
        setPhoneNo(phoneNo);
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public void setPhoneNo(String phoneNo) {
        if (phoneNo.length() != 11 /* 各种手机号校验 */) {
            throw new RuntimeException("手机号格式错误");
        }
        this.phoneNo = phoneNo;
    }

}
