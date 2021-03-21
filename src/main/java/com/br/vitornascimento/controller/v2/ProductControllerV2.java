package com.br.vitornascimento.controller.v2;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v2/products")
public class ProductControllerV2 {

	@Autowired
	ProductService service;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ProductVo findById(@PathVariable(value = "id") Long id) {

		ProductVo product = service.findById(id);

		product.add(linkTo(methodOn(ProductControllerV2.class).findById(id)).withSelfRel());

		return product;
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<PagedResources<ProductVo>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {

		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

		Page<ProductVo> products = service.findAll(pageable);
		
		products.stream().forEach(p ->{
			p.add(linkTo(methodOn(ProductControllerV2.class).findById(p.getKey())).withSelfRel());
		});

		return new ResponseEntity<>(assembler.toResource(products), HttpStatus.OK);

	}


	@PostMapping(produces = "application/json")
	public ProductVo postProduct(@RequestBody ProductVo productVo) {

		ProductVo product = service.save(productVo);
		
		product.add(linkTo(methodOn(ProductControllerV2.class).findById(product.getKey())).withSelfRel());

		return product;

	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ProductVo update(@PathVariable(value = "id") Long id, @RequestBody ProductVo productVo) {

		ProductVo product = service.update(id, productVo);
		
		product.add(linkTo(methodOn(ProductControllerV2.class).findById(product.getKey())).withSelfRel());

		return product;
	}

	@DeleteMapping(value = "/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {

		service.deleteProduct(id);
	}

}
