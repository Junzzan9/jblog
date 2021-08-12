package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public int writePost(PostVo postVo) {
		
		int count = postDao.insertPost(postVo);
		
		System.out.println("포-서 글쓰기"+count);
		
		
		return count;
	}
	
	
}
