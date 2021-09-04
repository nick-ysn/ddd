package me.liberty.ddd.core.model;

import lombok.Getter;
import me.liberty.ddd.common.util.enums.PolicyStatus;

import java.util.UUID;

/**
 * @author yuanshouna@gmail.com
 * @created 2021-09-02 11:40
 */
public class SystemPermPolicy extends Policy {
    @Getter
    private String name;
    @Getter
    private String desc;
    @Getter
    private Rule[] rules;

    public SystemPermPolicy(String name, String desc, Rule[] rules) {
        super();
        this.name = name;
        this.desc = desc;
        this.rules = rules;
    }

}
