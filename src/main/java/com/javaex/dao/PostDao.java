package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertPost(PostVo postVo) {

		return sqlSession.insert("post.insertPost", postVo);
	}

	public List<PostVo> selectPostList(int cateNo) {

		return sqlSession.selectList("post.selectPostList", cateNo);
	}

	public PostVo selectrecentPost() {
		
		return sqlSession.selectOne("post.selectrecentOne");
		
	}
	
	public PostVo selectPost(int postNo) {
		System.out.println("포다오 - 셀렉포스트");
		
		return sqlSession.selectOne("post.selectOne",postNo);
	}
	
public PostVo selectrecentPostincaTe(int cateNo) {
		
		return sqlSession.selectOne("post.selectrecentPostincaTe",cateNo);
		
	}
}
