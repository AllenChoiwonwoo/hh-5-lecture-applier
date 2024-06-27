package com.hh99.hh5cleanarchitecture.integration;

import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.entity.Lecture;
import com.hh99.hh5cleanarchitecture.infra.jpa.RegistrationStatusJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureScheduleJpaRepository;
import com.hh99.hh5cleanarchitecture.application.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class LectureApplyTest {

    @Autowired
    LectureService lectureService;
    @Autowired
    LectureJpaRepository lectureJpaRepository;
    @Autowired
    LectureScheduleJpaRepository lectureScheduleJpaRepository;
    @Autowired
    RegistrationStatusJpaRepository registrationStatusJpaRepository;

    private Lecture lecturePreset;
    private LectureSchedule lectureschedulePreset;
    private RegistrationStatus registrationstatus;
    private Long userId = 5l;

    @BeforeEach
     void setUp() {
        Lecture lecture = Lecture.builder()
                .name("테스트 특강")
                .build();
        lecturePreset = lectureJpaRepository.save(lecture);
        LectureSchedule lectureschedule = LectureSchedule.builder()
                .lectureId(lecture.getId())
                .applyDate(System.currentTimeMillis())
                .maxApplier(30l)
                .isFull(false)
                .build();
        lectureschedulePreset = lectureScheduleJpaRepository.save(lectureschedule);
        registrationstatus = RegistrationStatus.builder()
                .currentApplier(0l)
                .maxApplier(lectureschedulePreset.getMaxApplier())
                .sessionId(lectureschedulePreset.getId())
                .build();
        registrationStatusJpaRepository.save(registrationstatus);
    }

    @Test
    void success_apply(){
        //given
        ApplyRequest applyRequest = ApplyRequest.builder()
                .userId(userId)
                .lectureId(lecturePreset.getId())
                .lectureScheduleId(lectureschedulePreset.getId())
                .build();
        applyRequest.setTimestamp(System.currentTimeMillis()+1000);

        UserEnrollment apply = lectureService.apply(applyRequest);

        assert Objects.nonNull(apply);
    }
}
