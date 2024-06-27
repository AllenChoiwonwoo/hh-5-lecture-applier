package com.hh99.hh5cleanarchitecture.application;

import com.hh99.hh5cleanarchitecture.domain.repository.RegistrationStatusRepository;
import com.hh99.hh5cleanarchitecture.domain.repository.UserEnrollmentRepository;
import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.domain.entity.RegistrationStatus;
import com.hh99.hh5cleanarchitecture.domain.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
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
        lectureApplyHelper.checkAvailable(applyRequest);
        RegistrationStatus registrationstatus = raiseApplicantsCount(applyRequest.getLectureScheduleId());
        lectureApplyHelper.checkRegistrationStatus(registrationstatus);
        UserEnrollment userEnrollment = UserEnrollment.builder().lectureId(applyRequest.getLectureId()).lectureScheduleId(applyRequest.getLectureScheduleId()).userId(applyRequest.getUserId()).build();
        UserEnrollment result = userEnrollmentRepository.save(userEnrollment);
        return result;
    }
    public RegistrationStatus raiseApplicantsCount(Long lectureScheduleId) {
        RegistrationStatus registrationstatus = registrationStatusRepository.findRegistrationStatusBy(lectureScheduleId);
        registrationStatusRepository.save(registrationstatus.raseCount());
        return registrationstatus;
    }

}
