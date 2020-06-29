package com.example.jpetstore.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.AccountFormValidator;
import com.example.jpetstore.service.OrderValidator;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
public class UpdateAuctionItemController {

	
	@Value("AuctionUpdateForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
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
	
	@RequestMapping("/shop/updateAuction.do")
	public String showForm(@RequestParam("itemId2") String itemId, Model model) {
		Item item = petStore.getItem(itemId);
		if(item.getIsAuction()==1) {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm");	
		String closingTime = formatter.format(item.getClosingTime());
		System.out.println(closingTime);
		model.addAttribute("closingTime", closingTime);
		}
		 model.addAttribute("auctionForm", new AuctionForm(item));
		if(item.getIsAuction()==0) {
			return "ItemUpdateForm";
		}
		return formViewName;
	}

	
	@RequestMapping("/shop/auctionUpdateSubmitted.do")
	public String onSubmit(
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			BindingResult result,
			@RequestParam("keyword") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date closeTime) throws Exception {
		
		//validator.validate(auctionForm, result);
		
		//if (result.hasErrors()) return formViewName;
		Item item = auctionForm.getAuctionItem();
		//item.setAuction(1);
		Product product = petStore.getProductByName(item.getProductId());
		item.setProductId(product.getProductId());
		item.setClosingTime(closeTime);
		petStore.testScheduler(closeTime);
		item.setDeposit(item.getListPrice()/10);
		//item.setStatus("P");
		
		petStore.updateAuctionItem(item);
		//petStore.insertQuantity(item.getItemId(), 1000);
		

		
		return successViewName;
	}
	
	@RequestMapping("/shop/itemUpdateSubmitted.do")
	public String onItemSubmit(
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			BindingResult result) throws Exception {
		
		//validator.validate(auctionForm, result);
		
		//if (result.hasErrors()) return formViewName;
		Item item = auctionForm.getAuctionItem();
		//item.setAuction(1);
		Product product = petStore.getProductByName(item.getProductId());
		item.setProductId(product.getProductId());
		//item.setStatus("P");
		
		petStore.updateItem(item);
		//petStore.insertQuantity(item.getItemId(), 1000);
		

		
		return successViewName;
	}
	

}
