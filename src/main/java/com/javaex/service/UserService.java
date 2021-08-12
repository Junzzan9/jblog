package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	public int addUser(UserVo userVo) {
		System.out.println("유서비-회원추가   "+userVo);
		
		int count = userDao.insertUser(userVo);
		
		//create BLOG
		blogDao.insertBlog(userVo);
		
		//create category
		categoryDao.insertCate(userVo);
		
		
		return count;
	}
	
	public UserVo getUser(UserVo userVo) {
		
		
		return userDao.selectOne(userVo);
	}
}
