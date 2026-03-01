package com.Grocery.Controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Grocery.model.*;
import org.springframework.ui.Model;

import com.Grocery.Service.ProductServiceImpl;
import com.Grocery.Service.userService;
import com.Grocery.Util.Sendmail;

@Controller
public class HomeController {
	@Autowired
	private ProductServiceImpl ss;

	@Autowired
	private userService service;

	@Autowired
	private Sendmail sendmail;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@ModelAttribute
	public void getUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = service.findByEmail(email);
			m.addAttribute("user", user);
		}
	}

	@GetMapping("/signin")
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

	// ------user--------//

	@PostMapping(value = "/register")
	public String saveUser(@ModelAttribute User user, Model model) {
		System.out.println(user);
		if (service.existsByEmail(user.getEmail())) {
			model.addAttribute("msg", "Email Already Exits");
			return "register";
		}
		if (service.existsByMobile(user.getMobile())) {
			model.addAttribute("msg", "Mobile Number Already Exits");
			return "register";
		}
		User u = service.save(user);
		if (u != null) {
			model.addAttribute("msg", "registration Successfull");
			return "login";
		} else {
			model.addAttribute("msg", "Something went wrong on server");
			return "register";
		}
	}

	@GetMapping(value = "/forget_password")
	public ModelAndView forget_password(ModelAndView m) {
		m.setViewName("forget_password");
		return m;
	}

	@PostMapping(value = "/forget_password")
	public String processForgatePassword(@RequestParam String email, RedirectAttributes ra) {
		User u = service.findByEmail(email);
		if (u == null) {
			ra.addFlashAttribute("msg", "Invalid UserName");
		} else {
			String string = UUID.randomUUID().toString();
			u.setToken(string);
			service.updateToken(string, u.getId());
			boolean rs = sendmail.sendMail(email, string);
			if (rs) {
				ra.addFlashAttribute("msg", "Verification Link is sent to Your Email");
			} else {
				ra.addFlashAttribute("msg", "Something Wrong on server");
			}

		}
		return "redirect:/forget_password";
	}

	@GetMapping("/reset_password")
	public ModelAndView reset_password(@RequestParam("token") String token, ModelAndView m) {
		System.out.println("TOKEN = " + token);
		User byToken = service.findByToken(token);
		if (byToken == null) {
			m.addObject("msg", "Link is invalid or expried");
		}
		m.addObject("token", token);
		m.setViewName("reset_password");
		return m;
	}

	@PostMapping("/reset_password")
	public ModelAndView update_Password(ModelAndView m, @RequestParam("token") String token,
			@RequestParam("password") String password) {

		System.out.println(password);
		User byToken = service.findByToken(token);
		String encode = passwordEncoder.encode(password);
		if (byToken == null) {
			m.addObject("msg", "Link is invalid or expried");
		}else {
			byToken.setToken(null);
			service.updatePassword(encode, byToken.getId());
			m.addObject("msg", "Password Update Successfully");
			m.setViewName("login");
		}
		return m;
	}

}
