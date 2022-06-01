package com.kdm.todo.repository;

import com.kdm.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    public void save(Todo todo) {
        em.persist(todo);
    }

    // 제목으로 todo 검색
    public Todo findByTitle(String title) {
        return em.createQuery("select t from Todo t where t.title = :title", Todo.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    public Todo findById(Long todoId) {
        return em.find(Todo.class, todoId);
    }

    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }

    // 로그인 한 회원의 전체 todo 조회
    public List<Todo> findAllById(Long memberId) {
        return em.createQuery("select t from Todo t join t.member m where t.member.id = :memberId", Todo.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public void delete(Todo todo) {
        em.remove(todo);
    }
}
