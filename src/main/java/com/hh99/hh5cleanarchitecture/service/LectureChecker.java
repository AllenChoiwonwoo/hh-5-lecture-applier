package com.hh99.hh5cleanarchitecture.service;

import com.hh99.hh5cleanarchitecture.controller.ApplyRequest;
import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.Session;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class LectureChecker {
    private final LectureRepository lectureRepository;

    public Application checkAvailable(ApplyRequest applyRequest) {
        checkUserApplication(applyRequest);
        checkFullBooking(applyRequest);
        Application application = lectureRepository.getApplication(applyRequest.getSessionId()).raseCount();
        return application;

    }

    public void checkFullBooking(ApplyRequest applyRequest) {
        Boolean isfull = lectureRepository.isFull(applyRequest.getSessionId());
        if (isfull){
            throw new RuntimeException("수강 가능 인원이 초과되었습니다.");
        }
    }

    public void checkUserApplication(ApplyRequest applyRequest) {
        UserApplication userApplication = lectureRepository.getApplyRecord(applyRequest.getUserId(), applyRequest.getLectureId());
        if (nonNull(userApplication)){
            throw new RuntimeException("이미 신청한 유저입니다.");
        }
    }
}
