<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">
<h1>한줄 메모장</h1>
<form name="mf" action="memo" method="post">
	<ul class="memo">
		<li>작성자</li>
		<li><input type="text" name="name" placeholder="Name" required></li>
		<li>메모내용</li>
		<li><input type="text" name="msg" placeholder="Message" required></li>
		<li><li>
		<li><button>글쓰기</button></li>
	</ul>
</form>
</div>