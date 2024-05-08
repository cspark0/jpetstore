package com.example.jpetstore.controller.rest;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.ProductService;

/**
 * @author Chang-Sup Park
 */

@RestController
@RequestMapping("/rest")
public class RestProductController {
	private ProductService productSvc;
	
	@Value("${server.port}")
	private String serverPort;

	@Autowired
	public void setProductSvc(ProductService productService) {
		this.productSvc = productService;
	}
	
	@GetMapping(value="/product/{prodId}", produces="application/json")
	// @ResponseBody
	public Product getProduct(@PathVariable("prodId") String prodId, HttpServletResponse response)
			throws IOException {
		System.out.println("GET /rest/product/{prodId} request accepted: {prodId} = " + prodId);
		Product product = productSvc.getProduct(prodId);
		System.out.println(product);
		if (product == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return product;
	}

	@PostMapping(value="/product", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody Product product, HttpServletResponse response) throws IOException {
		System.out.println("POST /rest/product request accepted with a product: " + product);		

		product.setProductId(product.getProductId().toLowerCase());
		
		if (productSvc.getProduct(product.getProductId()) != null) {
			response.sendError(HttpServletResponse.SC_CONFLICT);
			return;
		}
        productSvc.createProduct(product);		 
        String ipAddr = InetAddress.getLocalHost().getHostAddress();
		UriComponents uriComp = UriComponentsBuilder
									.newInstance()
									.scheme("http")
									.host(ipAddr)
									.port(serverPort)
									.path("/rest/product/{prodId}")
									.build();
		UriComponents encodedUriComp = uriComp.expand(product.getProductId()).encode();
		response.setHeader("Location", encodedUriComp.toUriString());
		
		System.out.println("product " + product.getProductId() + " created.");
	}

	@PutMapping(value="/product/{prodId}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct(@PathVariable("prodId") String prodId, 
			@RequestBody Product product, HttpServletResponse response) throws IOException {
		System.out.println("PUT /rest/product/{prodId} request accepted: {prodId} = " + prodId + ", product = " + product);
		if (productSvc.getProduct(prodId) == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		productSvc.updateProduct(prodId, product);
		System.out.println("product " + prodId + " updated.");
	}

	@DeleteMapping(value="/product/{prodId}")
	@ResponseStatus(HttpStatus.OK)
	// @ResponseBody
	public void deleteProduct(@PathVariable("prodId") String prodId, HttpServletResponse response)
			throws IOException {
		System.out.println("DELETE /rest/product/{prodId} request accepted: {prodId} = " + prodId);

		String prodId2 = prodId.toLowerCase();

		if (productSvc.getProduct(prodId2) == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		productSvc.removeProduct(prodId2);
		System.out.println("product " + prodId2 + " deleted.");
	}
}
