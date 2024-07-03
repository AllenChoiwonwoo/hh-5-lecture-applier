package com.hh99.hh5cleanarchitecture.infra.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureScheduleRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureScheduleJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.RegistrationStatusJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.UserEnrollmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureScheduleRepoImp implements LectureScheduleRepository{
    private final LectureScheduleJpaRepository lectureScheduleJpaRepository;

    @Override
    public Boolean isFull(Long lectureScheduledId) {
        Optional<LectureSchedule> session = lectureScheduleJpaRepository.findById(lectureScheduledId);
        if(session.isEmpty()) throw new RuntimeException("Can not find LectureSchedule");
        return session.get().getIsFull();
    }

    @Override
    public LectureSchedule findLectureScheduleBy(Long lectureScheduledId) {
        return lectureScheduleJpaRepository.findById(lectureScheduledId).orElseThrow(() -> {throw new RuntimeException("no lecture Scheduled");});
    }

    @Override
    public void save(LectureSchedule lectureschedule) {
        lectureScheduleJpaRepository.save(lectureschedule);
    }
}
