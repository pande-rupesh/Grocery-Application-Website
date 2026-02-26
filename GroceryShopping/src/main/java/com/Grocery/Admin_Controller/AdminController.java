package com.Grocery.Admin_Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Grocery.Service.ProductServiceImpl;
import com.Grocery.Service.userService;
import com.Grocery.model.Product;
import com.Grocery.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ProductServiceImpl ss;
	
	@Autowired
	private userService service;

	@GetMapping("/")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/addproduct")
	public String AddProduct() {
		return "admin/AddProduct";
	}

	@PostMapping("/addProductinDb")
	public String addProductinDb(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, Model model)
			throws IOException {

		System.out.println(product);
		System.out.println(file.getOriginalFilename());

		String fileName;

		// If user uploads a new file
		if (!file.isEmpty()) {
			fileName = file.getOriginalFilename();
			product.setImage(fileName);
		} else {
			// Keep old image
			fileName = "defualt.jpeg";
			product.setImage(fileName);
		}
		ss.save(product);
		File savefile = new ClassPathResource("static/img").getFile();
		Path path = Paths.get(savefile.getAbsolutePath() + File.separator + "product" + File.separator + fileName);
		System.out.println(path);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		model.addAttribute("msg", "hello");

		return "admin/AddProduct";
	}

	@GetMapping(value = "/viewallproduct")
	public ModelAndView viewAllProduct(ModelAndView m) {
		m.addObject("ProductList", ss.findAll());
		m.setViewName("admin/viewAllProduct");
		return m;
	}

	@GetMapping(value = "/deleteproduct/{id}")
	public ModelAndView deleteProduct(ModelAndView m, @PathVariable int id) {
		int i = ss.deleteById(id);
		if (i == 1) {
			m.addObject("sussmsg", "Product Delete Successfully");
		} else {
			m.addObject("errmsg", "Something Went Wrong");
		}
		viewAllProduct(m);
		return m;
	}

	@GetMapping(value = "/editproduct/{id}")
	public ModelAndView editProduct(ModelAndView m, @PathVariable int id) {
		Product p = ss.findById(id);
		System.out.println(p);
		m.addObject("product", p);
		m.setViewName("admin/EditProduct");
		return m;
	}

	@PostMapping("/updateproduct")
	public String UpdateProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, Model model)
			throws IOException {

		// Fetch old product
		Product oldProduct = ss.findById(product.getId());

		String fileName;

		// If user uploads a new file
		if (!file.isEmpty()) {
			fileName = file.getOriginalFilename();
			product.setImage(fileName);
		} else {
			// Keep old image
			fileName = oldProduct.getImage();
			product.setImage(fileName);
		}

		// Save updated product to DB
		ss.save(product);

		// Save new image only if uploaded
		if (!file.isEmpty()) {
			File savefile = new ClassPathResource("static/img").getFile();
			Path path = Paths.get(savefile.getAbsolutePath() + File.separator + "product" + File.separator + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}

		model.addAttribute("msg", "Product Updated Successfully");
		return "redirect:/admin/viewallproduct";
	}
	
	public void getUser(Principal p, Model m)
	{
		if(p!=null)
		{
			String email=p.getName();
			User user=service.findByEmail(email);
			m.addAttribute("user",user);
		}
	}
	
	@GetMapping(value = "/user")
	public ModelAndView getalluser(ModelAndView m)
	{
	   List<User> u=service.findByRole("ROLE_USER");
	   m.addObject("User", u);
	   m.setViewName("admin/user");
		return m;
	}
	
	@GetMapping("/updateStatus")
	public String updateStatus(@RequestParam Boolean status,
	                           @RequestParam Integer id,
	                           RedirectAttributes ra) {

	    service.updateStatus(status, id);  

	   ra.addFlashAttribute("msg", "Status Updated Sucessfully!!!");
	    return "redirect:/admin/user";
	}
}
