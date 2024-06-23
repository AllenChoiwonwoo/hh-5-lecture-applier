package com.hh99.hh5cleanarchitecture.service;

import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.Session;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
public interface LectureRepository {
    UserApplication getApplyRecord(Long userId, Long lectureId);

    UserApplication addApplier(Long userId, Long lectureId, Long sessionId);

    Boolean isFull(Long sessionId);

    Application getApplication(Long sessionId);

    Session getSession(Long sessionId);
}
