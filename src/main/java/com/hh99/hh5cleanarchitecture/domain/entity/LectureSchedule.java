package com.hh99.hh5cleanarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table
public class LectureSchedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_id")
    private Long lectureId;
    @Column(name = "registration_start_at")
    private Long registrationStartAt;
    @Column(name = "start_at")
    private Long startAt;
    @Column(name = "end_at")
    private Long endAt;
    @Column(name = "max_applicants")
    private Long maxApplier;
    @Column(name = "is_full")
    private Boolean isFull;


    public void setFull() {
        this.isFull = true;
    }

    @Builder
    public LectureSchedule(Long sessionId, Long lectureId, Long registrationStartAt, Long startAt, Long endAt, Long maxApplier, Boolean isFull) {
        this.id = sessionId;
        this.lectureId = lectureId;
        this.registrationStartAt = registrationStartAt;
        this.startAt = startAt;
        this.endAt = endAt;
        this.maxApplier = maxApplier;
        this.isFull = isFull;
    }

    public void isRegistrationStart(Long applyAt) {
        if (this.registrationStartAt > applyAt) throw new RuntimeException("수강신청 가능 기간이 아닙니다.");
    }

    public void setIsFull(Long maxApplicants, Long currentApplicants) {
        this.isFull = maxApplicants <= currentApplicants;
    }

    public boolean setIsFull(boolean b) {
        this.isFull = b;
        return isFull;
    }
}

/*
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class Lecture extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public Lecture(String name) {
        this.name = name;
    }

}
 */