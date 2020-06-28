package com.example.jpetstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
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
	/*
	@RequestMapping("/shop/updateAuction.do")
	public String showForm(Model model) {
		 model.addAttribute("auctionForm", new AuctionForm());
		return formViewName;
	}*/
	
	@RequestMapping("/shop/updateAuction.do")
	public ModelAndView handleRequest(@RequestParam("itemId") String itemId) throws Exception {
		Item item = this.petStore.getItem(itemId);
		return new ModelAndView("AuctionUpdateForm", "auctionItem", item);
	}
	
	@RequestMapping("/shop/auctionUpdateSubmitted.do")
	public String onSubmit(
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			BindingResult result) throws Exception {
		
		//validator.validate(auctionForm, result);
		
		//if (result.hasErrors()) return formViewName;
		Item item = auctionForm.getAuctionItem();
		item.setAuction(1);
		Product product = petStore.getProductByName(item.getProductId());
		item.setProductId(product.getProductId());
		//item.setDeposit(item.getListPrice()/10);
		//item.setStatus("P");
		
		petStore.updateAuctionItem(item);
		//petStore.insertQuantity(item.getItemId(), 1000);
		

		
		return successViewName;
	}
	
	

}
