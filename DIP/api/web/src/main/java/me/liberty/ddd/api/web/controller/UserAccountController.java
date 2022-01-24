package me.liberty.ddd.api.web.controller;

import lombok.Data;
import me.liberty.ddd.biz.service.account.UserAccountApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 19:02
 */
//@RestController
public class UserAccountController {

//    @Autowired
//    UserAccountApplicationService userAccountApplicationService;
//
//    @RequestMapping(value = "account/user", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
//        String accountId = userAccountApplicationService.createAccount(
//                createAccountRequest.getNickName(),
//                createAccountRequest.getPhoneNo(),
//                createAccountRequest.getPassword());
//
//        HashMap<String, String> result = new HashMap<>();
//        result.put("accountId", accountId);
//        return result;
//    }
//
//    @Data
//    public static class CreateAccountRequest {
//
//        private String nickName;
//
//        private String phoneNo;
//
//        private String password;
//    }

}
