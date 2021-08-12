package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insertBlog(UserVo userVo) {
		System.out.println("블다오-블로그생성");
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getId()+"님의 블로그입니다.");
		
		
		
		sqlSession.insert("blog.insertBlog", blogVo);
		
	}
	
	public BlogVo selectBlog(String id) {
		System.out.println("블다오-seletone");
		
		return sqlSession.selectOne("blog.selectBlog", id);
	}
	
	public int updateBlog(BlogVo blogVo) {
		System.out.println("xml 전 blogVo "+ blogVo);
		
		return sqlSession.update("blog.updateBlog", blogVo);
	}
	
}
