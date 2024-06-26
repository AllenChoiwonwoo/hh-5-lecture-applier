package com.hh99.hh5cleanarchitecture.service;

import com.hh99.hh5cleanarchitecture.controller.ApplyRequest;
import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.Session;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import com.hh99.hh5cleanarchitecture.util.TimestampConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LectureCheckerTest {
    @InjectMocks
    @Spy
    LectureChecker lectureChecker;
    @Mock
    LectureRepository lectureRepository;

    TimestampConverter timestampConverter = new TimestampConverter();

    private ApplyRequest applyRequest;

    @BeforeEach
    void setUp() {
        applyRequest = ApplyRequest.builder().userId(1l).lectureId(1l).sessionId(1l).build();
    }

//    @DisplayName("success : 수강유저 추가 성공")
//    @Test
//    void checkAvailable() {
//        //given
//        Long currentApplier = 10l;
//        Application application = Application.builder().currentApplier(currentApplier).build();
//        //when
//        Application result = lectureChecker.checkAvailable(applyRequest);
//        //then
//        assert (currentApplier+1) == result.getCurrentApplier();
//    }

    @DisplayName("fail : 정원 초과")
    @Test
    void fail_checkFullBooking() {
        //given
        given(lectureRepository.isFull(anyLong())).willReturn(true);
        //when, then
        assertThrows(RuntimeException.class, () -> {
            lectureChecker.checkFullBooking(applyRequest);
        });
    }

    @DisplayName("fail : 이미 신청한 유저")
    @Test
    void fail_checkUserApplication() {
        //given
        UserApplication userApplication = UserApplication.builder().build();
        given(lectureRepository.getApplyRecord(anyLong(), anyLong())).willReturn(userApplication);
        //when, then
        assertThrows(RuntimeException.class, () -> {
            lectureChecker.checkUserApplication(applyRequest);
        });
    }

//    @DisplayName("success : 수강 인원 +1 ")
//    @Test
//    void success_raseApplierCount() {
//        //given
//        Long currentApplier = 10l;
//        Application application = Application.builder().sessionId(1l).currentApplier(currentApplier).maxApplier(30l).build();
//        given(lectureRepository.getApplication(application.getSessionId())).willReturn(application);
//
//        //when
//        Application result = lectureChecker.raseApplierCount(application.getSessionId());
//
//        //then
//        assert currentApplier + 1 == result.getCurrentApplier();
//    }

    @DisplayName("fail : 수강신청 기간이 아닌 강의를 신청할때")
    @Test
    void fail_apply1() {
        //given
        String applyDate = "2024-06-25 10:00:00";
        applyRequest.setDateTime(applyDate);
        String applyOpenDate = "2024-07-25 10:00:00";
        Session session = Session.builder()
                .sessionId(1l)
                        .applyDate(TimestampConverter.stringToTimestamp(applyOpenDate)).build();
        given(lectureRepository.getSession(session.getId())).willReturn(session);

        //when, then
        assertThrows(RuntimeException.class, () -> {
            lectureChecker.checkScadule(applyRequest);
        });

    }

    @DisplayName("fail : 없는 강의를 신청할때")
    @Test
    void fail_apply2() {
        //given
        given(lectureRepository.getSession(applyRequest.getSessionId())).willThrow(new RuntimeException("No Session"));
        //when
        assertThrows(RuntimeException.class, () -> {
            lectureChecker.checkScadule(applyRequest);
        });
    }
}