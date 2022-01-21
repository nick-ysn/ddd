package me.liberty.ddd.common.dal.dos;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-03 00:05
 */
@Entity
@Table(name = "user_account")
public class UserAccountDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Column(unique = true, name = "account_id")
    private String accountId;

    @Getter
    @Column(name = "nick_name")
    private String nickName;

    @Getter
    @Column(name = "phone_no", unique = true)
    private String phoneNo;

    @Getter
    @Column(name = "status")
    private AccountStatus status;

    @Getter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Getter
    @Column(name = "gmt_modified")
    private Date gmtModified;

    public UserAccountDO() {
    }

    public UserAccountDO(String nickName, String phoneNo, String password) {
        this.nickName = nickName;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public UserAccountDO(String accountId, String nickName, String phoneNo, AccountStatus status, String password) {
        this.accountId = accountId;
        this.nickName = nickName;
        this.phoneNo = phoneNo;
        this.status = status;
        this.password = password;
    }
}
