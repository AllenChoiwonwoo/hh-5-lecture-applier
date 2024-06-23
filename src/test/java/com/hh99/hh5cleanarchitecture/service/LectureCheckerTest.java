package com.hh99.hh5cleanarchitecture.service;

import com.hh99.hh5cleanarchitecture.controller.ApplyRequest;
import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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

    private ApplyRequest applyRequest;

    @BeforeEach
    void setUp() {
        applyRequest = ApplyRequest.builder().userId(1l).lectureId(1l).sessionId(1l).build();
    }

    @DisplayName("success : 수강유저 추가 성공")
    @Test
    void checkAvailable() {
        //given
        Long currentApplier = 10l;
        Application application = Application.builder().currentApplier(currentApplier).build();
        given(lectureRepository.getApplication(applyRequest.getSessionId())).willReturn(application);
        //when
        Application result = lectureChecker.checkAvailable(applyRequest);
        //then
        assert (currentApplier+1) == result.getCurrentApplier();

    }

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
    //    @DisplayName("수강신청 실패 : 이미 신청한 유저")
//    @Test
//    void fail_apply_1() {
//        //given
//        UserApplication userApplication = UserApplication.builder().build();
//        given(lectureRepository.getApplyRecord(anyLong(), anyLong())).willReturn(userApplication);
//        //when, then
//        assertThrows(RuntimeException.class, () -> {
//            lectureService.apply(applyRequest);
//        });
//    }
}