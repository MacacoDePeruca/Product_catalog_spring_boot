package com.br.vitornascimento.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.vitornascimento.data.model.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ActiveProfiles("test")
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;


	@Test
	public void shouldLoadAProductById() {
		long id = 1;

		Optional<Product> optionalProduct = repository.findById(id);

		assertNotNull(optionalProduct.get());
		assertEquals(id, optionalProduct.get().getId());

	}

	@Test
	public void shouldNotLoadAProductByInvalidID() {
		long id = 999;

		Optional<Product> optionalProduct = repository.findById(id);

		assertFalse(optionalProduct.isPresent());

	}

	@Test
	public void shouldLoadAProductListLargerThanOne() {
		

		List<Product> productList = repository.findAll();

		assertTrue((productList.size() > 1));

	}
	
	@Test
	public void shouldSearchAProductByParameters() {
		
		String nameOrDescription = "flour";
		BigDecimal minPrice = new BigDecimal(0.1);
		BigDecimal maxPrice = new BigDecimal(100.0);
		
		
		List<Product> productList = repository.searchProducts(nameOrDescription, minPrice, maxPrice);

		assertTrue((productList.size() >= 1));

	}
	
	@Test
	public void shouldSaveAProduct() {
		
		Product produto = new Product();
		produto.setDescription("ingrediente para guacamole");
		produto.setName("abacate");
		produto.setPrice(new BigDecimal(4.50));
	
		Product productReturned = repository.save(produto);
		
		assertTrue((productReturned.getId() != 0));
		

	}

}
