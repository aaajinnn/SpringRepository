package com.shop.domain;

import lombok.Data;

@Data //setter, getter
public class CategoryVO {
	
	private int upCg_code; //���� ī�װ� �ڵ�
	private String upCg_name; //���� ī�װ���
	
	private int downCg_code; //���� ī�װ� �ڵ�
	private String downCg_name; //���� ī�װ���
		
}
