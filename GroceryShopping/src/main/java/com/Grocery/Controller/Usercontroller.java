package com.Grocery.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Grocery.Service.userService;
import com.Grocery.model.User;

@Controller
@RequestMapping("/user")
public class Usercontroller {
	
	@Autowired
	private userService service;

	@GetMapping("/")
	public String home()
	{
		return "user/home";
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
}
