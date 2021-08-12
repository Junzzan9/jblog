package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlsession;

	public void insertCate(UserVo userVo) {

		CategoryVo categoryVo = new CategoryVo();

		categoryVo.setId(userVo.getId());

		sqlsession.insert("category.insertCate", categoryVo);
	}

	public int insertCate2(CategoryVo categoryVo) {
		System.out.println("카테 dao 인서트2  " + categoryVo);

		int no = sqlsession.insert("category.insertCate2", categoryVo);

		System.out.println(no);

		return no;
	}

	public CategoryVo selectCategory(int cateNo) {

		return sqlsession.selectOne("category.selectOne", cateNo);

	}

	public List<CategoryVo> selectCateList(String id) {

		return sqlsession.selectList("category.selectCateList", id);
	}
	
	public int deleteCate(int cateNo) {
		
		return sqlsession.delete("category.deleteCate", cateNo);
	}

}
