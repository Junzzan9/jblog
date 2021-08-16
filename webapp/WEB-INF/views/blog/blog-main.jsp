<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"
></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">

					<!-- 기본이미지 -->
					<c:choose>
						<c:when test="${blogMap.blogVo.logoFile eq 'spring-logo.jpg' }">
							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
						</c:when>
						<c:otherwise>

							<!-- 사용자업로드 이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogMap.blogVo.logoFile}">
						</c:otherwise>
					</c:choose>

					<div id="nick">${blogMap.blogVo.userName}(${blogMap.blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:choose>
							<c:when test="${empty blogMap.cateList }">
								<strong>등록된 글이 없습니다.</strong>
							</c:when>
							<c:otherwise>
								<c:forEach items="${blogMap.cateList}" var="categoryVo">
									<li><a
										href="${pageContext.request.contextPath}/${blogMap.blogVo.id}/admin/post/getPostList?cateNo=${categoryVo.cateNo}"
									>${categoryVo.cateName}</a></li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->

			<div id="post_area">

				<!-- 글이 없는 경우 -->
				<c:choose>
					<c:when test="${empty blogMap.PostVo }">
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left">
								<strong>등록된 글이 없습니다.</strong>
							</div>
							<div id="postDate" class="text-left">
								<strong></strong>
							</div>
							<div id="postNick"></div>
						</div>

						<div id="post"></div>
					</c:when>
					<c:otherwise>
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left">
								<strong>${blogMap.PostVo.postTitle }</strong>
							</div>
							<div id="postDate" class="text-left">
								<strong>${blogMap.PostVo.regDate }</strong>
							</div>
							<div id="postNick">${blogMap.blogVo.userName}(${blogMap.blogVo.id})님</div>
						</div>
						<!-- //postBox -->

						<div id="post">${blogMap.PostVo.postContent }</div>
						<!-- //post -->
					</c:otherwise>
				</c:choose>
				<div id="list">
					<div id="listTitle" class="text-left">
						<strong>카테고리의 글</strong>
					</div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<c:choose>
							<c:when test="${empty blogMap.postList }">
								<tr>
									<td id="titles" class="text-left" data-postno="${postVo.postNo}"><a href="">등록된 글이
											없습니다.</a></td>
									<td class="text-right"></td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${blogMap.postList}" var="postVo">
									<tr>
										<td id="titles" class="text-left" data-postno="${postVo.postNo}"><a
											href="${pageContext.request.contextPath}/${blogMap.blogVo.id}/admin/post/getPost?postNo=${postVo.postNo}"
										>${postVo.postTitle}</a></td>
										<td class="text-right">${postVo.regDate}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>


					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->



		</div>
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>



	</div>
	<!-- //wrap -->
</body>
</html>