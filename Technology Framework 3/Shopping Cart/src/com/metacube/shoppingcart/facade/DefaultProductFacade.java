/**
 * @Date 02-October-2017
 * @author Rajat Jain
 * @version 1.0
 * @Project Shopping Cart implemented through Angular and Spring mvc

 */
package com.metacube.shoppingcart.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metacube.shoppingcart.dto.ProductDto;
import com.metacube.shoppingcart.model.Product;
import com.metacube.shoppingcart.service.ProductService;

/**
 * The Class DefaultProductFacade.
 */
@Component("productFacade")
public class DefaultProductFacade implements ProductFacade {

	/** The product service. */
	@Autowired
	private ProductService productService;

	/**
	 * Gets the product service.
	 *
	 * @return the product service
	 */
	public ProductService getProductService() {
		return productService;
	}

	/**
	 * Sets the product service.
	 *
	 * @param productService
	 *            the new product service
	 */
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Instantiates a new default product facade.
	 */
	public DefaultProductFacade() {
	}

	/**
	 * Instantiates a new default product facade.
	 *
	 * @param ProductService
	 *            the product service
	 */
	public DefaultProductFacade(ProductService ProductService) {
		this.productService = ProductService;
	}

	/*
	 * @see com.metacube.shoppingcart.facade.ProductFacade#getAllProducts()
	 */
	@Override
	public Iterable<ProductDto> getAllProducts() {
		List<ProductDto> productDtoList = new ArrayList();

		for (Product Product : productService.getAllProducts()) {
			productDtoList.add(modelToDto(Product));
		}

		return productDtoList;
	}

	/*
	 * @see com.metacube.shoppingcart.facade.ProductFacade#getProductById(int)
	 */
	@Override
	public ProductDto getProductById(final int id) {
		return modelToDto(productService.getProductById(id));
	}

	/**
	 * Delete by id.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	public boolean deleteById(final int id) {
		return (productService.deleteById(id));
	}

	/**
	 * Dto to model.
	 *
	 * @param productDto
	 *            the product dto
	 * @return the product
	 */
	protected Product dtoToModel(ProductDto productDto) {
		if (productDto == null) {
			return null;
		}
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());

		return product;
	}

	/**
	 * Model to dto.
	 *
	 * @param product
	 *            the product
	 * @return the product dto
	 */
	protected ProductDto modelToDto(Product product) {
		if (product == null) {
			return null;
		}
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());

		return productDto;
	}

}
