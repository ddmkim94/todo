package com.kdm.todo.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    @Column(name = "mem_street")
    private String street; // 도로명주소

    @Column(name = "mem_detail")
    private String detail; // 상세주소
}
