package com.docmall.basic.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jakarta.servlet.http.HttpSession;

public interface CartMapper {

	void cart_add(CartVo vo);
	
	List<CartProductVo> cart_list(String mbsp_id);
	
	void cart_del(Long cart_code);
	
	void cart_change(@Param("cart_code") Long cart_code, @Param("cart_amount") int cart_amount);
	
	void cart_empty(String mbsp_id);
}
