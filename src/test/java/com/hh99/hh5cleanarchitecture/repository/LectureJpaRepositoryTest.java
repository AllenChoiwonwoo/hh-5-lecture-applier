package com.hh99.hh5cleanarchitecture.repository;

import com.hh99.hh5cleanarchitecture.entity.Lecture;
import com.hh99.hh5cleanarchitecture.infra.LectureJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LectureJpaRepositoryTest {
    @Autowired
    LectureJpaRepository lectureJpaRepository;

    @Test
    public void success_save(){
        //given
        Lecture lecture = Lecture.builder()
                .name("엘런의 특강")
                .build();
        //when
        Lecture result = lectureJpaRepository.save(lecture);
        //then
        assert lecture.getName().equals(result.getName());
    }

    @Test
    public void success_find() {
        //given
        String lectureName ="엘런의 특강";
        Lecture lecture = Lecture.builder()
                .name(lectureName)
                .build();
        Lecture savedLecture = lectureJpaRepository.save(lecture);
        //when
        Lecture result = lectureJpaRepository.findById(savedLecture.getId()).get();
        //then
        assert lectureName.equals(result.getName());
        assert savedLecture.getId() == result.getId();
    }

    @Test
    public void success_findByName() {
        //given
        String lectureName = "엘런의 특강";
        Lecture lecture = Lecture.builder()
                .name(lectureName)
                .build();
        lectureJpaRepository.save(lecture);
        //when
        Lecture result = lectureJpaRepository.findLectureByName(lectureName);
        //then
        System.out.println(result.toString());
        assertEquals(1l, result.getId());
    }

}