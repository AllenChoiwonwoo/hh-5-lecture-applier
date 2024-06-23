package com.hh99.hh5cleanarchitecture.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class Application  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="session_id")
    private Long sessionId;
    @Column(name="max_applier")
    private Long maxApplier;
    @Column(name="current_applier")
    private Long currentApplier;

    @Builder
    Application (Long id, Long sessionId, Long maxApplier, Long currentApplier){
        this.id = id;
        this.sessionId = sessionId;
        this.maxApplier = maxApplier;
        this.currentApplier = currentApplier;
    }

    public Application raseCount() {
        if (this.maxApplier <= this.currentApplier) throw new RuntimeException("수강 가능 인원이 초과되었습니다.");
        this.currentApplier = this.currentApplier + 1l;
        return this;
    }
}
