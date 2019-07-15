package com.atguigu.scw.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.scw.manager.bean.TPermission;
import com.atguigu.scw.manager.constant.Constants;
import com.atguigu.scw.manager.service.TPermissionService;

@Controller
public class DispatcherController {
    @Autowired
    TPermissionService ps;
	
	@RequestMapping(value = "main.html")
	public String toMain(HttpSession session) {
    	Object object = session.getAttribute(Constants.LOGIN_USER);
    	if (object == null) {
			return "redirect:/login.jsp";
		}else {
			//�û���¼�ɹ����������ҳ
			//1.�Ȳ�����в˵�����ҳ�������ʾ
			if (session.getAttribute(Constants.USER_MENUS)==null) {
				List<TPermission> meuns = ps.getAllMenus();
				session.setAttribute(Constants.USER_MENUS, meuns);
			}
            //2.���鵽�Ĳ˵�����session����
			return "manager/main";
		}    	
    }
}
