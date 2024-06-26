package com.hh99.hh5cleanarchitecture.integration;

import com.hh99.hh5cleanarchitecture.controller.ApplyRequest;
import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.Lecture;
import com.hh99.hh5cleanarchitecture.entity.Session;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import com.hh99.hh5cleanarchitecture.infra.ApplicationJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.LectureJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.SessionJpaRepository;
import com.hh99.hh5cleanarchitecture.service.LectureService;
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
    SessionJpaRepository sessionJpaRepository;
    @Autowired
    ApplicationJpaRepository applicationJpaRepository;

    private Lecture lecturePreset;
    private Session sessionPreset;
    private Application application;
    private Long userId = 5l;

    @BeforeEach
     void setUp() {
        Lecture lecture = Lecture.builder()
                .name("테스트 특강")
                .build();
        lecturePreset = lectureJpaRepository.save(lecture);
        Session session = Session.builder()
                .lectureId(lecture.getId())
                .applyDate(System.currentTimeMillis())
                .maxApplier(30l)
                .isFull(false)
                .build();
        sessionPreset = sessionJpaRepository.save(session);
        application = Application.builder()
                .currentApplier(0l)
                .maxApplier(sessionPreset.getMaxApplier())
                .sessionId(sessionPreset.getId())
                .build();
        applicationJpaRepository.save(application);
    }

    @Test
    void success_apply(){
        //given
        ApplyRequest applyRequest = ApplyRequest.builder()
                .userId(userId)
                .lectureId(lecturePreset.getId())
                .sessionId(sessionPreset.getId())
                .build();
        applyRequest.setTimestamp(System.currentTimeMillis()+1000);

        UserApplication apply = lectureService.apply(applyRequest);

        assert Objects.nonNull(apply);
    }
}
