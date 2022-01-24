package me.liberty.ddd.api.rpc.request;


import lombok.Getter;
import lombok.Setter;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-10-03 13:16
 */
public class UserAccountRequest {

    public static class CreateAccountRequest {
        @Getter
        @Setter
        private String nickName;
        @Getter
        @Setter
        private String phoneNo;
        @Getter
        @Setter
        private String password;
    }
}
