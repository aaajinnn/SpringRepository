package com.memo.model;

import java.sql.Timestamp;

import lombok.Data;
@Data// ���� outline Ȯ���غ��� setter, getter �ڵ����� ��������
public class MemoVO {

	private int no;
	private String name;
	private String msg;
	private Timestamp wdate;
}
