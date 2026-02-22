package com.Grocery.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import com.Grocery.model.*;
import com.Grocery.Service.ProductServiceImpl;

@Controller
public class HomeController {
	@Autowired
	private ProductServiceImpl ss;
	
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/login")
	public String login()
	{
			System.out.println("hello");
		return "login";
	}
	
	@GetMapping("/register")
	public String Register()
	{
			System.out.println("hello");
		return "register";
	}
	
	@GetMapping("/product/{categoryName}")
	public ModelAndView  Product(@PathVariable String categoryName,ModelAndView m)
	{
		List<Product> p=ss.findByCategoryIgnoreCase(categoryName);
		System.out.println(p);
			System.out.println(categoryName);
		m.addObject("products",p);
		m.setViewName("product");
		return m;
	}
	
	@GetMapping("/productDetails/{id}")
	public ModelAndView ProductDetails(@PathVariable int id,ModelAndView m)
	{
		Product p=ss.findById(id);
		System.out.println(p);
		m.addObject("product",p);
		m.setViewName("ProductDetails");
		return m;
	}
}
