package com.kdm.todo.service;

import com.kdm.todo.domain.*;
import com.kdm.todo.repository.FileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FileServiceTest {

    @Autowired MemberService memberService;
    @Autowired TodoService todoService;
    @Autowired FileRepository fileRepository;
    @Autowired FileService fileService;

    @Test
    @DisplayName("첨부파일 저장 테스트")
    void saveFileTest() throws Exception {
        Member member = new Member("ddmkim94@gmail.com",
                "1234", "1234", "김동민", new Address("서울 동대문구", "망우로20길"),
                MemberRole.USER, LocalDateTime.now());

        Todo todo = new Todo();
        todo.setWriter(member.getName());
        todo.setTitle("todo 만들기");
        todo.setContent("백엔드 구현부터 하자!");
        todo.setMember(member);
        todo.setType(TodoType.TODO);
        todo.setCreateDate(LocalDateTime.now());
        todo.setModifiedDate(LocalDateTime.now());
        FileId id = new FileId();
        id.setId(1L);
        id.setNo(1L);
        File file = new File(id, todo, "사진1", "png", 100, LocalDateTime.now());
        todo.getFileList().add(file);

        memberService.saveMember(member);
        fileService.saveFile(file);
        todoService.createTodo(todo);
    }


    @Test
    @DisplayName("첨부파일 검색 테스트")
    @Rollback(false)
    void findFileTest() throws Exception {
        Member member = new Member("ddmkim94@gmail.com",
                "1234", "1234", "김동민", new Address("서울 동대문구", "망우로20길"),
                MemberRole.USER, LocalDateTime.now());

        Todo todo = new Todo();
        todo.setWriter(member.getName());
        todo.setTitle("todo 만들기");
        todo.setContent("백엔드 구현부터 하자!");
        todo.setMember(member);
        todo.setType(TodoType.TODO);
        todo.setCreateDate(LocalDateTime.now());
        todo.setModifiedDate(LocalDateTime.now());
        FileId id = new FileId();
        id.setId(1L);
        id.setNo(1L);
        File file = new File(id, todo, "사진1", "png", 100, LocalDateTime.now());
        todo.getFileList().add(file);

        Todo todo2 = new Todo();
        todo2.setWriter(member.getName());
        todo2.setTitle("todo 만들기");
        todo2.setContent("백엔드 구현부터 하자!");
        todo2.setMember(member);
        todo2.setType(TodoType.TODO);
        todo2.setCreateDate(LocalDateTime.now());
        todo2.setModifiedDate(LocalDateTime.now());
        FileId id2 = new FileId();
        id2.setId(2L);
        id2.setNo(1L);
        File file2 = new File(id2, todo2, "사진1", "png", 100, LocalDateTime.now());
        todo.getFileList().add(file2);

        memberService.saveMember(member);
        fileService.saveFile(file);
        fileService.saveFile(file2);

        todoService.createTodo(todo);
        todoService.createTodo(todo2);

        List<File> files = fileService.findByTodoId(todo.getId());
        List<File> files2 = fileRepository.findAll();
        assertThat(files2.size()).isEqualTo(2);
        assertThat(files.size()).isEqualTo(1);
    }
}