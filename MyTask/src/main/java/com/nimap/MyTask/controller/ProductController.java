package com.nimap.MyTask.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.MyTask.entity.categories;
import com.nimap.MyTask.entity.products;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	SessionFactory factory;

	@GetMapping("products/page")
	List<products> getAllProducts() {
		Session session = factory.openSession();
		Query query = session.createQuery("from products");
		return query.list();
	}

	@PostMapping("products")
	public String createProduct(@RequestBody products product) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(product);
		tx.commit();
		return "done";
	}

	@GetMapping("products/{di}")
	products getProducts(@PathVariable("di") int id) {
		Session session = factory.openSession();
		products products = session.get(products.class, id);
		
		products p = new products();
		categories c = new categories();
		c.setCategoryID(products.getCategory().getCategoryID());
		c.setCategoryName(products.getCategory().getCategoryName());
		p.setProductID(products.getProductID());
		p.setProductName(products.getProductName());
		p.setCategory(c);
		
		
		return p;
	}

	@PutMapping("products/{di}")
	public String updateProduct(@PathVariable int di , @RequestBody products p) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		products products = session.get(products.class, di);
		
		products.setProductName(p.getProductName());
		
		session.update(products);
		tx.commit();
		return "done";
	}

	@DeleteMapping("products/{di}")
	public String deleteProduct(@PathVariable int di) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		products product = session.get(products.class, di);
		session.delete(product);
		tx.commit();
		return "done";

	}
}
