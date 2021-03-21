package com.br.vitornascimento.services;

import static com.br.vitornascimento.converter.DozerConverter.parseObject;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.vitornascimento.converter.DozerConverter;
import com.br.vitornascimento.data.model.Product;
import com.br.vitornascimento.data.vo.v1.ProductVo;
import com.br.vitornascimento.exception.InvalidProductFormatException;
import com.br.vitornascimento.exception.ProductNotfoundByIdException;
import com.br.vitornascimento.exception.ResourceNotfoundException;
import com.br.vitornascimento.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public ProductVo findById(long id) {

		return parseObject(
				repository.findById(id).orElseThrow(() -> new ProductNotfoundByIdException()),
				ProductVo.class);
	}

	public List<ProductVo> findAll() {

		List<Product> products = repository.findAll();

		return DozerConverter.parseListObjects(products, ProductVo.class);
	}

	public ProductVo save(ProductVo productVo) {

		if (productVo.getPrice() == null || productVo.getDescription().isEmpty() || productVo.getName().isEmpty()) {

			throw new InvalidProductFormatException("invalid format of product");
		}

		Product product = repository.save(DozerConverter.parseObject(productVo, Product.class));

		return DozerConverter.parseObject(product, ProductVo.class);
	}

	public ProductVo update(Long id, ProductVo productVo) {

		if (!repository.existsById(id)) {

			throw new ResourceNotfoundException("couldn't find the product");

		}

		return save(productVo);

	}

	public List<ProductVo> searchProducts(String nameOrDescription, BigDecimal minPrice, BigDecimal maxPrice) {
		
		List<Product> products = repository.searchProducts(nameOrDescription, minPrice, maxPrice);
		
		return DozerConverter.parseListObjects(products, ProductVo.class);
	}

	public void deleteProduct(Long id) {

		ProductVo product = findById(id);		
		
		repository.delete(DozerConverter.parseObject(product, Product.class));
		
	}

	public Page<ProductVo> findAll(Pageable pageable) {
		
		var page = repository.findAll(pageable);
		
		return page.map(this::convertToPersonVo);
	}
	
	private ProductVo convertToPersonVo(Product entity) {

		return DozerConverter.parseObject(entity, ProductVo.class);
	}

}
