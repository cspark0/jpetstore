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

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("sessionCart")
public class ViewCartController { 
	
	@ModelAttribute("sessionCart")
	public Cart createCart(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("sessionCart");
		if (cart == null) cart = new Cart();
		return cart;
	}
	
	@RequestMapping("/shop/viewCart.do")
	public ModelAndView viewCart(
			HttpServletRequest request,
			@RequestParam(value="page", required=false) String page,
			@ModelAttribute("sessionCart") Cart cart) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		handleRequest(page, cart, userSession);
		return new ModelAndView("Cart", "cart", cart);
	}

	@RequestMapping("/shop/checkout.do")
	public ModelAndView checkout(
			HttpServletRequest request,
			@RequestParam(value="page", required=false) String page,
			@ModelAttribute("sessionCart") Cart cart) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		handleRequest(page, cart, userSession);
		return new ModelAndView("Checkout", "cart", cart);
	}
	
	private void handleRequest(String page, Cart cart, UserSession userSession)
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
			cart.getCartItemList().nextPage();
		}
		else if ("previousCart".equals(page)) {
			cart.getCartItemList().previousPage();
		}
	}
}
