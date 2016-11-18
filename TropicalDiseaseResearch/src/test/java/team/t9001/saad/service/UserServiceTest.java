package team.t9001.saad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * desc:
 * Created by huangzhe on 2016/11/15.
 */
@ContextConfiguration(locations = "file:src/main/resources/application.xml")
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private UserService userService;

    @Test
    public void testSay(){
//        userService.get();
    }
}
