package com.docmall.basic.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;
import com.docmall.basic.user.UserVo;

import jakarta.servlet.http.HttpSession;
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
		cri.setAmount(2);
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
	
	// JacksonDatabind 라이브러리. 
	// 상품후기저장.  consumes : 클라이언트에서 보내온는 값의 포맷(MIME)
	@PostMapping(value = "/review_save", consumes = {"application/json"}, produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> review_save(@RequestBody ReviewVo vo, HttpSession session) throws Exception {
		
		String mbsp_id = ((UserVo) session.getAttribute("login_status")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		log.info("상품후기데이타: " + vo);
		
		reviewService.review_save(vo);
				
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
				
		return entity;
	}
	
	// 상품후기 수정폼
	@GetMapping("/review_modify/{rev_code}")
	public ResponseEntity<ReviewVo> review_modify(@PathVariable("rev_code") Long rev_code) throws Exception {
		
		ResponseEntity<ReviewVo> entity = null;
		
		entity = new ResponseEntity<ReviewVo>(reviewService.review_modify(rev_code), HttpStatus.OK);
		
		return entity;
	}
	
	// 상품후기 수정
	@PutMapping("/review_modify")
	public ResponseEntity<String> review_modify(@RequestBody ReviewVo vo) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		reviewService.review_update(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	
	// 상품후기삭제.
	@DeleteMapping("/review_delete/{rev_code}")
	public ResponseEntity<String> review_delete(@PathVariable("rev_code") Long rev_code) throws Exception {
		
		log.info("장바구니코드:" + rev_code);
		ResponseEntity<String> entity = null;
		reviewService.review_delete(rev_code);

		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
