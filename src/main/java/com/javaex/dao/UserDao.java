package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		System.out.println("다오- 회원추가  "+userVo);
		
		return sqlSession.insert("user.insertUser", userVo);
	}

	public UserVo selectOne(UserVo userVo) {
		System.out.println("다오-1명정보 "+userVo);
		
		return sqlSession.selectOne("user.selectOne", userVo);
	}
	
}
