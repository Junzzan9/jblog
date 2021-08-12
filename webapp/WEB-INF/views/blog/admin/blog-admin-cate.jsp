<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"
></script>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a
					href="${pageContext.request.contextPath}/${blogMap.blogVo.id}/admin/basic/"
				>기본설정</a></li>
				<li class="tabbtn selected"><a
					href="${pageContext.request.contextPath}/${blogMap.blogVo.id}/admin/category/"
				>카테고리</a></li>
				<li class="tabbtn"><a
					href="${pageContext.request.contextPath}/${blogMap.blogVo.id}/admin/writeForm/"
				>글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->

						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="cate_name" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="cate_description" name="desc"></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->



	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">


//카테고리 리스트그리기
$(document).ready(function() {
	console.log("화면 로딩 직전");
	//ajax 요청하기
	$.ajax({

		url : "${pageContext.request.contextPath }/${blogMap.blogVo.id}/admin/category/getCateList",
		type : "post",
		//contentType : "application/json",
		//data : {name: "홍길동"},

		//dataType : "json",
		success : function(cateList) {
			/*성공시 처리해야될 코드 작성*/
			for (var i = 0; i < cateList.length; i++) {

					render(cateList[i], "down"); //list draw

				}

		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});

//카테고리 추가하기
$("#btnAddCate").on("click",function(){
	console.log("btnAddCate clicked")
	event.preventDefault();
		
	var cateName = $("#cate_name").val();
	var description = $("#cate_description").val();
	
	$.ajax({
		
		//request
		url : "${pageContext.request.contextPath }/${blogMap.blogVo.id}/admin/category/write",
		type : "post",
		//contentType : "application/json",
		data : {
			cateName: cateName,
			description : description,
			id : '${blogMap.blogVo.id}'
		},
		
		
		//response
		//dataType : "json",
		success : function(categoryVo) {
			
			console.log(categoryVo);
			
			render(categoryVo,"up");
			
			$("#cate_name").val("");
			$("#cate_description").val("");
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	
});

//카테고리 삭제하기
$("#cateList").on("click", ".btnCateDel", function() {
	console.log("btnCateDel clicked")
	
	
	
	var no = $(this).data("no");
	console.log(no)
	
		$.ajax({
		
		//request
		url : "${pageContext.request.contextPath }/${blogMap.blogVo.id}/admin/category/removeCate/",
		type : "post",
		//contentType : "application/json",
		data : {
			cateNo: no
		},
		
		
		//response
		//dataType : "json",
		success : function(count) { 
			
				if(count == false){
				
					alert("삭제할 수 없습니다.");
			
				}else{
					
					$("#c-"+ no ).remove();
				
				}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
		
});

//카테고리 테이블 틀
function render(categoryVo,type){
	str = "";
	str += "<tr id = 'c-"+categoryVo.cateNo+"'>";
	str += "	<td>"+categoryVo.cateNo+"</td>";
	str += "	<td>"+categoryVo.cateName+"</td>";
	str += "	<td>"+categoryVo.count+"</td>";
	str += "	<td>"+categoryVo.description+"</td>";
	str += "	<td class='text-center'><img  data-no='"+categoryVo.cateNo+"' class='btnCateDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>";
	str += "</tr>";
	
	if(type == "down"){
		
		$("#cateList").append(str);
	}else if(type == "up"){
		
		$("#cateList").prepend(str);
	}
};

</script>



</html>