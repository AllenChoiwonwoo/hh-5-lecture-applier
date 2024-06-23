package com.hh99.hh5cleanarchitecture.infra;

import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserApplicationJpaRepository extends JpaRepository<UserApplication, Long> {
    UserApplication findByUserIdAndLectureId(Long userId, Long lectureId);
}
