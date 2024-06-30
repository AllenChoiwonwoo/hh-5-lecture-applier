package com.hh99.hh5cleanarchitecture.integration;

import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.Lecture;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.infra.jpa.RegistrationStatusJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureScheduleJpaRepository;
import com.hh99.hh5cleanarchitecture.application.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class LectureApplyConcurrencyTest {

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
                .registrationStartAt(System.currentTimeMillis())
                .maxApplier(30l)
                .isFull(false)
                .build();
        lectureschedulePreset = lectureScheduleJpaRepository.save(lectureschedule);
        registrationstatus = RegistrationStatus.builder()
                .currentApplicants(0l)
                .maxApplicants(lectureschedulePreset.getMaxApplier())
                .lectureScheduleId(lectureschedulePreset.getId())
                .build();
        registrationStatusJpaRepository.save(registrationstatus);
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
                        .lectureScheduleId(lectureschedulePreset.getId())
                        .timestamp(System.currentTimeMillis())
                        .build();
                try {
                    UserEnrollment apply = lectureService.apply(applyRequest);
                    System.out.println(apply);
                }catch (RuntimeException e){
                    System.out.println("ERROR : " + e.getMessage());
                }
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
