package com.hh99.hh5cleanarchitecture.presentation.dto;

import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
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
    private Long lectureScheduleAt;
    private Long enrollmentId;

    public static ApplyResponse from(UserEnrollment userEnrollment) {
        return ApplyResponse.builder()
                .lectureId(userEnrollment.getLectureId())
                .lectureScheduleAt(userEnrollment.getLectureScheduleId())
                .enrollmentId(userEnrollment.getId())
                .build();
    }
}
