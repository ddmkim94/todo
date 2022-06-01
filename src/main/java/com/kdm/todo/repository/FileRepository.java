package com.kdm.todo.repository;

import com.kdm.todo.domain.File;
import com.kdm.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public void save(File file) {
        em.persist(file);
    }

    // 첨부파일 검색 (todoId)
    public List<File> findByTodoId(Long todoId) {
        return em.createQuery("select f from File f where f.todo.id = :todoId", File.class)
                .setParameter("todoId", todoId)
                .getResultList();
    }

    // 첨부파일 검색 (memberId)
    public List<File> findByMemberId(Long memberId) {
        return em.createQuery("select f from File f where f.member.id = :memberId", File.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<File> findAll() {
        return em.createQuery("select f from File f", File.class)
                .getResultList();
    }

}
