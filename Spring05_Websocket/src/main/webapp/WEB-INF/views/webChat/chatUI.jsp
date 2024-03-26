<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::Chatting::</title>
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
<!-- websocket 라이브러리 추가 CDN-->
  <!--  https://cdnjs.com/libraries/sockjs-client  -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
  <!--  https://cdnjs.com/libraries/stomp.js -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
 <!-- --------------------------- -->
 
 <script>
 	let socket = null;
 	let stompclient = null;
 	let nickname; //input에 입력한 값
 	
 	function connect(){
 		socket = new SockJS("${pageContext.request.contextPath}/chat"); //end point => "/chat"	
 		
 		stompClient = Stomp.over(socket); //소켓을 이용해서 stomp생성
 		//stomp이용해서 서버에 연결
 		stompClient.connect({}, function(frame){
 			alert('연결됨 : ' + frame);
 			
 			// 서버로 메시지를 보내자
			sendMessage("aaa","server","안녕하세요");
 			
 			stompClient.subscribe("/topic/messages", function(msg){
 				alert(msg.body)
 				console.log('subscribe topic -> ', msg);
 			});//subscribe end-------------
 		});//stomp.connect() end------------
 	}//connect()---------------------------------
 	
 	// 서버로 메시지 보내는 함수
 	function sendMessage(from, to, text){
 		let obj = {
 				from:from,
 				to:to,
 				text:text,
 				mode:'all'
 		}
 		
 		stompClient.send('/app/chat',{},JSON.stringify(obj));
 	}
 	
 	function disconnect(){
 		
 	}
 </script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Multishop Chatting</h1>
			<div class="mb-3 mt-3">
		    	<label for="nickname" class="form-label">닉네임 : </label>
		    	<input type="text" class="form-control" id="nickname" placeholder="Enter nickname" name="nickname">
	  		</div>
		  	<!-- 접속하기 -->
			<button type="button" class="btn btn-warning" id="connectBtn" onclick="connect()">채팅서버 연결하기</button>
			<!-- 접속끊기 -->
			<button type="button" class="btn btn-secondary" id="disConnectBtn">채팅서버 연결끊기</button>
		</div>
	</div>
	
	<!-- 대화입력 박스/ 대화내용 박스 -->
	<!-- 연결상태 알림 -->
  	<div class="alert alert-success my-4">
  		<strong id="status">채팅을 연결후 사용하세요....</strong>
  	</div>
  	<div id="showChat" style="display:none">
  		<!-- 메시지 입력 -->
  		<div class="mb-3 mt-3">
		    <label for="inputMsg" class="form-label">메시지 : </label>
		    <input type="text" class="form-control" id="inputMsg" placeholder="메시지를 입력하세요." name="inputMsg">
	  	</div>
	  	<!-- 대화 내용 -->
	  	<div id="taMsg"></div>
  	</div>
</div><!-- .container -->

</body>
</html>