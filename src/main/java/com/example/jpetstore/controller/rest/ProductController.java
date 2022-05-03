package com.example.jpetstore.controller.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.ProductService;

/**
 * @author Chang-Sup Park
 */

@RestController
@RequestMapping("/rest")
public class ProductController {
	private ProductService productSvc;

	@Autowired
	public void setProductSvc(ProductService productService) {
		this.productSvc = productService;
	}
	
	@GetMapping(value = "/product/{prodId}") //, produces = "application/json")
	// @ResponseBody
	public Product getProduct(@PathVariable("prodId") String prodId, HttpServletResponse response)
			throws IOException {
		Product product = productSvc.getProduct(prodId);
		System.out.println(product);
		if (product == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return product;
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody Product product, HttpServletResponse response) {
		productSvc.createProduct(product);
		UriComponents uriComp = UriComponentsBuilder
									.newInstance()
									.scheme("http")
									.host("localhost")
									.port(8080)
									.path("/jpetstore/product/{prodId}")
									.build();
		UriComponents encodedUriComp = uriComp.expand(product.getProductId()).encode();
		response.setHeader("Location", encodedUriComp.toUriString());
		System.out.println("product " + product.getProductId() + " created.");
	}

	@RequestMapping(value = "/product/{prodId}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct(@PathVariable("prodId") String prodId, 
			@RequestBody Product product, HttpServletResponse response) throws IOException {
		if (productSvc.getProduct(prodId) == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		productSvc.updateProduct(prodId, product);
		System.out.println("product " + prodId + " updated.");
	}

	@RequestMapping(value = "/product/{prodId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	// @ResponseBody
	public Product deleteProduct(@PathVariable("prodId") String prodId, HttpServletResponse response)
			throws IOException {
		Product product = productSvc.removeProduct(prodId);
		if (product == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		System.out.println("product " + product.getProductId() + " deleted.");
		return product;
	}

}
