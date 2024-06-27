package com.hh99.hh5cleanarchitecture.infra.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.UserEnrollmentRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureScheduleJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.RegistrationStatusJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.UserEnrollmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEnrollmentRepoImp implements UserEnrollmentRepository {
    private final UserEnrollmentJpaRepository userEnrollmentJpaRepository;
    @Override
    public UserEnrollment getApplyRecord(Long userId, Long lectureId) {
        return userEnrollmentJpaRepository.findByUserIdAndLectureId(userId, lectureId);
    }

    @Override
    public UserEnrollment save(UserEnrollment userEnrollment) {
        return userEnrollmentJpaRepository.save(userEnrollment);
    }
}
