package com.example.jpetstore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.AccountFormValidator;
import com.example.jpetstore.service.OrderValidator;
import com.example.jpetstore.service.PetStoreFacade;
import com.example.jpetstore.service.ItemValidator;

@Controller
@SessionAttributes("userSession")
public class AuctionRegisterFormController {

	
	@Value("AuctionRegisterForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
  
  	@Autowired
	private ItemValidator validator;
	public void setValidator(ItemValidator validator) {
		this.validator = validator;
	}
/*
	@Autowired
	private AccountFormValidator validator;
	public void setValidator(AccountFormValidator validator) {
		this.validator = validator;
	}
*/
	@ModelAttribute("categories")
	public List<Category> getCategoryList() {
		return petStore.getCategoryList();
	}
	
	@ModelAttribute("products")
	public List<Product> getProductList(){
		return petStore.getProductList();
	}
	
	@RequestMapping("/shop/auctionRegister.do")
	public String showForm(Model model) {
		 model.addAttribute("auctionForm", new AuctionForm());
		return formViewName;
	}
	
	@RequestMapping("/shop/auctionRegisterSubmitted.do")
	public String onSubmit(
			HttpServletRequest request,//梨�姨붿찉�슜吏몄괜姨붿찉
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			BindingResult result,
			@RequestParam("keyword") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date closeTime
			) throws Exception {
		
		validator.validate(auctionForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
		
			Item item = auctionForm.getAuctionItem();
			item.setAuction(1);
			Product product = petStore.getProductByName(item.getProductId());
			System.out.println(product.getName());
			item.setProductId(product.getProductId());
			item.setDeposit(item.getListPrice()/10);
			item.setStatus("P");
     
			UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
			item.setUsername2(userSession.getAccount().getUsername());
			System.out.println("item �쉯�슎占쏀쉻夷섏콬姨붿쭠 username 占쎌껄占쎌콉: " + userSession.getAccount().getUsername());
	
			item.setClosingTime(closeTime);
			petStore.testScheduler(closeTime);
			petStore.insertAuctionItem(item);
			petStore.insertQuantity(item.getItemId(), 1000);
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("auction.itemId", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}

		
		return successViewName;
	}
	
	

}

