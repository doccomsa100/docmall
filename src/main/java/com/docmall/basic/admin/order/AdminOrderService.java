package com.docmall.basic.admin.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.order.OrderVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AdminOrderService {
	
	private final AdminOrderMapper adminOrderMapper;
	
	public List<OrderVo> order_list(Criteria cri) {
		return adminOrderMapper.order_list(cri);
	}
	
	public int getTotalCount(Criteria cri) {
		return adminOrderMapper.getTotalCount(cri);
	}
	
	public OrderVo order_info(Long ord_code) {
		return adminOrderMapper.order_info(ord_code);
	}
	
	public List<OrderDetailInfoVo> order_detail_info(Long ord_code) {
		return adminOrderMapper.order_detail_info(ord_code);
	}
	
	@Transactional
	public void order_product_delete(Long ord_code, int pro_num) {
		adminOrderMapper.order_product_delete(ord_code, pro_num);
		
		// 주문테이블 주문금액 변경
		
		//결제테이블 주문금액 변경
	}
	
	public void order_basic_modify(OrderVo vo) {
		adminOrderMapper.order_basic_modify(vo);
	}
}
