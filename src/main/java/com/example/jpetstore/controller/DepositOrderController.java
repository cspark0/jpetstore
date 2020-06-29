package com.example.jpetstore.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Cart;
import com.example.jpetstore.domain.DepositCart;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.service.OrderValidator;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Controller
@SessionAttributes({"depositSessionCart", "auctionForm"})
public class DepositOrderController {
	@Autowired
	private PetStoreFacade petStore;
	
	@ModelAttribute("auctionForm")
	public AuctionForm createOrderForm() {
		return new AuctionForm();
	}

	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		creditCardTypes.add("American Express");
		return creditCardTypes;			
	}
	
	@RequestMapping("/shop/newDepositOrder.do")
	public String initNewOrder(HttpServletRequest request,
			@ModelAttribute("depositSessionCart") DepositCart cart,
			@ModelAttribute("auctionForm") AuctionForm auctionForm
			
			) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if (cart != null) {
			// Re-read account from DB at team's request.
			Account account = petStore.getAccount(userSession.getAccount().getUsername());
			auctionForm.getOrder().initOrder(account, cart);
			
			return "NewDepositOrderForm";	
		}
		else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "An order could not be created because a cart could not be found.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	@RequestMapping("/shop/newDepositOrderSubmitted.do")
	public String bindAndValidateOrder(HttpServletRequest request,
			@ModelAttribute("auctionForm") AuctionForm auctionForm, 
			BindingResult result) {
		
				
				return "ConfirmDepositOrder";
			
	}
	
	@RequestMapping("/shop/confirmDepositOrder.do")
	protected ModelAndView confirmOrder(
			@ModelAttribute("auctionForm") AuctionForm auctionForm, 
			SessionStatus status) {
		
		Auction auction = new Auction();
		Date date = new Date();
		
		auction.setItemId(auctionForm.getOrder().getLineItems().get(0).getItem().getItemId());
		auction.setSuccessful(false);
		auction.setUsername(auctionForm.getOrder().getUsername());
		auction.setBiddingPrice(auctionForm.getAuction().getBiddingPrice());
		petStore.insertAuction(auction);
		
		//item Å×ÀÌºí update
		auction.setMaxAuctionId(petStore.getMaxAuctionId(auction.getItemId()));
		petStore.updateAuctionId(auction);
		
		ModelAndView mav = new ModelAndView("ViewAuction");
		mav.addObject("order", auctionForm.getAuction());
		mav.addObject("message", "Thank you, your order has been submitted.");
		status.setComplete();  // remove sessionCart and orderForm from session
		
		return mav;
	}
}
