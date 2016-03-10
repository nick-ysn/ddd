package me.bliss.ddd.transaction.script.test;

import me.bliss.ddd.transaction.script.PersonService;
import me.bliss.ddd.transaction.script.model.Person;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transaction.script.test, v 0.1 2/26/16
 *          Exp $
 */
public class PersonServiceTest {

    private PersonService personService;

    @BeforeClass
    public void setUp() {
        personService = new PersonService();
    }

    @Test
    public void testCreate() throws Exception {
        personService.create(new Person("shouna", 24));
    }

    @Test
    public void testChangeAge() throws Exception {
        personService.changeAge(new Person("shouna", 25));
    }

    @Test
    public void testChangeAgeOfNotDAO() throws Exception {
        personService.changeAgeOfNotDAO(new Person("shouna",26));
    }
}
