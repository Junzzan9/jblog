package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public CategoryVo cateWrite(CategoryVo categoryVo) {
		System.out.println("카테 서비스 - 카테생성  " + categoryVo);

		categoryDao.insertCate2(categoryVo);
		System.out.println(categoryVo);

		return categoryDao.selectCategory(categoryVo.getCateNo());
	}

	public List<CategoryVo> getCateList(String id) {
		System.out.println("카테-서비스 -리스트가져오기" + id);

		List<CategoryVo> cateList = categoryDao.selectCateList(id);

		System.out.println(cateList);

		return cateList;
	}

	public boolean removeCate(int cateNo) {
		System.out.println("카테 서비스 삭제");

		CategoryVo categoryVo = categoryDao.selectCategory(cateNo);

		int count = categoryVo.getCount();
		if (count != 0) {
			return false;
		} else {
			categoryDao.deleteCate(cateNo);
			return true;

		}
	}

}
