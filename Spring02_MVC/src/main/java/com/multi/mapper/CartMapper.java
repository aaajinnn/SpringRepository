package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.CartVO;
@Mapper
public interface CartMapper {

	Integer selectCartNum(CartVO cvo); //pnum, userid로 장바구니번호(cnum)가져오기
	//장바구니 번호가 없는 경우엔 MyBatis가 Null을 반환하기 때문에 int가 아닌 Integer타입을 사용
	int updateCartQty(CartVO cvo);
	int addCart(CartVO cvo);
	
	List<CartVO> selectCartView(String userid);
	CartVO getCartTotal(String userid);
	
	int delCart(int cartNum);
	int editCart(CartVO cvo);
	int delCartAll(String userid);
}
