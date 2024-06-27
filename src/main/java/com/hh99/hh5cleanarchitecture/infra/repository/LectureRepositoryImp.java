package com.hh99.hh5cleanarchitecture.infra.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.LectureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LectureRepositoryImp implements LectureRepository{
    private final LectureJpaRepository lectureJpaRepository;
}
