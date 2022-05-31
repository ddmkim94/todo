package com.kdm.todo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class File {

    @EmbeddedId
    private FileId fileId;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_ext")
    private String ext;

    @Column(name = "file_size")
    private int size;

    @Column(name = "file_save_date")
    private LocalDateTime saveDate;

}
