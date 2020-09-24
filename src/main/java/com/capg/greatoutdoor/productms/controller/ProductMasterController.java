package com.capg.greatoutdoor.productms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.greatoutdoor.productms.exceptions.ProductAlreadyExistsException;
import com.capg.greatoutdoor.productms.exceptions.ProductDetailNotfoundException;
import com.capg.greatoutdoor.productms.exceptions.ProductNotfoundException;
import com.capg.greatoutdoor.productms.model.ProductDto;
import com.capg.greatoutdoor.productms.service.ProductMsService;
@RestController /* mapping the request data to the requesthandlermethod (which converts into JSON)*/

@RequestMapping("/productmaster")/* which maps https requests handlermethods of MVC and RestControllers*/

public class ProductMasterController {
	@Autowired /*inject the object dependency, without creating object for a class autowire will create a refernce */
	private ProductMsService productService;
	@GetMapping("/getallproducts")/*  to map HTTP GET requests onto specific handler methods requestmethod.get*/
	List<ProductDto> viewAllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping("/addproduct/{userId}")/* @requestmapping= requestmethod.post*/
	String addProduct(@PathVariable String userId,@RequestBody ProductDto product) throws ProductAlreadyExistsException {
		/* which is used to bind http request with object in return type*/
		String status = "product added";
		
		
		if(productService.addProduct(userId,product)) {
			return status;
		}
		
		return "failed to add product!";
		
	}
	
	@DeleteMapping("/deleteproduct/productId/{userId}/{productId}")
	String deleteProduct(@PathVariable String userId,@PathVariable String productId) throws ProductNotfoundException{ /*name of the pathvariable is required method parameter
	is to bound the URL */
		if(productService.deleteProduct(userId,productId)) {
			return "product deleted!";
		}
		return "error";
	}
	
	@PutMapping("/updateproduct")
	String editProduct(@RequestBody ProductDto product) {
		String status = "product updated";
		
		if(productService.editProduct(product)) {
			return status;
		}
		
		return "fail to update product!";
		
	}
	@GetMapping("/searchproduct/productDetail/{productDetail}")
	public List<ProductDto> searchProduct(@PathVariable String productDetail)
	{
		return productService.searchProduct(productDetail);
		
	}
	@GetMapping("/get/productId/{productId}")
	public ProductDto getProduct(@PathVariable String productId)
	{
		return productService.getProduct(productId);
	}
	@GetMapping("/get/{userId}")
	public List<ProductDto> getProductByUserId(@PathVariable String userId)
	{
		return productService.getProductByUserId(userId);
	}
}