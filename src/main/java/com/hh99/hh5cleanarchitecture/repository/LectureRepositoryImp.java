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
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureRepositoryImp implements LectureRepository{
    private final LectureJpaRepository lectureJpaRepository;
    private final UserApplicationJpaRepository userApplicationJpaRepository;
    private final SessionJpaRepository sessionJpaRepository;
    private final ApplicationJpaRepository applicationJpaRepository;
    @Override
    public UserApplication getApplyRecord(Long userId, Long lectureId) {
        return userApplicationJpaRepository.findByUserIdAndLectureId(userId, lectureId);
    } @Override
    public UserApplication addApplier(Long userId, Long lectureId, Long sessionId) {
        UserApplication userApplication = UserApplication.builder()
                .userId(userId).lectureId(lectureId).sessionId(sessionId).build();
        return userApplicationJpaRepository.save(userApplication);
    }

    @Override
    public Boolean isFull(Long sessionId) {
        Optional<Session> session = sessionJpaRepository.findById(sessionId);
        if(session.isEmpty()) throw new RuntimeException("Can not find Session");
        return session.get().getIsFull();
    }

    @Override
    public Application getApplication(Long sessionId) {
        return applicationJpaRepository.findBySessionId(sessionId);
    }

    @Override
    public Session getSession(Long sessionId) {
        return sessionJpaRepository.findById(sessionId).orElseThrow(() -> {throw new RuntimeException("no session");});
    }

    @Override
    public void saveApplication(Application application) {
        applicationJpaRepository.save(application);
    }

    @Override
    public void saveSession(Session session) {
        sessionJpaRepository.save(session);
    }
}
