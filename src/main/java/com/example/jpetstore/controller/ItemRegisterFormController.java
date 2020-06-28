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
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.AccountFormValidator;
import com.example.jpetstore.service.OrderValidator;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes("userSession")
public class ItemRegisterFormController {

	
	@Value("ItemRegisterForm")
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
	
	@RequestMapping("/shop/itemRegister.do")
	public String showForm(Model model) {
		 model.addAttribute("itemForm", new ItemForm());
		return formViewName;
	}
	
	@RequestMapping("/shop/itemRegisterSubmitted.do")
	public String onSubmit(
			HttpServletRequest request,
			@ModelAttribute("itemForm") ItemForm itemForm,
			BindingResult result) throws Exception {
		
		//validator.validate(auctionForm, result);
		
		//if (result.hasErrors()) return formViewName;
		Item item = itemForm.getItem();
		item.setAuction(0);
		Product product = petStore.getProductByName(item.getProductId());
		item.setProductId(product.getProductId());
		item.setStatus("P");
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		item.setUsername2(userSession.getAccount().getUsername());
		System.out.println("?"+userSession.getAccount().getUsername());
		
		petStore.insertItem(item);
		petStore.insertQuantity(item.getItemId(), 10000);
		

		
		return successViewName;
	}
	
	

}