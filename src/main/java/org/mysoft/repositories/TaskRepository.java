package org.mysoft.repositories;

import java.util.List;

import org.mysoft.entities.Task;
import org.mysoft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUser(User user);

}
