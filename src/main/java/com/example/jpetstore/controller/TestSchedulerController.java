package com.example.jpetstore.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.service.PetStoreFacade;

@Controller
public class TestSchedulerController { 

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/shop/testScheduler.do")
	public ModelAndView handleRequest(HttpServletRequest request,
		@RequestParam("keyword")
		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date closeTime) throws Exception {
		System.out.println(closeTime);
		petStore.testScheduler(closeTime);
		return new ModelAndView("Scheduled", "closeTime", closeTime);	
	}
}
