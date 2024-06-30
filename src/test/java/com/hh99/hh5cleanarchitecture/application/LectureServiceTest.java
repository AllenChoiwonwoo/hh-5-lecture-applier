package com.hh99.hh5cleanarchitecture.application;

import com.hh99.hh5cleanarchitecture.domain.repository.RegistrationStatusRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.UserEnrollmentRepository;
import com.hh99.hh5cleanarchitecture.domain.service.LectureChecker;
import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    @Spy
    LectureService lectureService;
    @Mock
    LectureRepository lectureRepository;
    @Mock
    RegistrationStatusRepository registrationStatusRepository;
    @Mock
    LectureChecker lectureChecker;
    @Mock
    UserEnrollmentRepository userEnrollmentRepository;

    private ApplyRequest applyRequest;

    /*
    이미 신청했는지 확인
        이미 했으면 exception
    남은 자리 있는지 확인
        자리 없으면 exception
    수강인원 +1 로 업데이트
    정원 찾는지 확인하고 찼다면
        fullBook 처리
    수강자 명단에 추가
    응답
     */
    @BeforeEach
    void setUp() {
        applyRequest = ApplyRequest.builder().userId(1l).lectureId(1l).lectureScheduleId(1l).build();
    }


//    @DisplayName("수강신청 실패 : 이미 신청한 유저")
//    @Test
//    void fail_apply_1() {
//        //given
//        UserEnrollment userApplication = UserEnrollment.builder().build();
//        given(lectureRepository.findByUserIdAndLectureId(anyLong(), anyLong())).willReturn(userApplication);
//        //when, then
//        assertThrows(RuntimeException.class, () -> {
//            lectureService.apply(applyRequest);
//        });
//    }
//    @DisplayName("수강신청 실패 : 정원 초과 일때")
//    @Test
//    void fail_apply_2() {
//        //given
//        given(lectureRepository.findByUserIdAndLectureId(anyLong(), anyLong())).willReturn(null);
//        given(lectureRepository.isFull(anyLong())).willReturn(true);
//        //when, then
//        assertThrows(RuntimeException.class, () -> {
//            lectureService.apply(applyRequest);
//        });
//    }

    @DisplayName("수강신청 성공 : ")
    @Test
    void success_apply_1() {
        //given
        RegistrationStatus registrationstatus = RegistrationStatus.builder()
                .currentApplicants(0l)
                .maxApplicants(30l)
                .lectureScheduleId(1l)
                .build();
        UserEnrollment ua = UserEnrollment.builder()
                .applyId(1l)
                .userId(applyRequest.getUserId())
                .lectureId(applyRequest.getLectureId())
                .lectureScheduleId(applyRequest.getLectureScheduleId())
                .build();
        given(lectureService.raiseApplicantsCount(anyLong())).willReturn(registrationstatus);
        given(userEnrollmentRepository.save(
                UserEnrollment.builder()
                        .lectureId(ua.getLectureId())
                        .lectureScheduleId(ua.getLectureScheduleId())
                        .userId(ua.getUserId())
                        .applyId(ua.getId())
                        .build()
        )).willReturn(ua);
//        given(lectureRepository.addApplier(applyRequest.getUserId(), applyRequest.getLectureId(), applyRequest.getLectureScheduleId()))
//                .willReturn(ua);
//        given(registrationStatusRepository.findRegistrationStatusBy(registrationstatus.getId()))

        //when
        UserEnrollment result = lectureService.apply(applyRequest);
        //then
        System.out.println(result.getId());
        assert applyRequest.getUserId() == result.getUserId();
        assert applyRequest.getLectureId() == result.getLectureId();
    }

}