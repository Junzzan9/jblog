package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/write")
	public CategoryVo cateWrite(@PathVariable("id") String id, @ModelAttribute CategoryVo categoryVo) {
		System.out.println("카테고리 생성");
		
		
		return categoryService.cateWrite(categoryVo);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/getCateList")
	public List<CategoryVo> getCateList(@PathVariable("id") String id){
		System.out.println("카테-컨 -리스트가져오기 "+ id);
		
		
		return categoryService.getCateList(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/removeCate")
	public boolean removeCate(@PathVariable("id") String id,@RequestParam("cateNo")int cateNo) {
		System.out.println("컨트롤러 카테삭제 [cateNo : "+cateNo);
		
		boolean success = categoryService.removeCate(cateNo);
		
		return success;
	}
}
