package com.kdm.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class File {

    @EmbeddedId
    private FileId fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_ext")
    private String ext;

    @Column(name = "file_size")
    private int size;

    @Column(name = "file_save_date")
    private LocalDateTime saveDate;

    public File(FileId fileId, Todo todo, String name, String ext, int size, LocalDateTime saveDate) {
        this.fileId = fileId;
        this.todo = todo;
        this.name = name;
        this.ext = ext;
        this.size = size;
        this.saveDate = saveDate;
    }
}
