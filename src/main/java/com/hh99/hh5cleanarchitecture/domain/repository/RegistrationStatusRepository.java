package com.hh99.hh5cleanarchitecture.domain.repository;

import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;

//@Component
public interface RegistrationStatusRepository {

    RegistrationStatus findRegistrationStatusBy(Long sessionId);


    void save(RegistrationStatus registrationstatus);


}
