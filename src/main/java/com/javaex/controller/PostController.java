package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/{id}/admin/post/write")
	public String writePost(@PathVariable("id") String id,@ModelAttribute PostVo postVo) {
		System.out.println("포컨-글쓰기"+postVo);
		
		postService.writePost(postVo);
		
		return "redirect:/{id}/admin/writeForm";
	}
	
	@RequestMapping(value="/{id}/admin/post/getPost")
	public String getPost(@PathVariable("id") String id,@RequestParam("postNo") int postNo,Model model) {
		System.out.println("포컨 - 포스트바꾸기"+postNo);
		
		Map<String, Object> blogMap = postService.getPost(id,postNo);
		System.out.println(blogMap);
		
		model.addAttribute("blogMap", blogMap);
		
		return "/blog/blog-main";
	}
	@RequestMapping(value="/{id}/admin/post/getPostList")
	public String getPostList(@PathVariable("id") String id,@RequestParam("cateNo") int cateNo,Model model) {
		System.out.println("포컨 - 포스트리스트바꾸기"+cateNo);
		
		Map<String, Object> blogMap = postService.getPostList(id,cateNo);
		
		model.addAttribute("blogMap", blogMap);
		
		return "/blog/blog-main";
	}
	
}
