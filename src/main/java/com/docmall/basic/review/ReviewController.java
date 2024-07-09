package com.docmall.basic.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


// pro_detail.html 에서 상품후기 처리.

@RequiredArgsConstructor
@RequestMapping("/review/*")
@Slf4j
@RestController
public class ReviewController {

	private final ReviewService reviewService;
	
	// 리뷰목록과페이징. rest api 개발방법론  /revlist/상품코드/페이지번호   /revlist/10/1  매핑주소의 파트부분을 매개변수로 사용하고 자 할 경우
	// 전통 : /revlist?pro_num=10&page=1
	@GetMapping("/revlist/{pro_num}/{page}")  // 목록과페이징작업을 할 경우는 Criteria 파라미터를 지금까지는 사용했다.
	public ResponseEntity<Map<String, Object>> revlist(@PathVariable("pro_num") int pro_num, @PathVariable("page") int page) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();
		
		// 1)후기목록
		Criteria cri = new Criteria();
		cri.setPageNum(page);
		
		List<ReviewVo> revlist = reviewService.rev_list(pro_num, cri);
		
		//2)페이징정보
		int revcount = reviewService.getCountReviewByPro_num(pro_num);
		PageDTO pageMaker = new PageDTO(cri, revcount);
				
		map.put("revlist", revlist);
		map.put("pageMaker", pageMaker);
		
		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		return entity;
	}
}
