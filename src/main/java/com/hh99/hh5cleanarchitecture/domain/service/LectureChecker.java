package com.hh99.hh5cleanarchitecture.domain.service;

import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureChecker {

    public void checkAvailable(long applyAt, LectureSchedule lectureSchedule, Optional<UserEnrollment> userEnrollment) {
        lectureSchedule.isRegistrationStart(applyAt);
        if (lectureSchedule.getIsFull()) throw new RuntimeException("수강 가능 인원이 초과되었습니다.");
        if (!userEnrollment.isEmpty()) throw new RuntimeException("이미 신청한 강의입니다.");
    }

    public void updateAvailableSeatsInfo(LectureSchedule lectureSchedule, RegistrationStatus registrationstatus) {
        // 특강의 현재 수강인원 업데이트
        registrationstatus.raseCount();
        // 특강의 잔여석 유뮤 업데이트
        if (registrationstatus.isFull()) lectureSchedule.setIsFull(true);
    }
}
