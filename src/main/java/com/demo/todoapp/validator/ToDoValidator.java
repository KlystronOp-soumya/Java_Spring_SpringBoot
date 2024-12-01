package com.demo.todoapp.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.todoapp.entity.ToDoEntity;

@Component("todoValidator")
public class ToDoValidator implements Validator{

	

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ToDoEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		List<ToDoEntity> toDos = (List<ToDoEntity>) target ;
		for(ToDoEntity e : toDos)
		{
			if(e.getTodoId() == null)
			{
				errors.reject("todoId", "todoId.error");
			}
		}
		
		
	}

}
