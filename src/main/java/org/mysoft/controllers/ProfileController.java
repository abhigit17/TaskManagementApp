package org.mysoft.controllers;

import java.security.Principal;

import org.mysoft.entities.User;
import org.mysoft.services.TaskService;
import org.mysoft.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
	
	@Autowired
	UserService userService;
	@Autowired
	TaskService taskService;
	
	@GetMapping("/profile")
	public String showProfilePage(Model model, Principal principal) { //Principal-interface is part of SpringSecurity and used here to get the logged-in user's details
		String email = principal.getName();
		User user = userService.findOne(email);
		model.addAttribute("tasks", taskService.findUserTask(user));
		return "views/profile";
	}
	
}
