package com.example.jpetstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.service.OrderValidator;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes({"userSession", "orderForm"})
public class BiddingListController {

	private PetStoreFacade petStore;
	@Autowired
	private OrderValidator orderValidator;

	@ModelAttribute("orderForm")
	public OrderForm createOrderForm2() {
		return new OrderForm();
	}

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping("/shop/biddingList.do")
	public ModelAndView handleRequest(@ModelAttribute("userSession") UserSession userSession) throws Exception {
		String username = userSession.getAccount().getUsername();
		System.out.println(username);
		return new ModelAndView("BiddingList", "BiddingList", petStore.getAuctionByUsername(username));
	}

	@RequestMapping("/shop/checkout2.do")
	public ModelAndView handleRequest(
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("auctionId") int auctionId, ModelMap model
			) throws Exception {
		Auction auction = this.petStore.getAuctionByAuctionId(auctionId);
		if (userSession.getAccount().getUsername().equals(auction.getUsername())) {
			System.out.println(auctionId);
			return new ModelAndView("Checkout2", "auction", auction);
		}
		else {
			return new ModelAndView("Error", "message", "You may only view your own auctions.");
		}

	}

	@ModelAttribute("creditCardTypes")
	public List<String> referenceData2() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		creditCardTypes.add("American Express");
		return creditCardTypes;
	}

	@RequestMapping("/shop/newOrder2.do")
	public String initNewOrder(HttpServletRequest request, @ModelAttribute("orderForm") OrderForm orderForm, 
			@RequestParam("auctionId") int auctionId, 
			@RequestParam("itemId") String itemId,
			@RequestParam("totalPrice") double biddingPrice)
			throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession)
		request.getSession().getAttribute("userSession");
		Item item = this.petStore.getItem(itemId);
		Account account = petStore.getAccount(userSession.getAccount().getUsername());
		orderForm.getOrder().initOrder(account, item, biddingPrice);
		return "NewOrderForm2";

	}

	@RequestMapping("/shop/newOrderSubmitted2.do")
	public String bindAndValidateOrder(HttpServletRequest request,
			@ModelAttribute("orderForm") OrderForm orderForm, 
			BindingResult result) {
		if (orderForm.didShippingAddressProvided() == false) {	
			// from NewOrderForm
			orderValidator.validateCreditCard(orderForm.getOrder(), result);
			orderValidator.validateBillingAddress(orderForm.getOrder(), result);
			if (result.hasErrors()) return "NewOrderForm2";
			
			if (orderForm.isShippingAddressRequired() == true) {
				orderForm.setShippingAddressProvided(true);
				return "ShippingForm2";
			}
			else {			
				return "ConfirmOrder2";
			}
		}
		else {		// from ShippingForm
			orderValidator.validateShippingAddress(orderForm.getOrder(), result);
			if (result.hasErrors()) return "ShippingForm2";
			return "ConfirmOrder2";
		}
	}
	
	@RequestMapping("/shop/confirmOrder2.do")
	protected ModelAndView confirmOrder(
			@ModelAttribute("orderForm") OrderForm orderForm, 
			SessionStatus status) {
		petStore.insertOrder(orderForm.getOrder());
		ModelAndView mav = new ModelAndView("ViewOrder");
		mav.addObject("order", orderForm.getOrder());
		mav.addObject("message", "Thank you, your order has been submitted.");
		status.setComplete();  // remove sessionCart and orderForm from session
		return mav;
	}
}
