package com.kdm.todo.service;

import com.kdm.todo.domain.Todo;
import com.kdm.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long createTodo(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    public Todo findByTitle(String title) {
        return todoRepository.findByTitle(title);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public List<Todo> findAllById(Long memberId) {
        return todoRepository.findAllById(memberId);
    }

    @Transactional
    public void deleteTodo(Todo todo) {
        todoRepository.delete(todo);
    }
}
