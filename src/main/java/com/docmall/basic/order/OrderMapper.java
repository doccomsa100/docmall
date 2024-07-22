package com.docmall.basic.order;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

	void order_insert(OrderVo vo);
	
	// 장바구니테이블을 기반으로 주문상세테이블로 데이터를 저장.  https://velog.io/@ryuneng2/Spring-selectKey-%EC%8B%9C%ED%80%80%EC%8A%A4%ED%9A%8D%EB%93%9D
	void orderDetail_insert(@Param("ord_code") Long ord_code, @Param("mbsp_id") String mbsp_id);
}
