package com.capg.greatoutdoor.productms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.greatoutdoor.productms.exceptions.ProductDetailNotfoundException;
import com.capg.greatoutdoor.productms.model.ProductDto;
import com.capg.greatoutdoor.productms.service.ProductMsService;
@RestController
@RequestMapping("/retailer")
public class RetailerController {
	@Autowired
	private ProductMsService productService;
	public List<ProductDto> searchProduct(@PathVariable String productDetail) 
	{
		return productService.searchProduct(productDetail);
	}
}