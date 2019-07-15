import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.scw.manager.bean.TUser;
import com.atguigu.scw.manager.bean.TUserExample;
import com.atguigu.scw.manager.bean.TUserExample.Criteria;
import com.atguigu.scw.manager.dao.TUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ServiceTest {

	@Autowired
	TUserMapper userMapper;

	@Test
	public void test() {
		TUser user = new TUser();
		user.setUsername("123");
		user.setUserpswd("123456");
		user.setEmail("hpf@123.com");
		//TUser user2 =  userMapper.selectByPrimaryKey(1);
	    System.out.println(userMapper);

	}
	
	@Test
	public void test02() {
		TUserExample example = new TUserExample();
		TUser user = new TUser();
		user.setUsername("hpfhpf");
		user.setLoginacct("hpfhpf");
		Criteria criteria = example.createCriteria();
		criteria.andUsernameLike("1");
		
		List<TUser> list = userMapper.selectByExample(null);
		System.out.println(list);
	}

}
