package com.nimap.MyTask.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.MyTask.entity.categories;
import com.nimap.MyTask.entity.products;

@RestController
@RequestMapping("/api")
public class CategoryController 
{
	@Autowired
	SessionFactory factory;
	
	@GetMapping("categories/page")
	List<categories> getAllCategories() {
		Session session = factory.openSession();
		Query query = session.createQuery("from categories");
		return query.list();
	}
	
	@PostMapping("categories")
	public String createCategory(@RequestBody categories category)
	{
		Session session = factory.openSession();
		 
		session.persist(category);
		session.beginTransaction().commit();
		return "record saved";
	}
	
	@GetMapping("categories/{di}")
	categories getCategory(@PathVariable("di") int di)
	{
		Session session = factory.openSession();
		Query query = session.createQuery("from categories where categoryID=:di");
		query.setParameter("di", di);
		List<categories> list = query.list();
		categories category = list.get(0);
		return category;
	}
	
	@PutMapping("categories/{di}")
	public String updateCategory(@PathVariable("di") int di , @RequestBody categories c) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		categories category = session.get(categories.class, di);
		
		category.setCategoryName(c.getCategoryName());
		session.update(category);
		tx.commit();
		return "done";
	}
	
	@DeleteMapping("categories/{di}")
	public String deleteCategory(@PathVariable int di)
	{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		categories category = session.get(categories.class,di);
		session.delete(category);
		tx.commit();
		return "done";
	}
}
