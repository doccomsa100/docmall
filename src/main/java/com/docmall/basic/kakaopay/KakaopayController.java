package com.docmall.basic.kakaopay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao/*")
@Controller
public class KakaopayController {

	private final SampleService sampleService;
	
	
	@GetMapping("/kakaoPayRequest")
	public void kakaoPayRequest() {
		
	}
	
	
	@ResponseBody
	@GetMapping(value =  "/kakaoPay")
	public ReadyResponse kakaoPay() {
		
		ReadyResponse readyResponse = sampleService.ready(null, null);
		
		log.info("응답데이터: " + readyResponse);
		
		return readyResponse;
	}
	
	@GetMapping("/approval")
	public void approval(String pg_token) {
		log.info("pg_token: " + pg_token);
		String approveResponse = sampleService.approve(pg_token);
		
		log.info("최종결과: " + approveResponse);
	}
	
	@GetMapping("/cancel")
	public void cancel() {
		
		
	}
	
	@GetMapping("/fail")
	public void fail() {
		
		
	}
}
