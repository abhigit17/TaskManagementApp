package org.mysoft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mysoft.entities.Task;
import org.mysoft.entities.User;
import org.mysoft.services.TaskService;
import org.mysoft.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagementAppApplicationTests {
	
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	
	@Before
	public void initDb() {
		{
			User newUser = new User("testUser@mail.com", "testUser", "123456");
			userService.createUser(newUser);
		}
		{
			User newUser = new User("testAdmin@mail.com", "testAdmin", "123456");
			userService.createUser(newUser);
		}
		Task userTask = new Task("05/01/2019", "15:31", "17:30", "You have a new work to do.");
		User user = userService.findOne("testUser@mail.com");
		taskService.addTask(userTask, user);
	}
	@Test
	public void testUser() {
		User user = userService.findOne("testUser@mail.com");
		assertNotNull(user);
		User admin = userService.findOne("testAdmin@mail.com");
		assertEquals(admin.getEmail(),"testAdmin@mail.com");
	}
	@Test
	public void testTask() {
		User user = userService.findOne("testUser@mail.com");
		List<Task> tasks = taskService.findUserTask(user);
		assertNotNull(tasks);
	}
	
}
