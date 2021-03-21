package com.br.vitornascimento.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.br.vitornascimento.data.model.Product;
import com.br.vitornascimento.data.vo.v1.ProductVo;
import com.br.vitornascimento.exception.ProductNotfoundByIdException;
import com.br.vitornascimento.repository.ProductRepository;


public class ProductServiceTest {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void shouldReturnAllProductsList() {

		Mockito.when(repository.findAll()).thenReturn(products());

		List<ProductVo> returnedProducts = service.findAll();

		assertTrue(returnedProducts.size() > 0);

		Mockito.verify(repository).findAll();

	}

	@Test
	public void shouldReturnOneProductByid() {

		long id = 1;

		Mockito.when(repository.findById(id)).thenReturn(Optional.of(mockProduct()));

		ProductVo returnedProduct = service.findById(id);

		assertTrue(returnedProduct != null);

		Mockito.verify(repository).findById(id);

	}

	@Test
	public void shouldThrowsExceptionNotFoundProductById() {

		Mockito.when(repository.findById(Mockito.any())).thenThrow(ProductNotfoundByIdException.class);

		try {
			service.findById(Mockito.anyLong());
			Mockito.verifyZeroInteractions(repository);

		} catch (ProductNotfoundByIdException e) {

		}

	}
	@Test
	public void shouldSearchProductsByParam() {
		
		Mockito
		.when(repository.searchProducts(Mockito.anyString(), Mockito.any(), Mockito.any()))
		.thenReturn(products());
		

		List<ProductVo>	list = service.searchProducts("AL", new BigDecimal(1), new BigDecimal(100));
			
			assertFalse(list.isEmpty());
			
			
			Mockito.verify(repository).searchProducts(Mockito.any(), Mockito.any(), Mockito.any());
			

		
	}

	private Product mockProduct() {

		Product laranja = new Product();
		laranja.setDescription("rica em vitamina C");
		laranja.setId(1);
		laranja.setName("laranja");
		laranja.setPrice(new BigDecimal(1.54));

		return laranja;
	}

	private List<Product> products() {
		List<Product> produtos = new ArrayList<Product>();

		Product laranja = new Product();
		Product banana = new Product();
		Product maca = new Product();

		laranja.setDescription("rica em vitamina C");
		laranja.setId(1);
		laranja.setName("laranja");
		laranja.setPrice(new BigDecimal(1.54));

		banana.setDescription("rica em Potássio");
		banana.setId(2);
		banana.setName("banana");
		banana.setPrice(new BigDecimal(0.99));

		maca.setDescription("nacionalidade argentina");
		maca.setId(3);
		maca.setName("maçã");
		maca.setPrice(new BigDecimal(1.99));

		produtos.add(maca);
		produtos.add(banana);
		produtos.add(laranja);
		return produtos;
	}

}
