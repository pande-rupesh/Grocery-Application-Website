package com.Grocery.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
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
	
	@GetMapping("/product")
	public String Product()
	{
			
		return "product";
	}
	
	@GetMapping("/productDetails")
	public String ProductDetails()
	{
			
		return "ProductDetails";
	}
}
