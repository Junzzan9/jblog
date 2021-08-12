package com.javaex.dao;

import java.util.List;

import com.javaex.vo.CategoryVo;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CategoryDao categoryDao = new CategoryDao();
		
		List<CategoryVo> cateList = categoryDao.selectCateList("junzzang4");
		
		
		
		
		
		
		System.out.println((cateList));
		
	}

}
