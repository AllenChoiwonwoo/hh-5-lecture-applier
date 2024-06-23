package com.hh99.hh5cleanarchitecture.service;

import com.hh99.hh5cleanarchitecture.controller.ApplyRequest;
import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    public UserApplication apply(ApplyRequest applyRequest) {
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
        UserApplication userApplication = lectureRepository.getApplyRecord(applyRequest.getUserId(), applyRequest.getLectureId());
        if (nonNull(userApplication)){
            throw new RuntimeException("이미 신청한 유저입니다.");
        }
        // 락 시작
        Boolean isfull = lectureRepository.isFull(applyRequest.getSessionId());
        if (isfull){
            throw new RuntimeException("수강인원이 초과되었습니다.");
        }

        Application application = lectureRepository.getApplication(applyRequest.getSessionId());
        application.raseCount();
//        Application applicationAdded = lectureRepository.raseCurrentApplier(applyRequest.getUserId(),applyRequest.getLectureId(),applyRequest.getSessionId());

        // 락 종료
        if (application.getCurrentApplier() == application.getMaxApplier()) {
            lectureRepository.setFullBooking(applyRequest.getSessionId());
        }
        UserApplication result = lectureRepository.addApplier(applyRequest.getUserId(),applyRequest.getLectureId(), applyRequest.getSessionId());
        return result;
    }
}
