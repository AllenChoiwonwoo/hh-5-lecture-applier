package com.hh99.hh5cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class RegistrationStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="lecture_schedule_id")
    private Long lectureScheduleId;
    @Column(name="max_applicants")
    private Long maxApplicants;
    @Column(name="current_applicants")
    private Long currentApplicants;

    @Builder
    RegistrationStatus(Long id, Long lectureScheduleId, Long maxApplicants, Long currentApplicants){
        this.id = id;
        this.lectureScheduleId = lectureScheduleId;
        this.maxApplicants = maxApplicants;
        this.currentApplicants = currentApplicants;
    }

    public RegistrationStatus raseCount() {
        if (isFull()) {
            throw new RuntimeException("수강 가능 인원이 초과되었습니다.");
        }
        this.currentApplicants = this.currentApplicants + 1l;
        return this;
    }
    public boolean isFull() {
        return this.maxApplicants <= this.currentApplicants;
    }
}
