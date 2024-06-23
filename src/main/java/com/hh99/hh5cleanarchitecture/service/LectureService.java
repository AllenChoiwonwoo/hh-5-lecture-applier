package com.hh99.hh5cleanarchitecture.service;

import com.hh99.hh5cleanarchitecture.controller.ApplyRequest;
import com.hh99.hh5cleanarchitecture.entity.Application;
import com.hh99.hh5cleanarchitecture.entity.Session;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final LectureChecker lectureChecker;
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
        Application application = lectureChecker.checkAvailable(applyRequest);
        if (application.getCurrentApplier() == application.getMaxApplier()) {
            Session session =  lectureRepository.getSession(applyRequest.getSessionId());
            session.setFull();
        }
        UserApplication result = lectureRepository.addApplier(applyRequest.getUserId(),applyRequest.getLectureId(), applyRequest.getSessionId());
        return result;
    }

}
