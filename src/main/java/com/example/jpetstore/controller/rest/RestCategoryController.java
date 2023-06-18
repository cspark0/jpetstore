package com.example.jpetstore.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Chang-Sup Park
 */

@RestController
@RequestMapping("/rest")
public class RestCategoryController {
	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@GetMapping(value = "/category/{cateId}", produces = "application/json")
	public Category getCategory(@PathVariable String cateId, HttpServletResponse response)
			throws IOException {
		System.out.println("GET /rest/category/{cateId} request accepted: {cateId} = " + cateId);
		Category category = petStore.getCategory(cateId);
		System.out.println(category);
		if (category == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return category;
	}
}
