<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>weather</title>
	<!-- -------------------------------------------------- -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- ------------------------------------------------------>
	
	<script>

		$(function(){
			$('#btn').click(function(){
				//alert('a');
				$.ajax({
					url:'https://api.openweathermap.org/data/2.8/onecall?lat=37.28&lon=127.06&exclude=current&appid=8ed76df89f26c887b07b3ea36f40fe5d',
					dataType:'json',
					cache:false,
					success:function(data){
						str = '';
	 					let weather = data.daily[0].weather[0].main;
						let feels_like = data.daily[0].feels_like.day;
						
						if(weather=="Clouds"){
							str=`
								<h3>구름이 많아요!</h3>
								<img src="resources/clouds.jpg">
							`;
						}else if(weather=="Rain"){
							str=`
								<h3>비가 와요!</h3>
								<img src="resources/rain.jpg">
							`;
						}else if(weather=="Clear"){
							str=`
								<h3>해 쨍쨍!</h3>
								<img src="resources/sun.jpg">
							`;
						}
						$('#result').html(str);
						
						if(feels_like >= 300){
							str = `
								<h3>더운 날씨에요</h3>
								<img src="resources/hot_weather.jpg">
							`;
						}else if(feels_like >= 200){
							str = `
								<h3>보통 날씨에요</h3>
								<img src="resources/good_weather.jpg">
							`;
						}else{
							str = `
								<h3>추운 날씨에요</h3>
								<img src="resources/cold_weather.jpg">
							`;
						}
						$('#result').append(str);
					
					},
					error:function(err){
						alert(err.status);
					}
			})//---------------
		})
	})
	</script>
</head>
<body>
<div class="container py-5">
	<button class="btn btn-info" id="btn">날씨 알아보기(수원)</button>
	
	<hr>
		<div id="result" class="my-4">
	
		</div>
</div>
</body>
</html>