package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;

	public Map<String, Object> getBlog(String id) {
		System.out.println("블다오 - getBlog " + id);

		BlogVo blogVo = blogDao.selectBlog(id);

		List<CategoryVo> cateList = categoryDao.selectCateList(id);
		//test
		
		PostVo recentPostVo = postDao.selectrecentPost();
		int lastCateNo = recentPostVo.getCateNo();
		System.out.println(lastCateNo);
		System.out.println(recentPostVo);
		
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("id", id);
		listMap.put("cateNo", lastCateNo);
		List<PostVo> postList = postDao.selectPostList1(listMap);
		

		Map<String, Object> blogMap = new HashMap<String, Object>();

		blogMap.put("blogVo", blogVo);
		blogMap.put("cateList", cateList);
		blogMap.put("postList", postList);
		blogMap.put("PostVo", recentPostVo);
		
		System.out.println("가져온 Vo " + blogVo);

		return blogMap;
	}

	public int modifyBlog(String id, String blogTitle, MultipartFile file) {
			
		if (file.getSize() == 0) {
			System.out.println("파일 사이즈:  "+file.getSize());
			System.out.println("파일 없을때");
			BlogVo blogVo = new BlogVo(id, blogTitle, "");
			return blogDao.updateBlog(blogVo);
		} else {
			System.out.println("파일 있을때");

			String saveDir = "C:\\javaStudy\\upload";

			// filename extension
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

			// rename file
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

			// file path
			String filePath = saveDir + "\\" + saveName;
			System.out.println("filePath = " + filePath);

			// file restore on hdd
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileData);
				bout.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BlogVo blogVo = new BlogVo(id, blogTitle,saveName);

			return blogDao.updateBlog(blogVo);
		}

	}

}
