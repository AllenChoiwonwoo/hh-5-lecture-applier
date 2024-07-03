package com.hh99.hh5cleanarchitecture.domain.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;

public interface LectureScheduleRepository {

    Boolean isFull(Long sessionId);

    LectureSchedule findLectureScheduleBy(Long sessionId);

    void save(LectureSchedule lectureschedule);
}
