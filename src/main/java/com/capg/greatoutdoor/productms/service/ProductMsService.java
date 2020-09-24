package com.capg.greatoutdoor.productms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.capg.greatoutdoor.productms.exceptions.ProductAlreadyExistsException;
import com.capg.greatoutdoor.productms.exceptions.ProductDetailNotfoundException;
import com.capg.greatoutdoor.productms.exceptions.ProductNotfoundException;

import com.capg.greatoutdoor.productms.model.ProductDto;
import com.capg.greatoutdoor.productms.model.ProductMaster;
import com.capg.greatoutdoor.productms.repository.ProductMsRepository;

public interface ProductMsService {
	public List<ProductDto> getAllProducts();
	public boolean addProduct(String userId,ProductDto product) throws ProductAlreadyExistsException;
	public boolean editProduct(ProductDto product);
	public boolean deleteProduct(String userId,String productId) throws ProductNotfoundException;
	public List<ProductDto> searchProduct(String productDetail) ;
	public ProductMaster addMaster(ProductMaster master);
	public void setProductId(String userId,String productId);
	public void removeProductId(String userId,String productId);
	public List<ProductDto> getProductByUserId(String userId);
	public ProductDto getProduct(String productId);
}