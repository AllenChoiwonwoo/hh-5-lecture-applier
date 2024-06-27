package com.hh99.hh5cleanarchitecture.application;

import com.hh99.hh5cleanarchitecture.domain.repository.LectureScheduleRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.RegistrationStatusRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.UserEnrollmentRepository;
import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.LectureSchedule;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class LectureChecker {
    private final LectureRepository lectureRepository;
    private final LectureScheduleRepository lectureScheduleRepository;
    private final RegistrationStatusRepository registrationStatusRepository;
    private final UserEnrollmentRepository userEnrollmentRepository;

        public void checkAvailable(ApplyRequest applyRequest) {
            checkScadule(applyRequest);
            checkFullBooking(applyRequest);
            checkUserApplication(applyRequest);
        }

    public void checkScadule(ApplyRequest request) {
        LectureSchedule lectureschedule = lectureScheduleRepository.findLectureScheduleBy(request.getLectureScheduleId());
        boolean result = lectureschedule.isAvailable(request.getTimestamp());
        if (result == false) throw new RuntimeException("수강 신청 가능 기간이 아닙니다.");
    }

    public void checkFullBooking(ApplyRequest applyRequest) {
        Boolean isfull = lectureScheduleRepository.isFull(applyRequest.getLectureScheduleId());
        if (isfull){
            throw new RuntimeException("수강 가능 인원이 초과되었습니다.");
        }
    }

    public void checkUserApplication(ApplyRequest applyRequest) {
        UserEnrollment userEnrollment = userEnrollmentRepository.getApplyRecord(applyRequest.getUserId(), applyRequest.getLectureId());
        if (nonNull(userEnrollment)){
            throw new RuntimeException("이미 신청한 유저입니다.");
        }
    }
    public void checkRegistrationStatus(RegistrationStatus registrationstatus) {
        LectureSchedule lectureSchedule = lectureScheduleRepository.findLectureScheduleBy(registrationstatus.getLectureScheduleId());
        lectureSchedule.checkFull(registrationstatus.getMaxApplicants(), registrationstatus.getCurrentApplicants());
        lectureScheduleRepository.saveLectureSchedule(lectureSchedule);
    }
}
