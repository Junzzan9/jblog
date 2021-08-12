package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	
}
