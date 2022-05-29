package com.kdm.todo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class FileId implements Serializable {

    // 파일 관리 PK는 id, no를 합친 복합키로 관리
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_no")
    private Long no;

}
