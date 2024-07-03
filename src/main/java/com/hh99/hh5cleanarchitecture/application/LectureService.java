package com.hh99.hh5cleanarchitecture.application;

import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureScheduleRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.RegistrationStatusRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.UserEnrollmentRepository;
import com.hh99.hh5cleanarchitecture.domain.service.LectureChecker;
import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final LectureScheduleRepository lectureScheduleRepository;
    private final UserEnrollmentRepository userEnrollmentRepository;
    private final RegistrationStatusRepository registrationStatusRepository;
    private final LectureChecker lectureApplyHelper;

        /***
         * 수강신청
         * @param applyRequest
         * @return UserEnrollment ( 유정의 수강신청정보 )
         */
        @Transactional
        public UserEnrollment apply(ApplyRequest applyRequest) {
            // 수강신청 가능여부 체크
            LectureSchedule lectureSchedule = lectureScheduleRepository.findLectureScheduleBy(applyRequest.getLectureId());
            Optional<UserEnrollment> userEnrollment = userEnrollmentRepository.findByUserIdAndLectureId(applyRequest.getUserId(), applyRequest.getLectureId());
            lectureApplyHelper.checkAvailable(applyRequest.getTimestamp(), lectureSchedule, userEnrollment);

            // 특강의 잔여석 유뮤 업데이트 & 특강의 현재 수강인원 업데이트
            RegistrationStatus registrationstatus = registrationStatusRepository.findRegistrationStatusBy(lectureSchedule.getId());
            lectureApplyHelper.updateAvailableSeatsInfo(lectureSchedule, registrationstatus);
            lectureScheduleRepository.save(lectureSchedule);
            registrationStatusRepository.save(registrationstatus);

            // 수강자 명단에 신청자의 정보 추가
            return userEnrollmentRepository.save(
                    UserEnrollment.builder().lectureId(applyRequest.getLectureId()).lectureScheduleId(applyRequest.getLectureScheduleId()).userId(applyRequest.getUserId()).build()
            );
        }
}
