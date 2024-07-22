package com.docmall.basic.admin.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.order.OrderVo;

public interface AdminOrderMapper {

	List<OrderVo> order_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	// 주문자정보(수령인)
	OrderVo order_info(Long ord_code);
	
	// 주문상품정보
	List<OrderDetailInfoVo> order_detail_info(Long ord_code);
	
	// 주문상품 개별삭제.
	void order_product_delete(@Param("ord_code") Long ord_code, @Param("pro_num") int pro_num);
	
	// 주문기본정보 수정
	void order_basic_modify(OrderVo vo);
}
