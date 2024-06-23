package com.hh99.hh5cleanarchitecture.infra;

import com.hh99.hh5cleanarchitecture.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
