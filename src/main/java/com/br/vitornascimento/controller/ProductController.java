package com.br.vitornascimento.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.vitornascimento.data.vo.v1.ProductVo;
import com.br.vitornascimento.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ProductVo findById(@PathVariable(value = "id") Long id) {

		ProductVo product = service.findById(id);

		return product;
	}

	@GetMapping(produces = "application/json")
	public List<ProductVo> findAll() {

		List<ProductVo> products = service.findAll();

		return products;

	}

	@GetMapping(path = "/search", produces = "application/json")
	public List<ProductVo> SearchProducts(@RequestParam(value = "q", required = false, defaultValue = "") String nameOrDescription,
			@RequestParam(value = "min_price", required = false, defaultValue = "0") BigDecimal minPrice,
			@RequestParam(value = "max_price", required = false, defaultValue = "99999999") BigDecimal maxPrice) {

		List<ProductVo> filtredProduct = service.searchProducts(nameOrDescription, minPrice, maxPrice);

		return filtredProduct;
	}

	@PostMapping(produces = "application/json")
	public ProductVo postProduct(@RequestBody ProductVo productVo) {

		ProductVo product = service.save(productVo);

		return product;

	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ProductVo update(@PathVariable(value = "id") Long id, @RequestBody ProductVo productVo) {

		ProductVo product = service.update(id, productVo);

		return product;
	}

	@DeleteMapping(value = "/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {

		service.deleteProduct(id);
	}

}
