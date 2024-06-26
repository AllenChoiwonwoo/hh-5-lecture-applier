package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.Lecture;
import com.hh99.hh5cleanarchitecture.entity.Session;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import com.hh99.hh5cleanarchitecture.infra.ApplicationJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.LectureJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.SessionJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.UserApplicationJpaRepository;
import com.hh99.hh5cleanarchitecture.service.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LectureRepositoryImpTest {

    @Autowired
    private LectureRepositoryImp lectureRepository;
    @Autowired
    private LectureJpaRepository lectureJpaRepository;
    @Autowired
    private UserApplicationJpaRepository userApplicationJpaRepository;
    @Autowired
    private SessionJpaRepository sessionJpaRepository;
    @Autowired
    private ApplicationJpaRepository applicationJpaRepository;
    private UserApplication userApplication;
    private Application application;
    private Session session;
    private Lecture lecture;

    @BeforeEach
    void setUp() {
        userApplication = UserApplication.builder()
                .userId(1l)
                .lectureId(1l)
                .sessionId(1l)
                .build();
        application = Application.builder()
                .sessionId(1l)
                .currentApplier(10l)
                .maxApplier(30l)
                .build();
//        session = new Session();
//        session.setFull();

        session = Session.builder()
                .sessionId(1l)
                .lectureId(1l)
                .isFull(true)
                .build();

    }

    @Test
    void getApplyRecord() {
        //given
        UserApplication savedUser = userApplicationJpaRepository.save(userApplication);
        UserApplication record = lectureRepository.getApplyRecord(savedUser.getUserId(), savedUser.getLectureId());

        assert savedUser.getId() == record.getId();
    }

    @Test
    void addApplier() {
        UserApplication save = userApplicationJpaRepository.save(userApplication);
        System.out.println(save.getId());
        assert userApplication.getUserId() == save.getUserId();
        assert userApplication.getLectureId() == save.getLectureId();
        assert userApplication.getSessionId() == save.getSessionId();
    }

    @Test
    void isFull() {
        //given
        sessionJpaRepository.save(session);

        Boolean isFull = lectureRepository.isFull(session.getId());
        assert isFull == true;
    }

    @Test
    void getApplication() {
        // given
        applicationJpaRepository.save(application);
        // when
        Application result = lectureRepository.getApplication(application.getSessionId());
        // then
        assert application.getSessionId() == result.getSessionId();
    }

    @Test
    void getSession() {
        // given
        sessionJpaRepository.save(session);
        // when
        Session result = lectureRepository.getSession(session.getId());
        // then
        assert session.getId() == result.getId();
    }
}