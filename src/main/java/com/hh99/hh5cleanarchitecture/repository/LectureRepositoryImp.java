package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.Session;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import com.hh99.hh5cleanarchitecture.infra.LectureJpaRepository;
import com.hh99.hh5cleanarchitecture.infra.UserApplicationJpaRepository;
import com.hh99.hh5cleanarchitecture.service.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LectureRepositoryImp implements LectureRepository{
    private final LectureJpaRepository lectureJpaRepository;
    private final UserApplicationJpaRepository userApplicationJpaRepository;
    @Override
    public UserApplication getApplyRecord(Long userId, Long lectureId) {
        return userApplicationJpaRepository.findByUserIdAndLectureId(userId, lectureId);
    } @Override
    public UserApplication addApplier(Long userId, Long lectureId, Long sessionId) {
        return null;
    }

    @Override
    public Boolean isFull(Long sessionId) {
        return null;
    }

    @Override
    public Application getApplication(Long sessionId) {
        return null;
    }

    @Override
    public Session getSession(Long sessionId) {
        return null;
    }
}
