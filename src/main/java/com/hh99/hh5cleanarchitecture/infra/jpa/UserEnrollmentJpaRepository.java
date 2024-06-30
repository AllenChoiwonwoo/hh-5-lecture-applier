package com.hh99.hh5cleanarchitecture.infra.jpa;

import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public interface UserEnrollmentJpaRepository extends JpaRepository<UserEnrollment, Long> {
    Optional<UserEnrollment> findByUserIdAndLectureId(Long userId, Long lectureId);
}
