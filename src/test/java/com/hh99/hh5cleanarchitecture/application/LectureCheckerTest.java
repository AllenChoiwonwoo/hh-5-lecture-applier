package com.hh99.hh5cleanarchitecture.application;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureScheduleRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.UserEnrollmentRepository;
import com.hh99.hh5cleanarchitecture.domain.service.LectureChecker;
import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import com.hh99.hh5cleanarchitecture.util.TimestampConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    @Mock
    LectureScheduleRepository lectureScheduleRepository;
    @Mock
    UserEnrollmentRepository userEnrollmentRepository;

    TimestampConverter timestampConverter = new TimestampConverter();

    private ApplyRequest applyRequest;

    @BeforeEach
    void setUp() {
        applyRequest = ApplyRequest.builder().userId(1l).lectureId(1l).lectureScheduleId(1l).build();
    }

//    @DisplayName("success : 수강유저 추가 성공")
//    @Test
//    void checkAvailable() {
//        //given
//        Long currentApplier = 10l;
//        RegistrationStatus application = RegistrationStatus.builder().currentApplier(currentApplier).build();
//        //when
//        RegistrationStatus result = lectureChecker.checkAvailable(applyRequest);
//        //then
//        assert (currentApplier+1) == result.getCurrentApplicants();
//    }

//    @DisplayName("fail : 정원 초과")
//    @Test
//    void fail_checkFullBooking() {
//        //given
//        given(lectureScheduleRepository.isFull(anyLong())).willReturn(true);
//        //when, then
//        assertThrows(RuntimeException.class, () -> {
//            lectureChecker.checkFullBooking(applyRequest);
//        });
//    }

    @DisplayName("fail : 이미 신청한 유저")
    @Test
    void fail_checkUserApplication() {
        //given
        Long applyAt = System.currentTimeMillis();
        LectureSchedule lectureSchedule = LectureSchedule.builder().registrationStartAt(applyAt - 1000l).isFull(false).build();
        Optional<UserEnrollment> optUserEnroll = Optional.empty();
        //when, then
        assertThrows(RuntimeException.class, () -> {
            lectureChecker.checkAvailable(applyAt, lectureSchedule, optUserEnroll)  ;
        });
    }

//    @DisplayName("success : 수강 인원 +1 ")
//    @Test
//    void success_raseApplierCount() {
//        //given
//        Long currentApplier = 10l;
//        RegistrationStatus application = RegistrationStatus.builder().sessionId(1l).currentApplier(currentApplier).maxApplier(30l).build();
//        given(lectureRepository.findRegistrationStatusBy(application.getLectureScheduleId())).willReturn(application);
//
//        //when
//        RegistrationStatus result = lectureChecker.raseApplierCount(application.getLectureScheduleId());
//
//        //then
//        assert currentApplier + 1 == result.getCurrentApplicants();
//    }

//    @DisplayName("fail : 수강신청 기간이 아닌 강의를 신청할때")
//    @Test
//    void fail_apply1() {
//        //given
//        String applyDate = "2024-06-25 10:00:00";
//        applyRequest.setDateTime(applyDate);
//        String applyOpenDate = "2024-07-25 10:00:00";
//        LectureSchedule lectureschedule = LectureSchedule.builder()
//                .sessionId(1l)
//                .registrationStartAt(TimestampConverter.stringToTimestamp(applyOpenDate)).build();
////        given(lectureScheduleRepository.findLectureScheduleBy(lectureschedule.getId())).willReturn(lectureschedule);
//
//        //when, then
//        assertThrows(RuntimeException.class, () -> {
//            lectureChecker.checkScadule(lectureschedule, applyRequest.getTimestamp());
//        });
//
//    }

    @DisplayName("fail : 없는 강의를 신청할때")
    @Test
    void fail_apply2() {
        //given
        Long applyAt = System.currentTimeMillis();
        LectureSchedule lectureSchedule = LectureSchedule.builder().registrationStartAt(applyAt - 1000l).isFull(false).build();
        Optional<UserEnrollment> optUserEnroll = Optional.empty();
//        given(lectureScheduleRepository.findLectureScheduleBy(applyRequest.getLectureScheduleId())).willThrow(new RuntimeException("No LectureSchedule"));
        //when
        assertThrows(RuntimeException.class, () -> {
            lectureChecker.checkAvailable(applyRequest);
        });
    }
}