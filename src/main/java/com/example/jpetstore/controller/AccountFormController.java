package com.example.jpetstore.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.AccountFormValidator;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Controller
@RequestMapping({"/shop/newAccount.do","/shop/editAccount.do"})
public class AccountFormController { 

	@Value("EditAccountForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	private static final String[] LANGUAGES = {"english", "japanese"};
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@Autowired
	private AccountFormValidator validator;
	public void setValidator(AccountFormValidator validator) {
		this.validator = validator;
	}
		
	@ModelAttribute("accountForm")
	public AccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			return new AccountForm(
				petStore.getAccount(userSession.getAccount().getUsername()));
		}
		else {	// create a new account
			return new AccountForm();
		}
	}

	@ModelAttribute("languages")
	public String[] getLanguages() {
		return LANGUAGES;
	}

	@ModelAttribute("categories")
	public List<Category> getCategoryList() {
		return petStore.getCategoryList();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) throws Exception {

		if (request.getParameter("account.listOption") == null) {
			accountForm.getAccount().setListOption(false);
		}
		if (request.getParameter("account.bannerOption") == null) {
			accountForm.getAccount().setBannerOption(false);
		}
		
		validator.validate(accountForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
			if (accountForm.isNewAccount()) {
				petStore.insertAccount(accountForm.getAccount());
			}
			else {
				petStore.updateAccount(accountForm.getAccount());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.username", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		UserSession userSession = new UserSession(
			petStore.getAccount(accountForm.getAccount().getUsername()));
		PagedListHolder<Product> myList = new PagedListHolder<Product>(
			petStore.getProductListByCategory(accountForm.getAccount().getFavouriteCategoryId()));
		myList.setPageSize(4);
		userSession.setMyList(myList);
		session.setAttribute("userSession", userSession);
		return successViewName;  
	}
}
