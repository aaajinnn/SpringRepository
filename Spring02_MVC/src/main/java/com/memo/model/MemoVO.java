package com.memo.model;

import java.sql.Timestamp;

import lombok.Data;
@Data// 우측 outline 확인해보면 setter, getter 자동으로 생성해줌
public class MemoVO {

	private int no;
	private String name;
	private String msg;
	private Timestamp wdate;
}
