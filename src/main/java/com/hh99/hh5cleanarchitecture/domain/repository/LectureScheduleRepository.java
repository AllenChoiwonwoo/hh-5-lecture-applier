package com.hh99.hh5cleanarchitecture.domain.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;

public interface LectureScheduleRepository {

    Boolean isFull(Long sessionId);

    LectureSchedule findLectureScheduleBy(Long sessionId);

    void saveLectureSchedule(LectureSchedule lectureschedule);
}
