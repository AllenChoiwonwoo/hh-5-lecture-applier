package com.hh99.hh5cleanarchitecture.controller;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequest {
    private Long userId;
    private Long lectureId;
    private Long sessionId;
}
