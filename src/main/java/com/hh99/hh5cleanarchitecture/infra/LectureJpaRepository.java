package com.hh99.hh5cleanarchitecture.infra;

import com.hh99.hh5cleanarchitecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
    Lecture findLectureByName(String name);
}
