package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@RequestMapping("/login")
	public String login(){
		
		return "login";
	}
	
	@RequestMapping("/")
	public String home(){
		return "home";
	}

	@RequestMapping("/register")
	public String register(Model model){
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(path = "/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User user){
		System.out.println(user);
		
		Role role = new Role();
		Set<Role> roles = new HashSet<Role>();
		Set<User> users = new HashSet<User>();
		
		user.setEnabled(true);
		role.setRole("ADMIN");
		roles.add(role);
		user.setRoles(roles);
		
		users.add(user);
		role.setUsers(users);
		roleRepository.save(role);
		userRepository.save(user);
		
		return "redirect:/";
	}
	

}
