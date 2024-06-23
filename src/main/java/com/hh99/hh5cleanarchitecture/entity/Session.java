package com.hh99.hh5cleanarchitecture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table
public class Session extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_id")
    private Long lectureId;
    @Column(name = "apply_date")
    private Long applyDate;
    @Column(name = "start_date")
    private Long startDate;
    @Column(name = "end_date")
    private Long endDate;
    @Column(name = "max_applier")
    private Long maxApplier;
    @Column(name = "is_full")
    private Boolean isFull;


    public void setFull() {
        this.isFull = true;
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