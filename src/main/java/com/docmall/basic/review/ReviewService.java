package com.docmall.basic.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.basic.common.dto.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewService {

	private final ReviewMapper reviewMapper;
	
	public List<ReviewVo> rev_list(Integer pro_num, Criteria cri) {
		return reviewMapper.rev_list(pro_num, cri);
	}
	
	public int getCountReviewByPro_num(Integer pro_num) {
		return reviewMapper.getCountReviewByPro_num(pro_num);
	}
	
	public void review_save(ReviewVo vo) {
		reviewMapper.review_save(vo);
	}
	
	public void review_delete(Long rev_code) {
		reviewMapper.review_delete(rev_code);
	}
	
	public ReviewVo review_modify(Long rev_code) {
		return reviewMapper.review_modify(rev_code);
	}
	
	public void review_update(ReviewVo vo) {
		reviewMapper.review_update(vo);
	}
}
