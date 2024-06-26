package com.hh99.hh5cleanarchitecture.infra;

import com.hh99.hh5cleanarchitecture.entity.Application;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Application findBySessionId(Long sessionId);
}
