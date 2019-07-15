import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.scw.manager.bean.TUser;
import com.atguigu.scw.manager.dao.TUserMapper;
import com.atguigu.scw.manager.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-beans.xml"}) //����spring�����ļ�������IOC����
public class WebTest {

	@Autowired
	TUserMapper userMapper;
	@Autowired
	UserService userService;
	@Test
	public void test() {
	    //System.out.println(userService.getUserById(1));
	    System.out.println(userMapper.selectByUserAccout("dfa"));
	}
}
