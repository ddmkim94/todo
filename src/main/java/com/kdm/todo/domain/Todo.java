package com.kdm.todo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    private Member member;

    @Column(name = "todo_writer")
    private String writer;

    @Column(name = "todo_title")
    private String title;

    @Column(name = "todo_content")
    private String content;

    @Column(name = "todo_file")
    private String file; // 첨부파일 여기엔 파일명만 저장하고 다른 정보들은 테이블에서 따로 관리

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "todo_type")
    private TodoType type;

    @Column(name = "todo_create_date")
    private LocalDateTime createDate;

    @Column(name = "todo_modified_date")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "todo"/* 주인 엔티티에 있는 todo와 연관이 있다는걸 설정 */)
    private List<File> fileList = new ArrayList<>();
}
