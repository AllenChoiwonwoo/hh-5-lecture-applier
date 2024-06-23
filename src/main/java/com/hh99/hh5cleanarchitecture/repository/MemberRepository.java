package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
