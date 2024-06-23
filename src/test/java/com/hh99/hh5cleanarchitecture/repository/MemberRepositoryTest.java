package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void saveMember() {
        Member saveParams = Member.builder()
                .name("allen")
                .build();

        Member member = memberRepository.save(saveParams);
        assert member.getName().equals("allen");
    }
    @Test
    void findAllMameber() {
        List<Member> all = memberRepository.findAll();
        for (Member member : all){
            System.out.println(member);
        }

    }

}