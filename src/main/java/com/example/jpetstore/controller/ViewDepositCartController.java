package com.example.jpetstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.CartItem;
import com.example.jpetstore.domain.DepositCart;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("depositSessionCart")
public class ViewDepositCartController { 
	
	@ModelAttribute("depositSessionCart")
	public DepositCart createCart(HttpSession session) {
		DepositCart cart = (DepositCart)session.getAttribute("depositSessionCart");
		if (cart == null) cart = new DepositCart();
		return cart;
	}
	
	@RequestMapping("/shop/viewDepositCart.do")
	public ModelAndView viewCart(
			HttpServletRequest request,
			@RequestParam(value="page", required=false) String page,
			@ModelAttribute("depositSessionCart") DepositCart cart) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		handleRequest(page, cart, userSession);
		return new ModelAndView("DepositCart", "depositCart", cart);
	}

	@RequestMapping("/shop/depositCheckout.do")
	public ModelAndView checkout(
			HttpServletRequest request,
			@RequestParam(value="page", required=false) String page,
			@ModelAttribute("depositSessionCart") DepositCart cart) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		handleRequest(page, cart, userSession);
		System.out.println(cart.getNumberOfItems());
		return new ModelAndView("DepositCheckout", "despositCart", cart);
	}
	
	private void handleRequest(String page, DepositCart cart, UserSession userSession)
			throws Exception {
		if (userSession != null) {
			if ("next".equals(page)) {
				userSession.getMyList().nextPage();
			}
			else if ("previous".equals(page)) {
				userSession.getMyList().previousPage();
			}
		}
		if ("nextCart".equals(page)) {
			cart.getDepositCartItemList().nextPage();
		}
		else if ("previousCart".equals(page)) {
			cart.getDepositCartItemList().previousPage();
		}
	}
}
