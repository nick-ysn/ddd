package me.liberty.ddd.model.entity;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author lanjue
 * @version $Id: me.liberty.ddd.model.sprint, v 0.1 4/8/16
 *          Exp $
 */
public class SprintId {

    @Getter
    private String id;

    public SprintId(String id) {
        this.setId(id);
    }

    private void setId(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("sprint's id isn't allowed blank");
        }
        this.id = id;
    }
}
