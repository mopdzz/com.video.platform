import com.video.platform.entity.User;
import com.video.platform.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: yangl
 * Date: 13-7-27 下午6:28
 */

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserDaoTest {
    @Autowired
    private UserDao userMybatisDao;

    @Test
    public void getUser() throws Exception {
        User user = userMybatisDao.get(1L);
        System.out.println(user);
        assertNotNull("User not found", user);
        assertEquals("admin", user.getLoginName());
    }

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setLoginName("loginName");
        userMybatisDao.save(user);
    }

    @Test
    public void delete() {
        userMybatisDao.delete(4L);
    }

    @Test
    public void search(){
        List<User> list = userMybatisDao.search(null);
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println("--------------");
        System.out.println(userMybatisDao.findByLoginName("admin"));
    }
}
