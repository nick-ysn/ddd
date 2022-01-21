package me.liberty.ddd.core.model;

import lombok.Getter;

/**
 * @author yuanshouna@gmail.com
 * @created 2022-01-06 18:00
 */
public class AnticorrosDemo {

    @Getter
    private ASystemRequest aSystemRequest;

    @Getter
    private BSystemEnum bSystemEnum;


    static class ASystemRequest {

    }

    static enum BSystemEnum {

    }
}

