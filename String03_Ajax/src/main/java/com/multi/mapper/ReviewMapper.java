package com.multi.mapper;

import java.util.List;

import com.multi.domain.ReviewVO;

public interface ReviewMapper {
	int insertReview(ReviewVO rvo);
	
	List<ReviewVO> getReviewList(int pnum); //특정 상품에 대한 리뷰 글 목록
	
	int getReviewCount(int pnum);
	
	ReviewVO getReview(int no);//리뷰글번호(PK)로 상품글 가져오기
	
	int deleteReview(int no);
	
	int updateReview(ReviewVO rvo);
}
