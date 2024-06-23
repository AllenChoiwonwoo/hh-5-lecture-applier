package com.hh99.hh5cleanarchitecture.entity;

import jakarta.persistence.*;
import lombok.*;

import java.nio.file.attribute.UserPrincipal;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class UserApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userId;
    @Column(name="lecture_id")
    private Long lectureId;
    @Column(name="session_id")
    private Long sessionId;

    @Builder
    UserApplication(Long applyId, Long userId, Long lectureId, Long sessionId) {
        this.id = applyId;
        this.userId = userId;
        this.lectureId = lectureId;
        this.sessionId = sessionId;
    }
}
