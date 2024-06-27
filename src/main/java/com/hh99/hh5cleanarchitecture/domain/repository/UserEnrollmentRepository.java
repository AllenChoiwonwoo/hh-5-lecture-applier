package com.hh99.hh5cleanarchitecture.domain.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;

//@Component
public interface UserEnrollmentRepository {
    UserEnrollment getApplyRecord(Long userId, Long lectureId);

    UserEnrollment save(UserEnrollment userEnrollment);
}
