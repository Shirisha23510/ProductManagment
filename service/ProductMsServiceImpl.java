package com.capg.greatoutdoor.productms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.greatoutdoor.productms.exceptions.ProductAlreadyExistsException;
import com.capg.greatoutdoor.productms.exceptions.ProductDetailNotfoundException;
import com.capg.greatoutdoor.productms.exceptions.ProductNotfoundException;

import com.capg.greatoutdoor.productms.model.ProductDto;
import com.capg.greatoutdoor.productms.repository.ProductMsRepository;
@Service /* to perform service tasks */
public class ProductMsServiceImpl implements ProductMsService{
	@Autowired /*inject the object dependency, without creating object for a class autowire will create a refernce */
	ProductMsRepository productRepository;


	
	@Override
	public List<ProductDto> getAllProducts() {
		return (List<ProductDto>) productRepository.findAll();
	}

	@Override
	public boolean addProduct(ProductDto product) throws ProductAlreadyExistsException   {
		if(productRepository.existsById(product.getProductId()))
		{
			throw  new ProductAlreadyExistsException("Product Already Exists");
		}
		if(productRepository.existsByProductName(product.getProductName()))
		{
			throw  new ProductAlreadyExistsException("ProductName Already Exists");
		}
		
		productRepository.save(product);
		return true;
	}

	@Override
	public boolean editProduct(ProductDto product) {
		//Option is a data type 
		if(productRepository.existsById(product.getProductId()))
		{
			ProductDto existingProduct=productRepository.getOne(product.getProductId());
			if(product.getColour()==null)
			{
				existingProduct.setColour(existingProduct.getColour());
			}
			else
			{
				existingProduct.setColour(product.getColour());
			}
			if(product.getDimension()==null)
			{
				existingProduct.setDimension(existingProduct.getDimension());
			}
			else
			{
				existingProduct.setDimension(product.getDimension());
			}
			if(product.getPrice()==0)
			{
				existingProduct.setPrice(existingProduct.getPrice());
			}
			else
			{
				existingProduct.setPrice(product.getPrice());
			}
			productRepository.saveAndFlush(existingProduct);
			System.out.println(existingProduct);
			return true;
		}
		 
		return false;
	}

	@Override
	public boolean deleteProduct(String productId) throws ProductNotfoundException {
		if(productRepository.existsById(productId))
		{
			productRepository.deleteById(productId);
			return true;
		}
		else
		{
			throw new ProductNotfoundException("ProductId doest not exist");
		}}

	@Override
	public List<ProductDto> searchProduct(String productDetail)  {
		// TODO Auto-generated method stub
		
		List<ProductDto> productList=new ArrayList<>();
		if(productRepository.findByProductName(productDetail)==null)
		{
			throw new RuntimeException("Please enter correct details");
		}
		else
		{
			List<ProductDto> allProductsList=productRepository.findAll();
			for (ProductDto productDto : allProductsList) {
			if(productDto.getProductName().equals(productDetail))
			{
				productList.add(productDto);
			}
		}
			
		return productList;	
		}
	}
	}
