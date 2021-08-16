package com.javaex.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/{id}")
	public String blog(@PathVariable("id") String id, Model model) {
		System.out.println("블컨 - 블로그");

		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap = blogService.getBlog(id);
		System.out.println(blogMap);
		
		model.addAttribute("blogMap", blogMap);

		return "/blog/blog-main";
	}

	@RequestMapping(value = "/{id}/admin/basic")
	public String blogModifyForm(@PathVariable("id") String id, Model model, HttpSession session) {
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap = blogService.getBlog(id);

		model.addAttribute("blogMap", blogMap);
		System.out.println(blogMap);
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String ssnid = userVo.getId();
		System.out.println(ssnid+"    "+id);
		if (!userVo.getId().equals(id)) {
			return "redirect:/";
		} else {
			return "/blog/admin/blog-admin-basic";

		}
	}

	@RequestMapping(value = "/{id}/admin/basic/modify")
	public String blogModify(@PathVariable("id") String id,
			@RequestParam(value = "blogTitle", required = false, defaultValue = "") String blogTitle,
			@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
			HttpSession session) {
		System.out.println("블로그컨 - 수정 " + file.getOriginalFilename());

		blogService.modifyBlog(id, blogTitle, file);
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		if (!userVo.getId().equals(id)) {
			return "redirect:/";
		} else {
			return "redirect:/{id}/admin/basic";
		}
	}

	@RequestMapping(value = "/{id}/admin/category")
	public String cateModify(@PathVariable("id") String id, Model model, HttpSession session) {
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap = blogService.getBlog(id);

		model.addAttribute("blogMap", blogMap);
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		if (!userVo.getId().equals(id)) {
			return "redirect:/";
		} else {
			return "/blog/admin/blog-admin-cate";

		}
	}

	@RequestMapping(value = "/{id}/admin/writeForm")
	public String write(@PathVariable("id") String id, Model model, HttpSession session) {
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap = blogService.getBlog(id);
		
		
		model.addAttribute("blogMap", blogMap);
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		if (!userVo.getId().equals(id)) {
			return "redirect:/";
		} else {
			return "/blog/admin/blog-admin-write";
		}
	}

}
