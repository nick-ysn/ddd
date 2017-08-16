package me.liberty.ddd.factory;

import me.liberty.ddd.model.entity.SprintEntity;
import me.liberty.ddd.model.entity.SprintId;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author liberty
 * @email yuanshouna@gmail.com
 * @version $Id: me.liberty.ddd.factory, v 0.1 15/08/2017
 *          Exp $
 */
public class SprintFactory {

    public static SprintEntity create(String name) {
        final SprintId sprintId = new SprintId(RandomStringUtils.random(10, false, true));
        return new SprintEntity(sprintId,name);
    }
}
