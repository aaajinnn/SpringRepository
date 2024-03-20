<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피자 주문서</title>
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
	let req = false; //생성이 안될수도 있어서 일단 false로
	
	const init = function(){
		try{
			req = new XMLHttpRequest(); // 이 객체만 있으면 Ajax이용 가능(브라우저마다 생성하는 방식이 다르므로 try catch)
			console.log('req : ' + req); // 잘 받아오는지 브라우저 콘솔에 찍어보기
										//콘솔에 req : [object XMLHttpRequest] 라고 떴다면 정상적으로 호출 성공
		}catch(e){
			alert('req생성 실패');
		}
		
	}//-------------------

	const getUserInfo = function(){
		//데이터를 입력하고 다른곳으로 이동해서 입력하려고 하면 반응함 ===> onchange()
		
		//1.사용자가 입력한 연락처 정보를 알아내자
		// 	사용자가 입력한 값을 알아내기 위해서는 id="" 를 이용함
		let tel = $('#phone').val(); //document.getElementById('phone').value
		//alert(tel);
		
		//2.Ajax로 요청을 보내보자
		//[1] 요청을 준비하는 단계 => XMLHttpRequest의 open(요청방식, url, [비동기여부])
		
	 	//let url = "pizzaResult.jsp?phone="+tel; //==> text date를 생성
		//let url = "pizzaResultXML.jsp?phone="+tel; //==> xml data를 생성(db연동)
		let url = "pizzaResultJson.jsp?phone="+tel;
		
		req.open('GET', url, true) // true ==> 비동기방식으로 요청을 보냄(디폴트)
		
		//[2] 요청을 보내기 전에 onreadystatechange 속성값에 콜백함수 지정!!!!!! 
		// 응답이오면 state가 바뀜 -> 그걸감지하는 속성 ==> 상태값이 바뀔때 어떤값을 부를것이라고 함수 지정 -> 함수가 데이터를받아 화면에 랜더링해줌
		req.onreadystatechange = updatePage; // updatePageg함수를 부르겠다
		
		//[3] 요청을 전송하는 단계 => send(파라미터 데이터들)
		req.send(null);
		// get : null을 전달
		// post : phone=1111&id=hong 형식으로 전달
		
	}//-------------------
	
	//응답데이터를 1.text형태(html)  2.xml  3.json으로 받아보자(2, 3형식이 많음)
	
	// 응답데이터 받기 
	// 1. text(html) 형식이면 responseText로 받고
	// 2. xml형식이면 responseXML 로 받고
	// 3. json형식이면 responseText로 받고 JSON.parse()함수를 이용해서 text문자열을 json객체로 변환해야한다.
	
	//1.text형태(html)로 받을 경우
	const updatePage1 = function(){
		//alert(req.readyState + "/" + req.status);
		// readyState : 보내는 응답상태가 바뀔때마다 콜백함수 호출
		// status : 페이지 상태
		if(req.readyState==4 && req.status==200){ // 데이터 수신이 완료되면 4를 반환
			
			let res = req.responseText;
			//alert(res);
			
			//'#' 구분자를 기준으로 쪼개서 배열에 저장
			let tokens = res.split('#')
			//tokens[0]=회원번호	tokens[1]=이름...
			let str = "<h3>회원이름 : " + tokens[1] + "<h3>";
			let str2 = `
				<h3>회원 주소 : \${tokens[2]} </h3>
				<h3>회원 연락처 : \${tokens[3]} </h3>
			`;
			$('#userInfo').html(str);
			$('#address').html(str2);
			
		}//if------
	}//---------------------------
	
	// 2.xml로 받을 경우
	const updatePage2 = function(){
		if(req.readyState==4 && req.status==200){
			let res = req.responseXML; //xml로 받을 경우 => XMLDocument객체로 응답옴 ==> 이 문서를 파싱하여 필요한 데이터를 추출한다 ==> jquery의 find()함수 사용
			//alert(res);
			let num = $(res).find('num').text();
			if(parseInt(num)==0){ //비회원일 경우
				$('#nonUser').show(1000);
				$('#userInfo').html("").hide(1000); //안쪽에 들어간 값 비워주기
				$('#addr').focus();
			}else{ //회원일 경우
				let name = $(res).find('name').text();
				let addr = $(res).find('addr').text();
				let phone = $(res).find('phone').text();
				// phone태그의 type속성을 찾아 넣으세요 attr()정적, prop()기능적
				let phoneType = $(res).find('phone').attr('type'); 
				
				let str = `
					<h3>회원번호 : \${num} </h3>
					<h3>회원이름 : \${name} </h3>
					<h3>주소 : \${addr} </h3>
					<h3>연락처[\${phoneType}] : \${phone} </h3>
				`;
				$('#userInfo').html(str).show(1000);
				$('#nonUser').hide();
				$('#addr').val("");
				}
		}//if------
	}//---------------------------
	
	// 3.json 으로 받을 경우 responseText로 받고 JSON.parse()함수를 이용해서 text문자열을 json객체로 변환해야한다.
	const updatePage = function(){
		if(req.readyState==4 && req.status==200){
			let res = req.responseText;
			//alert(res + ", typeof res : " + typeof(res)); //string
			// [1] JSON.parse(문자열) ==> 문자열이 json객체로 변환된다.
			// [2] JSON.stringify(json객체) ==> json객체를 문자열로 변환함
			
			let obj = JSON.parse(res);
			//alert(obj + ", typeof(obj) : " + typeof(obj)); // [object Object], typeof(obj) : object (==>객체로 변환됨 확인)
			
			let num = obj.num;
			let name = obj.name;
			let phone = obj.phone;
			let addr = obj.addr;
			
			if(parseInt(num)==0){ //비회원일 경우
				$('#nonUser').show(1000);
				$('#userInfo').html("").hide(1000); //안쪽에 들어간 값 비워주기
				$('#addr'),focus();
			}else{ //회원일 경우
				let str = `
				<div class="alert alert-success my-3">
					<h3>회원번호 : \${num} </h3>
					<h3>회원이름 : \${name} </h3>
					<h3>주소 : \${addr} </h3>
					<h3>연락처 : \${phone} </h3>
				</div>
				`;
				$('#userInfo').html(str).show(1000);
				$('#nonUser').hide();
				$('#addr').val("");
			}
			
		}//if------
	}//---------------------------
	
	window.onload = init; //윈도우가 init을 불러 실행. $(function(){})과 비슷

