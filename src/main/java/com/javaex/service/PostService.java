package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private BlogDao blogDao;

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	public int writePost(PostVo postVo) {
		
		int count = postDao.insertPost(postVo);
		
		System.out.println("포-서 글쓰기"+count);
		
		
		return count;
	}
	
	public Map<String, Object> getPost(String id,int postNo) {
		System.out.println(id+""+postNo);
		BlogVo blogVo = blogDao.selectBlog(id);
		List<CategoryVo> cateList = categoryDao.selectCateList(id);
		System.out.println("포서비-카테리"+cateList);
		PostVo postVo = postDao.selectPost(postNo);
		int cateNo = postVo.getCateNo();
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("id", id);
		listMap.put("cateNo", cateNo);
		List<PostVo> postList = postDao.selectPostList1(listMap);
		
		Map<String, Object> blogMap = new HashMap<String, Object>();

		blogMap.put("blogVo", blogVo);
		blogMap.put("cateList", cateList);
		blogMap.put("postList", postList);
		blogMap.put("PostVo", postVo);
		
		System.out.println("포-서비스 "+blogMap);
		
		return blogMap;
	}
	
	public Map<String, Object> getPostList(String id,int cateNo){
		System.out.println("포서비스- 겟포스트리스트  "+id+""+cateNo);
		BlogVo blogVo = blogDao.selectBlog(id);
		List<CategoryVo> cateList = categoryDao.selectCateList(id);
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("id", id);
		listMap.put("cateNo", cateNo);
		List<PostVo> postList = postDao.selectPostList1(listMap);
		PostVo postVo = postDao.selectrecentPostincaTe(cateNo);
		
		Map<String, Object> blogMap = new HashMap<String, Object>();
		
		blogMap.put("blogVo", blogVo);
		blogMap.put("cateList", cateList);
		blogMap.put("postList", postList);
		blogMap.put("PostVo", postVo);
		
		System.out.println(blogMap);
		
		return blogMap;
	}
	
}
