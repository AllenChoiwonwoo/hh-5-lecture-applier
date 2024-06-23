package com.hh99.hh5cleanarchitecture.controller;

import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import com.hh99.hh5cleanarchitecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {
    public final LectureService lectureService;

    @PostMapping("/apply")
    public ResponseEntity apply(@RequestBody ApplyRequest applyRequest) {
        UserApplication userApplication = lectureService.apply(applyRequest);
        return ResponseEntity.ok(ApplyResponse.from(userApplication));
//        return (ResponseEntity) ResponseEntity.ok();
//        ApplyResponse applyResponse = ApplyResponse.builder()
//                .applyId(0l)
//                .lectureId(0l)
//                .sessionId(0l)
//                .build();
//        return ResponseEntity.ok(applyResponse);
    }
}
