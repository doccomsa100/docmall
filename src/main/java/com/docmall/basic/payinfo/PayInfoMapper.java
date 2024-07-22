package com.docmall.basic.payinfo;

public interface PayInfoMapper {

	
	void payInfo_insert(PayInfoVo vo);
	
	PayInfoVo ord_pay_info(Long ord_code);
}
