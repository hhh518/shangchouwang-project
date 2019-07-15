package com.atguigu.scw.manager.controller.permission;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.scw.manager.bean.TUser;
import com.atguigu.scw.manager.constant.Constants;
import com.atguigu.scw.manager.service.UserService;
@RequestMapping("permission/user")
@Controller
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/reg")
	public String reg(TUser user,Model model,HttpSession session ) {
		boolean flag = userService.register(user);
		if ( flag == true) {
			
			session.setAttribute(Constants.LOGIN_USER, user);
			return "redirect:/main.html"; //ע��ɹ�		
		}
		else {
			model.addAttribute("regError", "�û����Ѿ���ʹ��");
		    return "forward:/reg.jsp";
		}
	}
	
	
	@RequestMapping("/login")
	public String login(TUser user,HttpSession session) {
		TUser login =  userService.login(user);
		if (login == null) {
			session.setAttribute("errorUser", user);
			session.setAttribute("msg", "��¼ʧ��");
			return "redirect:/login.jsp";
		}
		//��¼�ɹ������û�����session����
		session.setAttribute(Constants.LOGIN_USER, user);
		return "redirect:/main.html";
	}

}
