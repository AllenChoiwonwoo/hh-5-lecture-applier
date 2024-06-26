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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@SpringBootTest
public class LectureApplySynchroTest {

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
    void synchroTest(){
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (long i = 0l; i < 40l; i++) {
            long finalI = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                ApplyRequest applyRequest = ApplyRequest.builder()
                        .userId(finalI)
                        .lectureId(lecturePreset.getId())
                        .sessionId(sessionPreset.getId())
                        .timestamp(System.currentTimeMillis())
                        .build();
                UserApplication apply = lectureService.apply(applyRequest);
                System.out.println(apply);
            });
            futures.add(future);
        }
        // futures 를 array 로 변환

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        try {
            // 모든 작업 완료 대기
            allOf.get();
            System.out.println("-=-----------------");
            System.out.println("All tasks completed.");
        } catch (Exception e) {
            e.printStackTrace();
//            assert
        }

    }
}
