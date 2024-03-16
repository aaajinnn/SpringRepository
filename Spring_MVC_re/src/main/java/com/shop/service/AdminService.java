package com.shop.service;

import java.util.List;

import com.shop.domain.CategoryVO;
import com.shop.domain.ProductVO;

public interface AdminService {
	/** [관리자 모드] - 카테고리 관리 */
	public List<CategoryVO> getUpcategory(); //상위카테고리 목록 가져오기

	public List<CategoryVO> getDowncategory(int upCg_code); //하위카테고리 목록 가져오기

	public int categoryAdd(CategoryVO cvo); //카테고리 등록

	public int categoryDelete(int cg_code); //카테고리 삭제

	/** [관리자 모드] - 상품 정보 등록하기 */
	public int productInsert(ProductVO prod); //상품 정보 등록

	public List<ProductVO> productList(); //상품 목록

	public ProductVO getProduct(int pnum);
	
	public int productUpdate(ProductVO prod); //상품목록 수정
	
	public int productDelete(int pnum); //상품삭제
	
}
