package com.Grocery.Admin_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Grocery.model.Product;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/addproduct")
	public String AddProduct() {
		return "admin/AddProduct";
	}

	@PostMapping("/addProductinDb")
	public String addProductinDb(@ModelAttribute Product product,
	                             @RequestParam("file") MultipartFile file,
	                             Model model) {

	    System.out.println(product);
	    System.out.println(file.getOriginalFilename());

	    model.addAttribute("msg", "hello");

	    return "admin/AddProduct";
	}


}
