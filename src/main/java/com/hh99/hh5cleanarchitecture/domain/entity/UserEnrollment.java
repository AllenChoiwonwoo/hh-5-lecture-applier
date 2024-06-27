package com.hh99.hh5cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class UserEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userId;
    @Column(name="lecture_id")
    private Long lectureId;
    @Column(name="lecture_schedule_id")
    private Long lectureScheduleId;

    @Builder
    UserEnrollment(Long applyId, Long userId, Long lectureId, Long lectureScheduleId) {
        this.id = applyId;
        this.userId = userId;
        this.lectureId = lectureId;
        this.lectureScheduleId = lectureScheduleId;
    }
}
