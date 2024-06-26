package com.hh99.hh5cleanarchitecture.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hh99.hh5cleanarchitecture.util.TimestampConverter;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequest {
    private Long userId;
    private Long lectureId;
    private Long sessionId;
    @JsonIgnore
    private String dateTime;
    @JsonIgnore
    private Long timestamp;

    public ApplyRequest(Long userId, Long lectureId, Long sessionId) {
        this.userId = userId;
        this.lectureId = lectureId;
        this.sessionId = sessionId;
        this.dateTime = TimestampConverter.timestampToString(System.currentTimeMillis());
        this.timestamp = System.currentTimeMillis();
    }
    public ApplyRequest(Long userId, Long lectureId, Long sessionId, String dateTime) {
        this.userId = userId;
        this.lectureId = lectureId;
        this.sessionId = sessionId;
        this.dateTime = dateTime;
        this.timestamp = TimestampConverter.stringToTimestamp(dateTime);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
        this.timestamp = TimestampConverter.stringToTimestamp(dateTime);
    }
}
