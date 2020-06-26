package com.example.jpetstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes({"product", "itemList"})
public class ViewProductController { 

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/shop/viewProduct.do")
	public String handleRequest(
			@RequestParam("productId") String productId,
			ModelMap model) throws Exception {
		PagedListHolder<Item> itemList;
		if(productId.equals("auction")) {
			itemList = new PagedListHolder<Item>(this.petStore.getItemListIsAuction());
		}else {
			
			itemList = new PagedListHolder<Item>(this.petStore.getItemListByProduct(productId));
			
		}
		
		itemList.setPageSize(15);
		Product product = this.petStore.getProduct(productId);

		//Product product = itemList.getPageList().get(0).getProduct();
		System.out.println(itemList.getPageList());


		
		model.put("itemList", itemList);
		model.put("product", product);
		return "Product";
		
	}
	
	@RequestMapping("/shop/viewProduct2.do")
	public String handleRequest2(
			@ModelAttribute("product") Product product,
			@ModelAttribute("itemList") PagedListHolder<Item> itemList,
			@RequestParam("pageNum") String page, ModelMap model
			) throws Exception {
		if ("next".equals(page)) {
			itemList.nextPage();
		}
		else if ("previous".equals(page)) {
			itemList.previousPage();
		}
		model.put("itemList", itemList);
		model.put("product", product);
		return "Product";
	}

}
