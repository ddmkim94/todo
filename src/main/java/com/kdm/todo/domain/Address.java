package com.kdm.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {

    @Column(name = "mem_street")
    private String street; // 도로명주소

    @Column(name = "mem_detail")
    private String detail; // 상세주소
}