</script>
</head>
<body>
<div class="container py-5">
    
    <h1 class="m-4 text-center">Pizza Order Page</h1>
    
    <form role="form" class="form-horizontal" 
    name="orderF" id="orderF"
     action="order.jsp" method="POST">
        <div class="form-group">
            <p class="text-info">
            <b>귀하의 전화번호를 입력하세요:</b>
            <input type="text" size="20" name="phone" id="phone" onchange="getUserInfo()"  class="form-control"/>
            </p>
            <p class="text-danger">
            <b>
                귀하가 주문하신 피자는 아래 주소로 배달됩니다.
            </b>  
            </p>
            <div id="userInfo"></div>
            <div id="address"></div>
            <!-- 비회원 입력 폼 : 비회원일 경우 주소입력 폼을 보여주자.-->
            <div id="nonUser" style="display:none;">
                주소: <input type="text" name="addr" id="addr"
                        size="60" style="border:2px solid maroon;" class="form-control"/>
            </div>
            <!-- ------------------------------------------- -->
            <p class="text-info">
            <b>주문 내역을 아래에 기입하세요</b></p>
            <p>
                <textarea name="orderList"
                 id="orderList" rows="6" cols="50" class="form-control"></textarea>
            </p>
            <p>
                <input type="submit" value="Order Pizza" class="btn btn-primary"/>
            </p>
        </div>
    </form>
</div>
</body>
</html>