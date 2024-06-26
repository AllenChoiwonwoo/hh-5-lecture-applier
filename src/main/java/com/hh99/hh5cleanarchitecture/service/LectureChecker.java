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

    public void checkAvailable(ApplyRequest applyRequest) {
        checkScadule(applyRequest);
        checkFullBooking(applyRequest);
        checkUserApplication(applyRequest);
    }

    public void checkScadule(ApplyRequest request) {
        Session session = lectureRepository.getSession(request.getSessionId());
        boolean result = session.isAvailable(request.getTimestamp());
        if (result == false) throw new RuntimeException("수강 신청 가능 기간이 아닙니다.");
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
    public void updateSessionState(ApplyRequest applyRequest, Application application) {
        if (application.getCurrentApplier() == application.getMaxApplier()) {
            Session session =  lectureRepository.getSession(applyRequest.getSessionId());
            session.setFull();
            lectureRepository.saveSession(session);
        }
    }
}
