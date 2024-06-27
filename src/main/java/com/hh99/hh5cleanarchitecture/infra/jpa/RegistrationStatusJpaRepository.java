package com.hh99.hh5cleanarchitecture.infra.jpa;

import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationStatusJpaRepository extends JpaRepository<RegistrationStatus, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    RegistrationStatus findByLectureScheduleId(Long lectureScheduleId);
}
