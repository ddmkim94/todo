package com.kdm.todo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Long id;

    @Column(name = "mem_email")
    private String email;

    @Column(name = "mem_pwd")
    private String pwd;

    @Column(name = "mem_pwp_chk")
    private String pwd_chk;

    @Embedded
    @Column(name = "mem_address")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "mem_role")
    private MemberRole role;

    @Column(name = "mem_profile")
    private String profile; // 프로필 사진은 따로 관리하지않고 바로 주소를 통해서 뿌려줄 예정...

}
