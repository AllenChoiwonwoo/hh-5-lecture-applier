package com.hh99.hh5cleanarchitecture.controller;

import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResponse {
    private Long lectureId;
    private Long sessionId;
    private Long applyId;

    public static ApplyResponse from(UserApplication userApplication) {
        return ApplyResponse.builder()
                .lectureId(userApplication.getLectureId())
                .sessionId(userApplication.getSessionId())
                .applyId(userApplication.getId())
                .build();
    }
}
