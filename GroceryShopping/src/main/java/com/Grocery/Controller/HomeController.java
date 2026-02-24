package com.Grocery.Controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.Grocery.model.*;
import org.springframework.ui.Model;

import com.Grocery.Service.ProductServiceImpl;
import com.Grocery.Service.userService;

@Controller
public class HomeController {
	@Autowired
	private ProductServiceImpl ss;
	
	@Autowired
	private userService service;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		System.out.println("hello");
		return "login";
	}

	@GetMapping("/register")
	public String Register() {
		System.out.println("hello");
		return "register";
	}

	@GetMapping("/product/{categoryName}")
	public ModelAndView Product(@PathVariable String categoryName, ModelAndView m) {
		List<Product> products = ss.findByCategoryIgnoreCase(categoryName);

		m.addObject("products", products);
		m.addObject("categoryName", categoryName);
		m.setViewName("product");
		return m;
	}

	@GetMapping(value = "/ProductSort/{categoryName}")
	public ModelAndView productSort(ModelAndView m, @PathVariable String categoryName,
			@RequestParam(required = false) String sort) {
		List<Product> products = ss.findByCategoryIgnoreCase(categoryName);
		if ("1".equals(sort)) { // Price: Low to High
			products.sort(Comparator.comparing(Product::getPrice));
		} else if ("2".equals(sort)) { // Price: High to Low
			products.sort(Comparator.comparing(Product::getPrice).reversed());
		}
		m.addObject("products", products);
		m.addObject("categoryName", categoryName); // for page heading
		m.addObject("sort", sort); // to retain selected dropdown value in Thymeleaf
		m.setViewName("product");
		return m;
	}

	@GetMapping("/productDetails/{id}")
	public ModelAndView ProductDetails(@PathVariable int id, ModelAndView m) {
		Product p = ss.findById(id);
		System.out.println(p);
		m.addObject("product", p);
		m.setViewName("ProductDetails");
		return m;
	}
	
	//------user--------//
	
	@PostMapping(value = "/register")
	public String saveUser(@ModelAttribute User user, Model model)
	{
		System.out.println(user);
		if(service.existsByEmail(user.getEmail()))
		{
			model.addAttribute("msg","Email Already Exits");
			return "register";
		}
		if(service.existsByMobile(user.getMobile())) {
			model.addAttribute("msg","Mobile Number Already Exits");
			return "register";
		}
		User u=service.save(user);
		if (u!=null) {
			model.addAttribute("msg","registration Successfull");
			return "login";
		}else
		{
			model.addAttribute("msg","Something went wrong on server");
			return "register";
		}
	}

}
