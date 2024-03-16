<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- prodList.jsp -->
<h2 class="text-center">상품 목록 = Product List</h2>
<div class="row my-4">
	<div class="col-md-10 offset-1">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>상품번호</th>
					<th>카테고리</th>
					<th>상품명</th>
					<th>이미지</th>
					<th>가격</th>
					<th>수정 | 삭제</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>전자제품 > 냉장고</td>
					<td><a href="../ProdDetail?pnum?=1">상품명</a> <span
						class="badge badge-info">NEW</span></td>
					<td><a href="../prodDetail?pnum=1"> 
						<img src="${myctx}/resources/product_images/a" width="100px">
						</a>
					</td>
					<td>정 가 : 
						<del><fmt:formatNumber value="1" pattern="###,###" /></del>원 <br> 
						판매가 : <b> <fmt:formatNumber value="1" pattern="###,###" /> </b>원 <br> 
							<span class="badge badge-danger">1% 할인</span> 
							<span class="badge badge-warning">1p 적립</span>
					</td>
					<td><a href="#" onclick="edit('${item.pnum}')">수정</a> | <a
						href="#" onclick="remove('${item.pnum}')">삭제</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>