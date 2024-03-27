<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::chatBot::</title>
<!-- -------------------------------------------------- -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- ------------------------------------------------------>
<!-- websocket 라이브러리 추가 CDN-->
<!--  https://cdnjs.com/libraries/sockjs-client  -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<!--  https://cdnjs.com/libraries/stomp.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<!-- --------------------------- -->

<!-- 화면이 로드 되자마자 챗봇 서버에 접속 -->
<script>
	let socket = null;
	let stompClient = null;

	$(function(){
		chat_connect();
		
		// 엔터쳤을 때 메시지보내기
		$('#text').keyup(function(evt){
			if(evt.keyCode==13){
				if($(this).val()!=''){
					sendMessage();
					$(this).val('');
				}
			}
		})
		
		
	})//$() end------------
	
	function chat_connect(){
		//소켓 생성
		socket = new SockJS("${pageContext.request.contextPath}/chatbot"); //endpoint
		stompClient = Stomp.over(socket);
		
		stompClient.connect({}, function(frame){
			//alert(frame);
			console.log('frame=>', frame);
			
			//구독
			stompClient.subscribe('/user/queue/messages', function(msg){
				//alert('queue: ' + msg.body);
				let jsonMsg = JSON.parse(msg.body); //JSON.parse : json으로 변환
				showChatMessage(jsonMsg);
				
			})//subscribe() end---------
		})// chat_connect()---------
	}//--------------------
	
	/*
		<div id='response'>
			<p> (챗봇이 보내는 메뉴) </p>
		</div>
		이 형태를 만드는 작업
	*/
	function showChatMessage(obj){
		let res = document.getElementById('response');
		
		let p = document.createElement('p'); // p태그
		let txt = document.createTextNode(obj.menu); // 텍스트 노드
		p.appendChild(txt);
		res.appendChild(p); //부모인 res안에 자식인 p를 넣는다
	}//--------------------
	
	function sendMessage(){
		let from = 'Guest';
		let text = $('#text').val();
		let obj = {
				from:from,
				text:text,
				mode:'one'
		};
		stompClient.send('/app/chatbot', {}, JSON.stringify(obj));
	}//--------------------
	
	function chat_disconnect(){
		alert('연결끊음')
		if(stompClient!=null){
			stompClient.disconnect();
			stompClient = null;
		}
		console.log('Disconnected...');
	}//--------------------
</script>
</head>
<body onunload="chat_disconnect()">
	<div class="container">
		<br><br>
		<img src="resources/chat1.png" height="136">
		<img src="resources/chat2.png" height="136" width="237">
		
		<div class="alert alert-danger" style="width: 500px;">
		<div>챗 봇>> 1)상품소개  	2)주문확인</div>
		<br>
		<div id="response">
			<!-- <p>응답메뉴</p> -->
		</div>
		<div class="form-floating mb-3 mt-3" id="taMsg">
		<table>
		<tr>
			<td><input type="text" class="form-control" id="text" style="width: 400px; background: yellow"></td>
			<td><button id="sendMessage" onclick="sendMessage();"class="btn btn-primary">Send</button></td>
		</tr>
		</table>
		</div>
	</div>
	</div>
</body>
</html>