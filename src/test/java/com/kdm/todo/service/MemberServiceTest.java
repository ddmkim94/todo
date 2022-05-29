package com.kdm.todo.service;

import com.kdm.todo.domain.Address;
import com.kdm.todo.domain.Member;
import com.kdm.todo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    @Test
    void saveMemberTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("주소1", "주소2"));

        //when
        Long saveId = memberService.saveMember(member);
        Member findMember = memberService.findMember(saveId);

        //then
        assertThat(saveId).isEqualTo(findMember.getId());
    }

    @Test
    void validateMemberTest() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("member1");
        member1.setEmail("ddmkim94@naver.com");
        member1.setAddress(new Address("주소1", "주소2"));

        Member member2 = new Member();
        member2.setName("member2");
        member2.setEmail("ddmkim94@naver.com");
        member2.setAddress(new Address("주소3", "주소4"));

        //when
        memberService.saveMember(member1);

        //then
        assertThrows(IllegalStateException.class, () -> { memberService.saveMember(member2);});
    }

}