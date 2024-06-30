package com.hh99.hh5cleanarchitecture.domain.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;

import java.util.Optional;

//@Component
public interface UserEnrollmentRepository {
    Optional<UserEnrollment> findByUserIdAndLectureId(Long userId, Long lectureId);

    UserEnrollment save(UserEnrollment userEnrollment);
}
