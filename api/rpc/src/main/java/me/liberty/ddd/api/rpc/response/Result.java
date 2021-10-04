package me.liberty.ddd.api.rpc.response;

import lombok.Getter;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-10-03 13:19
 */
public class Result<T> {

    @Getter
    private boolean success;

    @Getter
    private T data;

    @Getter
    private String errorCode;

    @Getter
    private String errorMsg;

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success, String errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
