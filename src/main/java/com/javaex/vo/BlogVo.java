package com.javaex.vo;

public class BlogVo {

	// field
	private String id;
	private String blogTitle;
	private String logoFile;
	private String userName;

	// constructor
	public BlogVo() {
		super();
	}

	public BlogVo(String id) {
		super();
		this.id = id;
	}

	public BlogVo(String id, String blogTitle) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
	}
	
	public BlogVo(String id, String blogTitle, String logoFile) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}

	public BlogVo(String id, String blogTitle, String logoFile, String userName) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
		this.userName = userName;
	}

	// method g/s
	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	// method general
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + ", userName=" + userName
				+ "]";
	}

}
