<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.jumbotron, .navbar{
		display:none;
	}
</style>
<script>
	function id_check(){
		if(!idf.userid.value){
			alert('아이디를 입력하세요.');
			idf.userid.focus();
			return false;
		}
		return true;
	}
</script>
<!-- idCheck.jsp -->
<div class="container">
	<form name="idf" action="idCheck" method="post" onsubmit="return id_check()"> 
		<label for=id>아이디</label>
		<input type="text" name="userid" id="userid" class="form-control" placeholder="ID" autofocus="autofocus">
		<button class="btn btn-outline-success">확   인</button>
	</form>
</div>