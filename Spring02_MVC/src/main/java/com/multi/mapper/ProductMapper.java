package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.CategoryVO;
import com.shop.domain.ProductVO;

@Mapper
public interface ProductMapper {

	//상위 카테고리 목록 가져오기
	List<CategoryVO> getUpCategory();
	
	//하위 카테고리 목록 가져오기
	List<CategoryVO> getDowncategory(int upCg_code);
	
	//상품 정보 등록
	int productInsert(ProductVO prod);
	
	//상품 목록
	List<ProductVO> productList();
	
	//상품 삭제
	int productDelete(int pnum);

	//수정할 상품 정보 가져오기
	ProductVO getProduct(int pnum);
	
	//상품 수정
	int productUpdate(ProductVO prod);

	//스펙으로 목록 가져오기
	List<ProductVO> selectByPspec(String pspec);
}
