package com.docmall.basic.payinfo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PayInfoService {
	private final PayInfoMapper payInfoMapper;
	
	public PayInfoVo ord_pay_info(Long ord_code) {
		return payInfoMapper.ord_pay_info(ord_code);
	}
}
