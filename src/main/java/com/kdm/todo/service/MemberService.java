package com.kdm.todo.service;

import com.kdm.todo.domain.Member;
import com.kdm.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long saveMember(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 회원중복검사
    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByEmail(member.getEmail());
        if (!members.isEmpty()) { // 검색 결과가 나온다면
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
