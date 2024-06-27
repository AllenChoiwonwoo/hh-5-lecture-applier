package com.hh99.hh5cleanarchitecture.infra.jpa;

import com.hh99.hh5cleanarchitecture.domain.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
    Lecture findLectureByName(String name);


}
