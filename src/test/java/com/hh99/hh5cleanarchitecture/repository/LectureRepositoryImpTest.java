package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.Lecture;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureScheduleJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.RegistrationStatusJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.UserEnrollmentJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.repository.LectureRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LectureRepositoryImpTest {

    @Autowired
    private LectureRepositoryImp lectureRepository;
    @Autowired
    private LectureJpaRepository lectureJpaRepository;
    @Autowired
    private UserEnrollmentJpaRepository userEnrollmentJpaRepository;
    @Autowired
    private LectureScheduleJpaRepository lectureScheduleJpaRepository;
    @Autowired
    private RegistrationStatusJpaRepository registrationStatusJpaRepository;
    private UserEnrollment userEnrollment;
    private RegistrationStatus registrationstatus;
    private LectureSchedule lectureschedule;
    private Lecture lecture;

    @BeforeEach
    void setUp() {
        userEnrollment = UserEnrollment.builder()
                .userId(1l)
                .lectureId(1l)
                .lectureScheduleId(1l)
                .build();
        registrationstatus = RegistrationStatus.builder()
                .lectureScheduleId(1l)
                .currentApplicants(10l)
                .maxApplicants(30l)
                .build();
//        lectureschedule = new LectureSchedule();
//        lectureschedule.setFull();

        lectureschedule = LectureSchedule.builder()
                .sessionId(1l)
                .lectureId(1l)
                .isFull(true)
                .build();

    }

    // fixme : 오류 수정 필요
    @Test
    void getApplyRecord() {
        //given
        UserEnrollment savedUser = userEnrollmentJpaRepository.save(userEnrollment);
        UserEnrollment record = lectureRepository.getApplyRecord(savedUser.getUserId(), savedUser.getLectureId());

        assert savedUser.getId() == record.getId();
    }

    @Test
    void addApplier() {
        UserEnrollment save = userEnrollmentJpaRepository.save(userEnrollment);
        System.out.println(save.getId());
        assert userEnrollment.getUserId() == save.getUserId();
        assert userEnrollment.getLectureId() == save.getLectureId();
        assert userEnrollment.getLectureScheduleId() == save.getLectureScheduleId();
    }

    @Test
    void isFull() {
        //given
        lectureScheduleJpaRepository.save(lectureschedule);

        Boolean isFull = lectureRepository.isFull(lectureschedule.getId());
        assert isFull == true;
    }

    @Test
    void getApplication() {
        // given
        registrationStatusJpaRepository.save(registrationstatus);
        // when
        RegistrationStatus result = lectureRepository.findRegistrationStatusBy(registrationstatus.getLectureScheduleId());
        // then
        assert registrationstatus.getLectureScheduleId() == result.getLectureScheduleId();
    }

    @Test
    void getSession() {
        // given
        lectureScheduleJpaRepository.save(lectureschedule);
        // when
        LectureSchedule result = lectureRepository.findLectureScheduleBy(lectureschedule.getId());
        // then
        assert lectureschedule.getId() == result.getId();
    }
}