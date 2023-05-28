package com.sandeep.retailstore;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sandeep.retailstore.entity.Product;
import com.sandeep.retailstore.entity.User;
import com.sandeep.retailstore.model.ProductType;
import com.sandeep.retailstore.model.UserType;
import com.sandeep.retailstore.repository.ProductRepository;
import com.sandeep.retailstore.repository.UserRepository;

@SpringBootApplication
public class RetailstoreApplication {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	void init() {
		fillProductTable();
		fillUserTable();
	}

	public static void main(String[] args) {
		SpringApplication.run(RetailstoreApplication.class, args);
	}

	public void fillProductTable() {
		Product product = new Product();
		product.setId(1l);
		product.setName("Cricket bat");
		product.setType(ProductType.OTHER);
		product.setUnitPrice(1200);
		
		Product product2 = new Product();
		product2.setId(2l);
		product2.setName("Cricket ball");
		product2.setType(ProductType.OTHER);
		product2.setUnitPrice(100);
		
		Product product3 = new Product();
		product3.setId(3l);
		product3.setName("Salt");
		product3.setType(ProductType.GROCERY);
		product3.setUnitPrice(50);
		
		Product product4 = new Product();
		product4.setId(4l);
		product4.setName("Rice");
		product4.setType(ProductType.GROCERY);
		product4.setUnitPrice(250);
		
		productRepository.save(product);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);


	}

	private void fillUserTable() {
		User user = new User();
		user.setId(9584665551l);
		user.setName("Sandeep Patidar");
		user.setType(UserType.STORE_EMPLOYEE);
		user.setCreatedAt(LocalDateTime.now());
		
		User user2 = new User();
		user2.setId(9584665552l);
		user2.setName("Sandeep Patel");
		user2.setType(UserType.STORE_AFFILIATE);
		user2.setCreatedAt(LocalDateTime.now());
		
		User user3 = new User();
		user3.setId(9584665553l);
		user3.setName("Sandeep Rajpoot");
		user3.setType(UserType.OTHER);
		user3.setCreatedAt(LocalDateTime.of(2020, 02, 12, 2, 22));
		
		userRepository.save(user);
		userRepository.save(user2);
		userRepository.save(user3);

	}

}
