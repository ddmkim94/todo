package com.kdm.todo.service;

import com.kdm.todo.domain.Address;
import com.kdm.todo.domain.Member;
import com.kdm.todo.domain.Todo;
import com.kdm.todo.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired MemberService memberService;
    @Autowired TodoRepository todoRepository;
    @Autowired TodoService todoService;

    @DisplayName("TODO 생성 테스트")
    @Test
    void createTodo() {
        Todo todo = new Todo();
        todo.setWriter("김동민");
        todo.setTitle("제목1");
        todo.setContent("내용!!~~");

        todoService.createTodo(todo);
        List<Todo> todos = todoService.findAll();
        assertThat(todos.size()).isEqualTo(1);
    }

    @DisplayName("TODO 삭제 테스트!!")
    @Test
    void deleteTodoTest() throws Exception {
        Todo todo = new Todo();
        todo.setWriter("김동민");
        todo.setTitle("제목1");
        todo.setContent("내용!!~~");

        Long saveId = todoService.createTodo(todo);
        List<Todo> todos = todoService.findAll();
        assertThat(todos.size()).isEqualTo(1);

        todoService.deleteTodo(todo);
        List<Todo> todos2 = todoService.findAll();
        assertThat(todos2.size()).isEqualTo(0);
    }

    @DisplayName("특정 회원의 TODO 목록조회 테스트")
    @Test
    void todosByMemberId() throws Exception {
        Member member1 = new Member();
        member1.setName("member1");
        member1.setEmail("ddmkim94@naver.com");
        member1.setAddress(new Address("주소1", "주소2"));
        memberService.saveMember(member1);

        Member member2 = new Member();
        member2.setName("member1");
        member2.setEmail("ddmkim94@gmail.com");
        member2.setAddress(new Address("주소1", "주소2"));
        memberService.saveMember(member2);

        Todo todo1 = new Todo();
        todo1.setWriter("김동민");
        todo1.setTitle("제목1");
        todo1.setContent("내용!!~~");
        todo1.setMember(member1);

        Todo todo2 = new Todo();
        todo2.setWriter("김동민");
        todo2.setTitle("제목1");
        todo2.setContent("내용!!~~");
        todo2.setMember(member1);

        Todo todo3 = new Todo();
        todo3.setWriter("김동민");
        todo3.setTitle("제목1");
        todo3.setContent("내용!!~~");
        todo3.setMember(member2);

        todoService.createTodo(todo1);
        todoService.createTodo(todo2);
        todoService.createTodo(todo3);

        List<Todo> todos = todoService.findAllById(member1.getId());
        System.out.println(todos.size());
    }

}