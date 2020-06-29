package com.example.jpetstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes({"userSession", "itemList"})
public class SellerItemManageController {
	
	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping("/shop/SellerItemManage.do")
	public String handleRequest(
			@ModelAttribute("userSession") UserSession userSession,
			ModelMap model) throws Exception {
		PagedListHolder<Item> itemList;
		
		String nowUser = userSession.getAccount().getUsername();
	
		itemList = new PagedListHolder<Item>(this.petStore.getItemListByUsername(nowUser));
		itemList.setPageSize(5);
		
		System.out.println(itemList);
		
		model.put("itemList", itemList);
	
		return "SellerItemManage";
		
	}
	
	@RequestMapping("/shop/SellerItemManage2.do")
	public String handleRequest2(
			@RequestParam("page") String page,
			@ModelAttribute("itemList") PagedListHolder<Item> itemList,
			BindingResult result
			) throws Exception {
		if (itemList == null) {
			throw new IllegalStateException("Cannot find pre-loaded item list");
		}
		if ("next".equals(page)) { itemList.nextPage(); }
		else if ("previous".equals(page)) { itemList.previousPage(); }
		return "SellerItemManage";
	}
}
