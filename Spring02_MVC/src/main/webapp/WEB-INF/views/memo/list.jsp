<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	body *{
		padding:0;
		margin:0;
		box-sizing:border-box;
	}
	#memoList{
		text-align:center;
		width:80%
		margin:auto;
	}
	ul.memos{
		margin:2em auto;
	}
	ul.memos li{
		list-style:none;
		line-height:40px;
		height:40px;
		padding:5px;
		border-bottom:1px solid silver;
		float:left;
	}
	ul.memos li:nth-child(4n+1){
		width:10%
	}
	ul.memos li:nth-child(4n+2){
		width:60%
	}
	ul.memos li:nth-child(4n+3){
		width:15%
	}
	ul.memos li:nth-child(4n){
		width:15%
	}
</style>
<div id="memoList">
	<h1 class="text-center">한줄 메모장 목록</h1>
	<ul class="memos">
		<li>글번호</li>
		<li>메모내용</li>
		<li>작성자</li>
		<li>작성일</li>
		<!-- --------------- -->
		<c:if test="empty listMemo">
			<p>메모가 없습니다.</p>
		</c:if>
		<c:forEach var="memo" items="${memoList}">
				<li>${memo.no }</li>
				<li>${memo.msg }</li>
				<li>${memo.name }</li>
				<li>${memo.wdate }</li>
		</c:forEach>
		<!-- --------------- -->
	</ul>
	<div style="clear:both"></div>
	<a href="memo">[글쓰기]</a>
</div>