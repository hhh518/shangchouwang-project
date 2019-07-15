package com.atguigu.scw.manager.service.impl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.project.MD5Util;
import com.atguigu.project.MyStringUtils;
import com.atguigu.scw.manager.bean.TUser;
import com.atguigu.scw.manager.bean.TUserExample;
import com.atguigu.scw.manager.bean.TUserExample.Criteria;
import com.atguigu.scw.manager.dao.TUserMapper;
import com.atguigu.scw.manager.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private TUserMapper userMapper;
	
	public TUser getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}	
	public boolean register(TUser user) {
        if (userMapper.selectByUserAccout(user.getLoginacct()) != null) {
        	//如果已经存在了用户账号，则注册失败
			return false;
		}
        int i = 0;
		//String crypt = Md5Crypt.md5Crypt(user.getLoginacct().getBytes(), salt);
		String crypt =  MD5Util.digest16(user.getUserpswd());
        user.setUserpswd(crypt); //保存密码
		
		user.setCreatetime(MyStringUtils.formatSimpleDate(new Date()));//保存注册时间
		user.setLoginacct(user.getLoginacct());
		user.setUsername(user.getLoginacct());
		try {
			i = userMapper.insertSelective(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		return i == 1?true:false;
	}
	public TUser login(TUser user) {
		TUserExample example = new TUserExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andLoginacctEqualTo(user.getLoginacct());
		criteria.andUserpswdEqualTo(MD5Util.digest16(user.getUserpswd()));
		List<TUser> list = null;
		try {
			list = userMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return list.size() == 1?list.get(0):null;
		
	}

}
