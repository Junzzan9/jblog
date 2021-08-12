package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		System.out.println("유컨-로그인폼");

		return "user/loginForm";
	}

	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		System.out.println("유컨-가입폼");

		return "user/joinForm";
	}

	@RequestMapping(value = "/join")
	public String joinOk(@ModelAttribute UserVo userVo) {
		System.out.println("유컨-조인 " + userVo);
		userService.addUser(userVo);

		return "user/joinSuccess";
	}

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("유컨-로그인");
		UserVo authUser = userService.getUser(userVo);
		System.out.println(authUser);
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
		else {
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		System.out.println("유컨-로그아웃");
		session.removeAttribute("authUser");
		
		
		return "redirect:/";
	}
	
	
	
	
	
	
}
