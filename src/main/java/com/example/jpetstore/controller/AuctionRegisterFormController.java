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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.AccountFormValidator;
import com.example.jpetstore.service.OrderValidator;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
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
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			BindingResult result) throws Exception {
		
		//validator.validate(auctionForm, result);
		
		//if (result.hasErrors()) return formViewName;

		petStore.insertAuctionItem(auctionForm.getAuctionItem());

		
		return successViewName;
	}
	
	

}
