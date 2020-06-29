package com.example.jpetstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.DepositCart;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("depositSessionCart")
public class RemoveItemFromDepsitCartController { 

	@RequestMapping("/shop/removeDepositItemFromCart.do")
	public ModelAndView handleRequest(
			@RequestParam("workingItemId") String workingItemId,
			@ModelAttribute("depositSessionCart") DepositCart cart
		) throws Exception {
		cart.removeItemById(workingItemId);
		return new ModelAndView("DepositCart", "depositCart", cart);
		}
}
