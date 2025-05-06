package com.example.jpetstore.client.rest;

import java.net.URI;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;

import com.example.jpetstore.domain.Product;

public class ProductServiceClient {

	private static RestTemplate restTemplate = new RestTemplate();
	private static String host = "localhost";
	private static String port = "8088";
	private static String appContext = "/rest/product";
	private static String productSvcUrl = "http://" + host + ":" + port + appContext;

	public static void main(String[] args) {
		// test getProduct
		getForObjectByVariableArg("K9-BD-01", "K9-PO-02");
		getForObjectByVariableMap("FL-DLH-02");
		
		// test createProduct
		Product product = new Product();
		product.setProductId("FL-DOC-03");
		product.setCategoryId("CATS");
		product.setName("Maine Coon");
		product.setDescription("메인 쿤");
		String newProductUri = postForLocation(product);
		
		product = restTemplate.getForObject(newProductUri, Product.class);
		System.out.println("get the product by URI: " + product);
		
		// test updateProduct
		put("FL-DOC-03", "CATS", "Korean Shorthair", "<image src=\"../images/koreanCat.gif\">한국 고양이");
		
		product = restTemplate.getForObject(newProductUri, Product.class);
		System.out.println("get the product by URI: " + product);
		
		// test deleteProduct
		delete("FL-DOC-03");
		
		// test RestClientException
		catchException();
		
		// test RestTemplate.exchange()
		// exchange();				
	}

	private static void getForObjectByVariableArg(String prodId1, String prodId2) {
		System.out.println("\n[getForObjectByVariableArg]");
		String response = restTemplate.getForObject(
				productSvcUrl + "/{productId}",
				String.class, prodId1); 
		System.out.println("Response as a string: " + response);
		System.out.println();
		Product product = restTemplate.getForObject(
				productSvcUrl + "/{productId}", Product.class, prodId2);
		System. out.println("Response as an object: " + product);
	}

	private static void getForObjectByVariableMap(String prodId) {
		System.out.println("\n[getForObjectByVariableMap]");
		Map<String, Object> pathVariableMap = new HashMap<>();
		pathVariableMap.put("productId", prodId);

		Product product2 = restTemplate.getForObject(
				productSvcUrl + "/{productId}",
				Product.class,
				pathVariableMap);
		System.out.println("Response as an object: " + product2);
	}

	private static String postForLocation(Product product) {
		System.out.println("\n[postForLocation]");
		URI uri = restTemplate.postForLocation(productSvcUrl, product);
		String uriStr = uri.toString();		
		System.out.println(product + " created.");
		System.out.println("New Product URI: " + uriStr);
		return uriStr;
	}

	private static void put(String prodId, String categoryId, String name, String desc) {
		System.out.println("\n[put]");
		Product product = new Product();
		product.setProductId(prodId);
		product.setCategoryId(categoryId);
		product.setName(name);
		product.setDescription(desc);
		restTemplate.put(productSvcUrl + "/{productId}", product, product.getProductId());
		System.out.println(product + " updated.");
	}
	
	private static void delete(String prodId) {
		System.out.println("\n[delete]");
		restTemplate.delete(productSvcUrl + "/{productId}", prodId);
		System.out.println("product " + prodId + " deleted.");
	}
	
	private static void catchException() {
		System.out.println("\n[catchException]");
		System.out.println("Product [productId=K9-BD-11] 요청 (false ID)");

		try {
			restTemplate.getForObject(
					productSvcUrl + "/{productId}",
					String.class, "K9-BD-11");
		} catch (RestClientException e) {
			System.out.println("RestClientException has been caught.");
			// e.printStackTrace();
		}
	}
/*	
	private static void exchange() {
		System.out.println("\n[exchange]");

		// getForEntity를 exchange를 이용해서 구현한 코드
		URI uri = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host(host)
				.port(port)
				.path(appContext + "/{productId}")
				.build()
				.expand("K9-BD-01").encode()
				.toUri();
		
		ResponseEntity<Product> itemResponse = restTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				Product.class);
		
		Product product = itemResponse.getBody();
		System.out.println("Response as an object: " + product);

		// postForLocation을 exchange를 이용해서 구현한 코드
		Product product2 = new Product();
		product2.setProductId("FL-DRD-04");
		product2.setCategoryId("CATS");
		product2.setName("Ragdoll");
		product2.setDescription("렉돌");		
		HttpEntity<Product> requestEntity = new HttpEntity<Product>(product2);
		
		ResponseEntity<Void> postResponse = restTemplate.exchange(
				productSvcUrl,
				HttpMethod.POST, 
				requestEntity, 
				Void.class);
		
		URI newProductUri = postResponse.getHeaders().getLocation();
		System.out.println(product2 + " created.");
		System.out.println("New Product URI: " + newProductUri);
	}
*/	
}
