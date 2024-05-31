package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	
	private TodoService todoservice;
	
	
	
	public TodoResource(TodoService todoservice) {
		this.todoservice = todoservice;
	}



	@GetMapping("/users/{username}/todos")
	public List<Todo> retriveTodos(@PathVariable String username) {
		return todoservice.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retriveTodo(@PathVariable String username,@PathVariable int id) {
		return todoservice.findById(id);
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public void deleteTodo(@PathVariable int id){
		todoservice.deleteById(id);	
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable int id, @PathVariable String username, @RequestBody Todo todo ){
		todoservice.updateTodo(todo);
		return todo;
	}
	
	@PostMapping("/users/{username}/todos")
	public Todo createTodo( @PathVariable String username, @RequestBody Todo todo ){
		Todo createdTodo = todoservice.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return createdTodo;
	}
	
	
}
