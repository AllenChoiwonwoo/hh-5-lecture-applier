package com.hh99.hh5cleanarchitecture.service;

import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface LectureRepository {
    UserApplication getApplyRecord(Long userId, Long lectureId);

    Application getApplierCount(Long sessionId);

    Application raseCurrentApplier(Long userId, Long lectureId, Long sessionId);

    void setFullBooking(Long sessionId);

    UserApplication addApplier(Long userId, Long lectureId, Long sessionId);

    Boolean isFull(Long sessionId);

    Application getApplication(Long sessionId);
}
