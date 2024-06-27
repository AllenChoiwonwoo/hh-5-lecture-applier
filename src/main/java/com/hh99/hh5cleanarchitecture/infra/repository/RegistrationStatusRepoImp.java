package com.hh99.hh5cleanarchitecture.infra.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.repository.RegistrationStatusRepository;
import com.hh99.hh5cleanarchitecture.infra.jpa.RegistrationStatusJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationStatusRepoImp implements RegistrationStatusRepository {
    private final RegistrationStatusJpaRepository registrationStatusJpaRepository;

    @Override
    public RegistrationStatus findRegistrationStatusBy(Long lectureScheduledId) {
        return registrationStatusJpaRepository.findByLectureScheduleId(lectureScheduledId);
    }


    @Override
    public void save(RegistrationStatus registrationstatus) {
        registrationStatusJpaRepository.save(registrationstatus);
    }
}
