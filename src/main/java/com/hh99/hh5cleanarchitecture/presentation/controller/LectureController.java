package com.hh99.hh5cleanarchitecture.presentation.controller;

import com.hh99.hh5cleanarchitecture.domain.entity.UserEnrollment;
import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyRequest;
import com.hh99.hh5cleanarchitecture.presentation.dto.ApplyResponse;
import com.hh99.hh5cleanarchitecture.application.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {
    public final LectureService lectureService;

    @PostMapping("/apply")
    public ResponseEntity apply(@RequestBody ApplyRequest applyRequest) {
        UserEnrollment userEnrollment = lectureService.apply(applyRequest);
        return ResponseEntity.ok(ApplyResponse.from(userEnrollment));
    }
}
