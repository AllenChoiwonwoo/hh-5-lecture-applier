package com.hh99.hh5cleanarchitecture.infra.jpa;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureScheduleJpaRepository extends JpaRepository<LectureSchedule, Long> {
}
