package org.mysoft;

import org.mysoft.entities.User;
import org.mysoft.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementAppApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception  {
		{
			User newAdmin = new User("admin@mail.com","Admin","12356");
			userService.createAdmin(newAdmin);
		}
	}
	
}
