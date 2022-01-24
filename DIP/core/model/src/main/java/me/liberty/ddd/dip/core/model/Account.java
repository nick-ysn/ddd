package me.liberty.ddd.dip.core.model;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.AccountStatus;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public abstract class Account {

    @Getter
    private String accountId;
    @Getter
    private AccountStatus status;

    public Account() {
        this.accountId = UUID.randomUUID().toString();
        this.status = AccountStatus.ASSIGNED;
    }

    public boolean isLocked() {
        return this.getStatus() == AccountStatus.LOCKED;
    }

    public void assigned() {
        this.status = AccountStatus.ASSIGNED;
    }


    public static class UserAccount extends Account {

        @Getter
        private String nickName;
        @Getter
        private String phoneNo;
        @Getter
        private String password;

        public UserAccount(String nickName, String phoneNo, String password) {
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

    public static class ApiAccount extends Account {
        @Getter
        private String accessKeyId;
        @Getter
        private String accessSecretKey;

        public ApiAccount() {
            super();
            this.accessKeyId = accessKeyId;
            this.accessSecretKey = accessSecretKey;
        }
    }
}
