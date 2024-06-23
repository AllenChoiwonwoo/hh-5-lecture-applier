package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.entity.Member;
import com.hh99.hh5cleanarchitecture.infra.MemberJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void saveMember() {
        Member saveParams = Member.builder()
                .name("allen")
                .build();

        Member member = memberJpaRepository.save(saveParams);
        assert member.getName().equals("allen");
    }
    @Test
    void findAllMameber() {
        List<Member> all = memberJpaRepository.findAll();
        for (Member member : all){
            System.out.println(member);
        }

    }

}