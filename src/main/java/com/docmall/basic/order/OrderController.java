package com.docmall.basic.order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.basic.cart.CartProductVo;
import com.docmall.basic.cart.CartService;
import com.docmall.basic.cart.CartVo;
import com.docmall.basic.user.UserVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/order/*")
@Slf4j
@Controller
public class OrderController {

	private final OrderService orderService;
	private final CartService cartService;
	
	@GetMapping("/orderinfo")
	public String orderinfo(CartVo vo, Model model, HttpSession session) throws Exception {
		
		String mbsp_id = ((UserVo) session.getAttribute("login_status")).getMbsp_id();
		vo.setMbsp_id(mbsp_id);
		
		//1)장바구니 저장
		cartService.cart_add(vo);
		
		//2)주문내역
		List<CartProductVo> cart_list = cartService.cart_list(mbsp_id);
		int total_price = 0;
		cart_list.forEach(d_vo -> { 
			d_vo.setPro_up_folder(d_vo.getPro_up_folder().replace("\\", "/"));
//			total_price += (d_vo.getCart_amount() * d_vo.getPro_price());
			}
		);
		
		for(int i=0; i < cart_list.size(); i++) {
			total_price += (cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount());
		}
				
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("total_price", total_price);
		
		return "/order/orderinfo";
	}
}
