package com.shop.service;

import java.util.List;

import com.shop.domain.CategoryVO;
import com.shop.domain.ProductVO;

public interface AdminService {
	/** [������ ���] - ī�װ� ���� */
	public List<CategoryVO> getUpcategory(); //����ī�װ� ��� ��������

	public List<CategoryVO> getDowncategory(int upCg_code); //����ī�װ� ��� ��������

	public int categoryAdd(CategoryVO cvo); //ī�װ� ���

	public int categoryDelete(int cg_code); //ī�װ� ����

	/** [������ ���] - ��ǰ ���� ����ϱ� */
	public int productInsert(ProductVO prod); //��ǰ ���� ���

	public List<ProductVO> productList(); //��ǰ ���

	public ProductVO getProduct(int pnum);
	
	public int productUpdate(ProductVO prod); //��ǰ��� ����
	
	public int productDelete(int pnum); //��ǰ����
	
}
